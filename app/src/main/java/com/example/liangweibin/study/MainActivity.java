package com.example.liangweibin.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button schoolmessage;
    private Button translation;
    private Button getJob;
    private Button jisuanqi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        schoolmessage = (Button) findViewById(R.id.schoolmessage);
        translation = (Button) findViewById(R.id.translation);
        getJob = (Button) findViewById(R.id.getJob);
        jisuanqi = findViewById(R.id.jisuanqi);
        schoolmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SchoolLoginActivity.class);
                startActivity(intent);
            }
        });

        translation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TranslationActivity.class);
                startActivity(intent);
            }
        });

        getJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GetJobActivity.class);
                startActivity(intent);
            }
        });
        jisuanqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,JisuanqiActivity.class);
                startActivity(intent);
            }
        });
    }
}
