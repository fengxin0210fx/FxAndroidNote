package io.noties.markwon.app.readme

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.ThreadUtils
import io.noties.markwon.*
import io.noties.markwon.app.utils.textOrHide
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin
import io.noties.markwon.ext.tasklist.TaskListPlugin
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.ImagesPlugin
import io.noties.markwon.recycler.MarkwonAdapter
import io.noties.markwon.recycler.SimpleEntry
import io.noties.markwon.recycler.table.TableEntry
import io.noties.markwon.recycler.table.TableEntryPlugin
import io.noties.prism4j.annotations.PrismBundle
import org.commonmark.ext.gfm.tables.TableBlock
import org.commonmark.node.FencedCodeBlock

@PrismBundle(includeAll = true)
class ReadMeActivity : Activity() {
    var TAG:String="ReadMeActivity"

    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_me)

        progressBar = findViewById(R.id.progress_bar)

        val stringExtra = intent.getStringExtra("data")

        initAppBar(stringExtra)

        initRecyclerView(stringExtra)
    }

    private val markwon: Markwon
        get() = Markwon.builder(this)
            .usePlugin(ImagesPlugin.create())
            .usePlugin(HtmlPlugin.create())
            .usePlugin(TableEntryPlugin.create(this))
            .usePlugin(TaskListPlugin.create(this))
            .usePlugin(StrikethroughPlugin.create())
            .usePlugin(ReadMeImageDestinationPlugin(intent.data))
            .usePlugin(object : AbstractMarkwonPlugin() {
                override fun configureVisitor(builder: MarkwonVisitor.Builder) {
                    builder.on(FencedCodeBlock::class.java) { visitor, block ->
                        // we actually won't be applying code spans here, as our custom view will
                        // draw background and apply mono typeface
                        //
                        // NB the `trim` operation on literal (as code will have a new line at the end)
                        val code = visitor.configuration()
                            .syntaxHighlight()
                            .highlight(block.info, block.literal.trim())
                        visitor.builder().append(code)
                    }
                }

                override fun configureConfiguration(builder: MarkwonConfiguration.Builder) {
                    builder.linkResolver(ReadMeLinkResolver())
                }
            })
            .build()

    private fun initAppBar(data: String?) {
        val appBar = findViewById<View>(R.id.app_bar)
        appBar.findViewById<View>(R.id.app_bar_icon).setOnClickListener { onBackPressed() }

        val (title: String, subtitle: String?) = if (data == null) {
            Pair("adb.md", null)
        } else {
            Pair(data ?: "", data.toString())
        }

        appBar.findViewById<TextView>(R.id.title).text = title
        appBar.findViewById<TextView>(R.id.subtitle).textOrHide(subtitle)
    }

    private fun initRecyclerView(data: String?) {

        val adapter = MarkwonAdapter.builder(R.layout.adapter_node, R.id.text_view)
            .include(
                FencedCodeBlock::class.java,
                SimpleEntry.create(R.layout.adapter_node_code_block, R.id.text_view)
            )
            .include(TableBlock::class.java, TableEntry.create {
                it
                    .tableLayout(R.layout.adapter_node_table_block, R.id.table_layout)
                    .textLayoutIsRoot(R.layout.view_table_entry_cell)
            })
            .build()

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        ThreadUtils.getCachedPool().execute {
            val readAssets2String = ResourceUtils.readAssets2String(data)
            LogUtils.dTag(TAG, readAssets2String)
            runOnUiThread {
                val node = markwon.parse(readAssets2String)
                adapter.setParsedMarkdown(markwon, node)
                adapter.notifyDataSetChanged()

            }
        }

    }


}