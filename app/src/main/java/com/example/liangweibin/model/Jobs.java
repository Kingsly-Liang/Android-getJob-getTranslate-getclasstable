package com.example.liangweibin.model;

public class Jobs {
    private String jobName;
    private String company;
    private String address;
    private String payment;
    private String releaseTime;
    private String request;

    public Jobs(String jobName, String company, String address, String payment, String releaseTime, String request){
        this.jobName = jobName;
        this.company = company;
        this.address = address;
        this.payment = payment;
        this.releaseTime = releaseTime;
        this.request = request;
    }


    public String getJobName() {
        return jobName;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public String getReleaseTime() {
        return releaseTime;
    }
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
    public String getRequest() {
        return request;
    }
    public void setRequest(String request) {
        this.request = request;
    }
    @Override
    public String toString() {
        return "Jobs [jobName=" + jobName + ", company=" + company + ", address=" + address + ", payment=" + payment
                + ", releaseTime=" + releaseTime + ", request=" + request + "]";
    }
}
