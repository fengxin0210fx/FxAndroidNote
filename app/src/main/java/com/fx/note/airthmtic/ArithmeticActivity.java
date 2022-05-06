package com.fx.note.airthmtic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.fx.note.PngShowActivity;
import com.fx.note.R;
import com.fx.note.airthmtic.tree.TreeTest;
import com.fx.note.airthmtic.tree.TwoTreeActivity;
import com.fx.note.handler.HandlerActivity;

public class ArithmeticActivity extends AppCompatActivity {
    private Button show_png_1, show_png_2, show_png_3, show_png_4, show_png_5,show_png_6,show_png_7;
    private static final String TAG = "ArithmeticActivity";



    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic);
        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        testDem1();
        stringFromJNI();
        TreeTest treeTest = new TreeTest();

        treeTest.createTree(treeTest.root,treeTest.index);
//        int deth = treeTest.getDeth(treeTest.root);
//        LogUtils.dTag(TAG, "deth  " + deth);



        LogUtils.dTag(TAG, "递归先序.........  " );
        treeTest.traverseInOrder(treeTest.root);
//        LogUtils.dTag(TAG, "递归中序.........  " );
//        treeTest.traverseMidOrder(treeTest.root);
//        LogUtils.dTag(TAG, "递归后序.........  " );
//        treeTest.traverseEndOrder(treeTest.root);
        LogUtils.dTag(TAG, "先序.........  " );
        treeTest.traverseInOrder1(treeTest.root);
        LogUtils.dTag(TAG, "中序.........  " );
        treeTest.traverseMidOrder1(treeTest.root);
        LogUtils.dTag(TAG, "后序.........  " );
        treeTest.traverseEndOrder1(treeTest.root);


//        treeTest.traverseInOrder(treeTest.root);
        show_png_1=findViewById(R.id.show_png_1);
        show_png_2=findViewById(R.id.show_png_2);
        show_png_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(ArithmeticActivity.this, TwoTreeActivity.class);
            }
        });
        show_png_5=findViewById(R.id.show_png_5);
        show_png_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ArithmeticActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","HashMap");
                startActivity(intent);
            }
        });
        show_png_6=findViewById(R.id.show_png_6);
        show_png_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ArithmeticActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","concurrenthashmap_hashtable_treemap");
                startActivity(intent);
            }
        });
        show_png_7=findViewById(R.id.show_png_7);
        show_png_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ArithmeticActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","arraylist_linkedlist_vector");
                startActivity(intent);
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String testDem1();

}