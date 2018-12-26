package com.example.liangweibin.study;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.liangweibin.adapter.JobsAdapter;
import com.example.liangweibin.model.Jobs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class GetJobFromNetActivity extends AppCompatActivity {

    Intent intent;
    private List<Jobs> jobsList;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_job_from_net);

        jobsList = new ArrayList<>();
        intent = getIntent();
        String jobNameUrl = intent.getStringExtra("jobNameUrl");
        Log.v("jobNameUrl:",jobNameUrl+"");

        getJobs(jobNameUrl);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    Log.v("列表：",jobsList+"");
                    JobsAdapter adapter = new JobsAdapter(GetJobFromNetActivity.this,R.layout.job_item,jobsList);
                    ListView listView = findViewById(R.id.job_list_view);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Jobs jobs = jobsList.get(position);
                            Intent intent = new Intent(GetJobFromNetActivity.this,ShowJobActivity.class);
                            intent.putExtra("jobnames",jobs.getJobName());
                            intent.putExtra("jobaddress",jobs.getAddress());
                            intent.putExtra("jobcompany",jobs.getCompany());
                            intent.putExtra("jobrequest",jobs.getRequest());
                            intent.putExtra("jobtime",jobs.getReleaseTime());
                            intent.putExtra("jobpayment",jobs.getPayment());
                            startActivity(intent);
                        }
                    });
                }

            }
        };


    }

    public void getJobs(String url){
        final String urlGet = url;
        final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3573.0 Safari/537.36";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Document doc = Jsoup.connect(urlGet).userAgent(userAgent).get();
                    Elements name = doc.select("div.el p span a");
                    Elements company = doc.select("div.el span.t2 a");
                    Elements address = doc.select("div.el span.t3");
                    Elements payment = doc.select("div.el span.t4");
                    Elements time = doc.select("div.el span.t5");

                    for (int i = 0; i < name.size(); i++) {
                        Document request = Jsoup.connect(name.get(i).attr("href")).userAgent(userAgent).get();
                        Elements writeRequest = request.select("div.bmsg.job_msg.inbox p");
                        String writejobName = name.get(i).text();
                        String writecompany = company.get(i).text();
                        String writeadress = address.get(i+1).text();
                        String writepayment = payment.get(i+1).text();
                        String writetime = time.get(i+1).text();
                        String writerequest = writeRequest.text();

                        Log.v("jobName:",writejobName+"");
                        Log.v("jobcompany:",writecompany+"");
                        Log.v("jobadress:",writeadress+"");
                        Log.v("jobpayment:",writepayment+"");
                        Log.v("jobtime:",writetime+"");
                        Log.v("jobrequest:",writerequest+"");

                        Jobs jobs = new Jobs(writejobName, writecompany, writeadress, writepayment, writetime, writerequest);
                        jobsList.add(jobs);
                    }

                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                }catch(Exception e){
                    e.printStackTrace();
                }finally {

                }
            }
        }).start();
    }
}
