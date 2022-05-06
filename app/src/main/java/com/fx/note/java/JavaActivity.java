package com.fx.note.java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fx.note.PngShowActivity;
import com.fx.note.R;
import com.fx.note.java.JavaNew.JavaNew;
import com.fx.note.java.annotation.TestAnn;
import com.fx.note.java.proxy.TestProxy;

public class JavaActivity extends AppCompatActivity {
    private TextView show_png_0, show_png_1,show_png_2,show_png_3,show_png_4,show_png_5,show_png_6,show_png_7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        show_png_0 = findViewById(R.id.show_png_0);
        show_png_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "String");
                startActivity(intent);
            }
        });

        show_png_1 = findViewById(R.id.show_png_1);
        show_png_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "annotation");
                startActivity(intent);
            }
        });
        show_png_2 = findViewById(R.id.show_png_2);
        show_png_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "reflect1");
                startActivity(intent);
            }
        });
        show_png_3 = findViewById(R.id.show_png_3);
        show_png_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "reflect2");
                startActivity(intent);
            }
        });
        show_png_4 = findViewById(R.id.show_png_4);
        show_png_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "proxy");
                startActivity(intent);
            }
        });
        show_png_5 = findViewById(R.id.show_png_5);
        show_png_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "jvmart");
                startActivity(intent);
            }
        });
        show_png_6 = findViewById(R.id.show_png_6);
        show_png_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "javanew");
                startActivity(intent);
            }
        });
        show_png_7 = findViewById(R.id.show_png_7);
        show_png_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JavaActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "javaquote");
                startActivity(intent);
            }
        });

        TestAnn.startTest();
        TestProxy.startTest();
        JavaNew.startTest();
    }
}