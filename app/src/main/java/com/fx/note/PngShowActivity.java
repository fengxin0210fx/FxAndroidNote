package com.fx.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.UriUtils;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;


public class PngShowActivity extends AppCompatActivity {
    private static final String TAG = "PngShowActivity";
    int SELR = 0;
    private String mMipmapR = "viewevent";

    /**
     * 分享前必须执行本代码，主要用于兼容SDK18以上的系统
     * 否则会报android.os.FileUriExposedException: file:///xxx.pdf exposed beyond app through ClipData.Item.getUri()
     */
    private void checkFileUriExposure() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_png_show);
        Button png_share = findViewById(R.id.png_share);

        PhotoView photo_view = findViewById(R.id.photo_view);
        photo_view.setMaximumScale(100);
        Intent intent = getIntent();
        String extra = intent.getStringExtra("pngid");

        if (TextUtils.equals(extra, "event")) {
            photo_view.setImageResource(R.mipmap.viewevent);
            mMipmapR = "viewevent";
            SELR = R.mipmap.viewevent;
        }

        if (TextUtils.equals(extra, "Handler")) {
            photo_view.setImageResource(R.mipmap.handler);
            mMipmapR = "handler";
            SELR = R.mipmap.handler;
        }

        if (TextUtils.equals(extra, "IdleHandler")) {
            photo_view.setImageResource(R.mipmap.idlehandler);
            mMipmapR = "idlehandler";
            SELR = R.mipmap.idlehandler;
        }
        if (TextUtils.equals(extra, "Looper")) {
            photo_view.setImageResource(R.mipmap.looper);
            mMipmapR = "looper";
            SELR = R.mipmap.looper;
        }
        if (TextUtils.equals(extra, "Message")) {
            photo_view.setImageResource(R.mipmap.message);
            mMipmapR = "message";
            SELR = R.mipmap.message;
        }
        if (TextUtils.equals(extra, "MessageQueue")) {
            photo_view.setImageResource(R.mipmap.messagequeue);
            mMipmapR = "messagequeue";
            SELR = R.mipmap.messagequeue;
        }
        if (TextUtils.equals(extra, "ThreadLocal")) {
            photo_view.setImageResource(R.mipmap.threadlocal);
            mMipmapR = "threadlocal";
            SELR = R.mipmap.threadlocal;
        }
        if (TextUtils.equals(extra, "IntentService")) {
            photo_view.setImageResource(R.mipmap.intentservice);
            mMipmapR = "intentservice";
            SELR = R.mipmap.intentservice;
        }


        if (TextUtils.equals(extra, "application_init")) {
            photo_view.setImageResource(R.mipmap.application_init);
            mMipmapR = "application_init";
            SELR = R.mipmap.application_init;
        }
        if (TextUtils.equals(extra, "apkbuild")) {
            photo_view.setImageResource(R.mipmap.apkbuild);
            mMipmapR = "apkbuild";
            SELR = R.mipmap.apkbuild;
        }
        if (TextUtils.equals(extra, "apkinstall")) {
            photo_view.setImageResource(R.mipmap.apkinstall);
            mMipmapR = "apkinstall";
            SELR = R.mipmap.apkinstall;
        }


        if (TextUtils.equals(extra, "handlerReusme")) {
            photo_view.setImageResource(R.mipmap.handlerreusme);
            mMipmapR = "handlerreusme";
            SELR = R.mipmap.handlerreusme;
        }

        if (TextUtils.equals(extra, "setContentView")) {
            photo_view.setImageResource(R.mipmap.setcontentview);
            mMipmapR = "setcontentview";
            SELR = R.mipmap.setcontentview;
        }

        if (TextUtils.equals(extra, "Choreographer")) {
            photo_view.setImageResource(R.mipmap.choreographer);
            mMipmapR = "choreographer";
            SELR = R.mipmap.choreographer;
        }
        if (TextUtils.equals(extra, "viewmeasure")) {
            photo_view.setImageResource(R.mipmap.viewmeasure);
            mMipmapR = "viewmeasure";
            SELR = R.mipmap.viewmeasure;
        }
        if (TextUtils.equals(extra, "invalidate_to_ondraw")) {
            photo_view.setImageResource(R.mipmap.invalidate_to_ondraw);
            mMipmapR = "invalidate_to_ondraw";
            SELR = R.mipmap.invalidate_to_ondraw;
        }
        if (TextUtils.equals(extra, "viewpost")) {
            photo_view.setImageResource(R.mipmap.viewpost);
            mMipmapR = "viewpost";
            SELR = R.mipmap.viewpost;
        }

        TextUtils.equals(extra, "Thread");   //photo_view.setImageResource(R.mipmap.th);


        if (TextUtils.equals(extra, "Thread")) {
            photo_view.setImageResource(R.mipmap.thread);
            mMipmapR = "thread";
            SELR = R.mipmap.thread;
        }

        if (TextUtils.equals(extra, "AtomicInteger")) {
            photo_view.setImageResource(R.mipmap.atomicinteger);
            mMipmapR = "atomicinteger";
            SELR = R.mipmap.atomicinteger;
        }

        if (TextUtils.equals(extra, "Synchronized")) {
            photo_view.setImageResource(R.mipmap.synchroniz);
            mMipmapR = "synchroniz";
            SELR = R.mipmap.synchroniz;
        }

        if (TextUtils.equals(extra, "ReetrantLock")) {
            photo_view.setImageResource(R.mipmap.reetrantlock);
            mMipmapR = "reetrantlock";
            SELR = R.mipmap.reetrantlock;
        }

        if (TextUtils.equals(extra, "ThreadPool")) {

            photo_view.setImageResource(R.mipmap.threadpoolexecutor);
            mMipmapR = "threadpoolexecutor";
            SELR = R.mipmap.threadpoolexecutor;
        }

        if (TextUtils.equals(extra, "AsyncTask")) {

            photo_view.setImageResource(R.mipmap.asynctask);
            mMipmapR = "asynctask";
            SELR = R.mipmap.asynctask;
        }
        if (TextUtils.equals(extra, "FutureTask")) {

            photo_view.setImageResource(R.mipmap.futuretask);
            mMipmapR = "futuretask";
            SELR = R.mipmap.futuretask;
        }


        if (TextUtils.equals(extra, "Service")) {

            photo_view.setImageResource(R.mipmap.service);
            mMipmapR = "service";
            SELR = R.mipmap.service;
        }

        if (TextUtils.equals(extra, "twothreebase")) {

            photo_view.setImageResource(R.mipmap.twothreebase);
            mMipmapR = "twothreebase";
            SELR = R.mipmap.twothreebase;
        }
        if (TextUtils.equals(extra, "sharedpreference")) {

            photo_view.setImageResource(R.mipmap.sharedpreference);
            mMipmapR = "sharedpreference";
            SELR = R.mipmap.sharedpreference;
        }
        if (TextUtils.equals(extra, "HashMap")) {

            photo_view.setImageResource(R.mipmap.hashmap);
            mMipmapR = "hashmap";
            SELR = R.mipmap.hashmap;
        }
        if (TextUtils.equals(extra, "concurrenthashmap_hashtable_treemap")) {

            photo_view.setImageResource(R.mipmap.concurrenthashmap_hashtable);
            mMipmapR = "concurrenthashmap_hashtable";
            SELR = R.mipmap.concurrenthashmap_hashtable;
        }

        if (TextUtils.equals(extra, "arraylist_linkedlist_vector")) {

            photo_view.setImageResource(R.mipmap.arraylist_linkedlist_vector);
            mMipmapR = "arraylist_linkedlist_vector";
            SELR = R.mipmap.arraylist_linkedlist_vector;
        }


        if (TextUtils.equals(extra, "String")) {

            photo_view.setImageResource(R.mipmap.string);
            mMipmapR = "string";
            SELR = R.mipmap.string;
        }
        if (TextUtils.equals(extra, "annotation")) {

            photo_view.setImageResource(R.mipmap.annotation);
            mMipmapR = "annotation";
            SELR = R.mipmap.annotation;
        }
        if (TextUtils.equals(extra, "reflect1")) {

            photo_view.setImageResource(R.mipmap.reflect1);
            mMipmapR = "reflect1";
            SELR = R.mipmap.reflect1;
        }
        if (TextUtils.equals(extra, "reflect2")) {

            photo_view.setImageResource(R.mipmap.reflect2);
            mMipmapR = "reflect2";
            SELR = R.mipmap.reflect2;
        }
        if (TextUtils.equals(extra, "proxy")) {

            photo_view.setImageResource(R.mipmap.proxy);
            mMipmapR = "proxy";
            SELR = R.mipmap.proxy;
        }
        if (TextUtils.equals(extra, "jvmart")) {

            photo_view.setImageResource(R.mipmap.jvmart);
            mMipmapR = "jvmart";
            SELR = R.mipmap.jvmart;
        }
        if (TextUtils.equals(extra, "javanew")) {

            photo_view.setImageResource(R.mipmap.javanew);
            mMipmapR = "javanew";
            SELR = R.mipmap.javanew;
        }

        if (TextUtils.equals(extra, "javaquote")) {

            photo_view.setImageResource(R.mipmap.javaquote);
            mMipmapR = "javaquote";
            SELR = R.mipmap.javaquote;
        }

        png_share.setOnClickListener(v -> {
            //将mipmap中图片转换成Uri
            Uri imgUri = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + SELR);

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
//其中imgUri为图片的标识符
            shareIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
            shareIntent.setType("image/png");
//切记需要使用Intent.createChooser，否则会出现别样的应用选择框，您可以试试
            shareIntent = Intent.createChooser(shareIntent, "Here is the  " + mMipmapR);
            startActivity(shareIntent);

//             Uri imgUri = UriUtils.res2Uri(SELR+".png");
//             openFileThirdApp(UriUtils.uri2File(imgUri));

        });
//        ThreadUtils.getCachedPool().execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
////                            String path = "file:///android_asset/image/" + mMipmapR+".png";
//                    String path = "image";
//                    String filePath = PathUtils.getExternalDownloadsPath()
//                            + File.separator + path;
//                    ResourceUtils.copyFileFromAssets(path
//                            , filePath);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    LogUtils.eTag(TAG, e.toString());
//                }
//            }
//        });

    }

    /**
     * 调用系统应用打开文件（系统分享）
     */
    public void openFileThirdApp(File file) {
        checkFileUriExposure();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_STREAM,  UriUtils.file2Uri(file));
        intent.setType("*/*");   //分享文件类型
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        ActivityUtils.getTopActivity().startActivity(Intent.createChooser(intent, "分享"));
    }
}