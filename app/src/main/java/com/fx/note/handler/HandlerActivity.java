package com.fx.note.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fx.note.PngShowActivity;
import com.fx.note.R;
import com.fx.note.viewdraw.ViewDrawActivity;

public class HandlerActivity extends AppCompatActivity {
    private Button show_png_1,show_png_2,show_png_3,show_png_4,show_png_5,show_png_6,show_png_7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        show_png_1=findViewById(R.id.show_png_1);
        show_png_2=findViewById(R.id.show_png_2);
        show_png_3=findViewById(R.id.show_png_3);
        show_png_4=findViewById(R.id.show_png_4);
        show_png_5=findViewById(R.id.show_png_5);
        show_png_6=findViewById(R.id.show_png_6);
        show_png_7=findViewById(R.id.show_png_7);
        show_png_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HandlerActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","Handler");
                startActivity(intent);
            }
        });
        show_png_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HandlerActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","IdleHandler");
                startActivity(intent);
            }
        });
        show_png_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HandlerActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","Looper");
                startActivity(intent);
            }
        });

        show_png_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HandlerActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","Message");
                startActivity(intent);
            }
        });
        show_png_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HandlerActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","MessageQueue");
                startActivity(intent);
            }
        });
        show_png_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HandlerActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","ThreadLocal");
                startActivity(intent);
            }
        });
        show_png_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HandlerActivity.this, PngShowActivity.class);
                intent.putExtra("pngid","IntentService");
                startActivity(intent);
            }
        });
    }
}