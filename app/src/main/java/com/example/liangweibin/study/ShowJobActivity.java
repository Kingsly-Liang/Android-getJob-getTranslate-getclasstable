package com.example.liangweibin.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ShowJobActivity extends AppCompatActivity {

    Intent intent;
    TextView showjobName;
    TextView showjobAddress;
    TextView showjobCompany;
    TextView showjobRequest;
    TextView showTime;
    TextView showjobPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job);
        intent = new Intent();
        intent = getIntent();
        String jobName = intent.getStringExtra("jobnames");
        String jobaddress = intent.getStringExtra("jobaddress");
        String jobcompany = intent.getStringExtra("jobcompany");
        String jobrequest = intent.getStringExtra("jobrequest");
        String jobtime = intent.getStringExtra("jobtime");
        String jobpayment = intent.getStringExtra("jobpayment");

        showjobName = findViewById(R.id.showjobname);
        showjobAddress = findViewById(R.id.showjobaddress);
        showjobCompany = findViewById(R.id.showjobcompany);
        showjobRequest = findViewById(R.id.showjobrequest);
        showjobRequest.setMovementMethod(ScrollingMovementMethod.getInstance());
        showTime = findViewById(R.id.showjobtime);
        showjobPayment = findViewById(R.id.showjobpayment);

        showjobPayment.setText(jobpayment);
        showTime.setText(jobtime);
        showjobRequest.setText(jobrequest);
        showjobAddress.setText(jobaddress+"|");
        showjobCompany.setText(jobcompany+"|");
        showjobName.setText(jobName+"|");

    }
}
