package com.fx.note.viewdraw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fx.note.PngShowActivity;
import com.fx.note.R;
import com.fx.note.eventtest.EventActivity;

public class ViewDrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_draw);
        Button show_png_1 = findViewById(R.id.show_png_1);
        Button show_png_2 = findViewById(R.id.show_png_2);
        Button show_png_3 = findViewById(R.id.show_png_3);
        Button show_png_4 = findViewById(R.id.show_png_4);
        Button show_png_5 = findViewById(R.id.show_png_5);
        Button show_png_6 = findViewById(R.id.show_png_6);
        Button show_png_7 = findViewById(R.id.show_png_7);


        show_png_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDrawActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "setContentView");
                startActivity(intent);
            }
        });
        show_png_2.setOnClickListener(v -> {
            Intent intent = new Intent(ViewDrawActivity.this, PngShowActivity.class);
            intent.putExtra("pngid", "handlerReusme");
            startActivity(intent);
        });
        show_png_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDrawActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "Choreographer");
                startActivity(intent);
            }
        });
        show_png_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDrawActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "viewmeasure");
                startActivity(intent);
            }
        });
        show_png_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDrawActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "event");
                startActivity(intent);
            }
        });
        show_png_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDrawActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "invalidate_to_ondraw");
                startActivity(intent);
            }
        });
        show_png_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDrawActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "viewpost");
                startActivity(intent);
            }
        });


    }
}