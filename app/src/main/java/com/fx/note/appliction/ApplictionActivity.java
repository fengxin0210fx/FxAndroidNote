package com.fx.note.appliction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.fx.note.PngShowActivity;
import com.fx.note.R;

public class ApplictionActivity extends AppCompatActivity {
    private Button show_png_1, show_png_2, show_png_3, show_png_4, show_png_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliction);
        show_png_1 = findViewById(R.id.show_png_1);
        show_png_2 = findViewById(R.id.show_png_2);
        show_png_3 = findViewById(R.id.show_png_3);
        show_png_4 = findViewById(R.id.show_png_4);
        show_png_5 = findViewById(R.id.show_png_5);
        show_png_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplictionActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "application_init");
                startActivity(intent);
            }
        });
        show_png_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplictionActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "apkbuild");
                startActivity(intent);
            }
        });
        show_png_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplictionActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "apkinstall");
                startActivity(intent);
//                ToastUtils.showLong("集成中");
            }
        });

        show_png_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("集成中");
            }
        });

    }
}