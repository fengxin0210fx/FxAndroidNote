package com.fx.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.noties.markwon.app.readme.ReadMeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ServiceUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fx.note.airthmtic.ArithmeticActivity;
import com.fx.note.appliction.ApplictionActivity;
import com.fx.note.appliction.TestService;
import com.fx.note.eventtest.EventActivity;
import com.fx.note.fourcomponents.FourCpActivity;
import com.fx.note.handler.HandlerActivity;
import com.fx.note.java.JavaActivity;
import com.fx.note.thread.ThreadActivity;
import com.fx.note.thread.syn.TestSyn;
import com.fx.note.viewdraw.ViewDrawActivity;

import java.io.File;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    TextView btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
//        btn7=findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10= findViewById(R.id.btn10);
        btn11= findViewById(R.id.btn11);
//        btn8=findViewById(R.id.btn8);
//        btn9=findViewById(R.id.btn9);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ArithmeticActivity.class));
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, EventActivity.class));

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ViewDrawActivity.class));

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("集成中");
                startActivity(new Intent(HomeActivity.this, FourCpActivity.class));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HandlerActivity.class));

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ApplictionActivity.class));


            }
        });


//        new TestSyn().Test();

//        btn7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("1",2);
//                ActivityUtils.startActivity(TestActivity.class,bundle);
//                ActivityUtils.finishAllActivities();
//            }
//        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(ThreadActivity.class);
            }
        });
//        ServiceUtils.startService(TestService.class);
//        int a = 0, b = 0, c = 0;
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReadMeActivity.class);
                intent.putExtra("data", "adb.md");
                startActivity(intent);
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(JavaActivity.class);
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(TestActivity.class);
            }
        });

    }


}