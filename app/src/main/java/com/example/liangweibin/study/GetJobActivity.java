package com.example.liangweibin.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetJobActivity extends AppCompatActivity {
    EditText jobEditText;
    Button confirm;
    Button reset;
    TextView test;

    RadioButton job51;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_job);

        jobEditText = (EditText) findViewById(R.id.jobEditText);
        confirm = (Button)findViewById(R.id.confirmButton);
        reset = (Button)findViewById(R.id.reset);

        test = (TextView) findViewById(R.id.test);

        job51 = findViewById(R.id.qcwy);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobEditText.setText("");
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(job51.isChecked()) {
                    String jobNameUrl = "https://search.51job.com/list/000000,000000,0000,00,9,99," + jobEditText.getText().toString() + ",2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=\n";
                    Intent intent = new Intent(GetJobActivity.this, GetJobFromNetActivity.class);
                    intent.putExtra("jobNameUrl", jobNameUrl);
                    startActivity(intent);
                }


            }
        });
    }

}
