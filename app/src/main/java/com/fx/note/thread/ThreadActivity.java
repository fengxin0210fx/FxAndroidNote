package com.fx.note.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.fx.note.PngShowActivity;
import com.fx.note.R;

import java.util.concurrent.FutureTask;

public class ThreadActivity extends AppCompatActivity {
    private Button show_png_1, show_png_2, show_png_3, show_png_4, show_png_5,show_png_6,show_png_7,show_png_8;
    private static final String TAG = "ThreadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.dTag(TAG," Integer.SIZE:  " +Integer.SIZE);
        String  s=new String("");
        ThreadUtils.getCachedPool().execute(new Runnable() {
            @Override
            public void run() {
                int i = (1 << 29)-1;
                String toBinaryString = Integer.toBinaryString(i);
                LogUtils.dTag(TAG,"toBinaryString  " +toBinaryString);

//              toBinaryString= 0001 1111  1111 1111    1111  1111 1111 1111
//                计算机中是以补码进行存储的，最高位为符号位。
//                左移和右移都是操作补码，正数补码源码反码一样，负数的反码是原码求反(符号位不变),补码是反码+1
//                移位分为 有符号左移（<<），有符号右移（>>）
//                       无符号右移（>>>）没有无符号左移（<<<）

//                  有符号位移运算，何为有符号左移、右移，因为计算机中符号位最高位0代表正数，1代表负数，
//                  所以正数有符号右移高位补0，负数有符号右移高位补1，不管有无符号位移运算左移低位都是补0。


//                1的二进制：
//                原码      0000 0000  0000 0000    0000  0000  0000  0001
//                反码      0000 0000  0000 0000    0000  0000  0000  0001
//                补码      0000 0000  0000 0000    0000  0000  0000  0001

//                 1<<29:
//                        0010 0000  0000 0000    0000  0000  0000  0000
//                 也就是2^29
//                 CAPACITY=( 1<<29) -1
//                        0001 1111  1111 1111    1111  1111  1111  1111

//                -1<<29  负数的反码是原码求反,补码是反码+1
//                -1原码：1000 0000  0000 0000    0000  0000  0000  0001
//                -1反码: 1111 1111  1111 1111    1111  1111  1111  1110
//                -1补码：1111 1111  1111 1111    1111  1111  1111  1111

//                 -1<<29
//             左移后补码：1110 0000  0000 0000    0000  0000  0000  0000
//                  反码: 1101 1111  1111 1111    1111  1111  1111  1111
//                  源码：1010 0000  0000 0000    0000  0000  0000  0000
//                  也就是-2^29

//                  1&CAPACITY  过程：
//                  CAPACITY补码： 0001 1111  1111 1111    1111  1111  1111  1111
//                  1的补码：      0000 0000  0000 0000    0000  0000  0000  0001
//                     结果:      0000 0000  0000 0000    0000  0000  0000  0001

            }
        });

        setContentView(R.layout.activity_thread);
        show_png_1 = findViewById(R.id.show_png_1);
        show_png_2 = findViewById(R.id.show_png_2);
        show_png_3 = findViewById(R.id.show_png_3);
        show_png_4 = findViewById(R.id.show_png_4);
        show_png_5 = findViewById(R.id.show_png_5);
        show_png_6 = findViewById(R.id.show_png_6);
        show_png_7=findViewById(R.id.show_png_7);
        show_png_8=findViewById(R.id.show_png_8);
        show_png_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "Thread");
                startActivity(intent);
            }
        });
        show_png_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "AtomicInteger");
                startActivity(intent);
            }
        });
        show_png_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "Synchronized");
                startActivity(intent);

            }
        });

        show_png_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "ReetrantLock");
                startActivity(intent);
            }
        });
        show_png_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "ThreadPool");
                startActivity(intent);
            }
        });
        show_png_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "sharedpreference");
                startActivity(intent);
            }
        });
        show_png_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "AsyncTask");
                startActivity(intent);
            }
        });
        show_png_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "FutureTask");
                startActivity(intent);
            }
        });

    }
}