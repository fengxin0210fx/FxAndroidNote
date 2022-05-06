package com.fx.note.fourcomponents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fx.note.HomeActivity;
import com.fx.note.PngShowActivity;
import com.fx.note.R;
import com.fx.note.airthmtic.ArithmeticActivity;
import com.fx.note.appliction.ApplictionActivity;
import com.fx.note.eventtest.EventActivity;
import com.fx.note.handler.HandlerActivity;
import com.fx.note.thread.ThreadActivity;
import com.fx.note.thread.syn.TestSyn;
import com.fx.note.viewdraw.ViewDrawActivity;

public class FourCpActivity extends AppCompatActivity {

    TextView btn0,btn1,btn2,btn3;
    private static final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_cp);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);



//        btn8=findViewById(R.id.btn8);
//        btn9=findViewById(R.id.btn9);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("集成中");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourCpActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "Service");
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("集成中");

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("集成中");

            }
        });



    }


}