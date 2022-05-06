package com.fx.note.airthmtic.tree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fx.note.PngShowActivity;
import com.fx.note.R;
import com.fx.note.thread.ThreadActivity;

public class TwoTreeActivity extends AppCompatActivity {
    private Button show_png_1, show_png_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_tree);
        show_png_1=findViewById(R.id.show_png_1);
        show_png_2=findViewById(R.id.show_png_2);
        show_png_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoTreeActivity.this, PngShowActivity.class);
                intent.putExtra("pngid", "twothreebase");
                startActivity(intent);
            }
        });
    }
}