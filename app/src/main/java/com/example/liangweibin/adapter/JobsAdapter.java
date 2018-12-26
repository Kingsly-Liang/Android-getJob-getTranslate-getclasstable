package com.example.liangweibin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.liangweibin.model.Jobs;
import com.example.liangweibin.study.R;

import java.util.List;

public class JobsAdapter extends ArrayAdapter<Jobs> {
    private int resourceId;
    public JobsAdapter(Context context, int textViewResourceId, List<Jobs> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Jobs jobs = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView job_name = view.findViewById(R.id.jobs_name);
        TextView job_adress = view.findViewById(R.id.jobs_address);
        TextView job_payment = view.findViewById(R.id.jobs_payment);
        TextView job_company = view.findViewById(R.id.jobs_company);
        TextView job_time = view.findViewById(R.id.jobs_realeaseTime);
        job_name.setText(jobs.getJobName());
        job_adress.setText(jobs.getAddress());
        job_payment.setText(jobs.getPayment());
        job_company.setText(jobs.getCompany());
        job_time.setText(jobs.getReleaseTime());
        return view;
    }
}
