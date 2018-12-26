package com.example.liangweibin.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchGradeActivity extends AppCompatActivity {

    Intent intent;
    private WebView gradeTableView;
    private String LOGIN_URL = "http://jwxt.gdufe.edu.cn/jsxsd/";
    private String USER_AGENT = "User-Agent";
    private String USER_AGENT_VALUE = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E; InfoPath.3)";
    private String html;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_grade);

        intent = getIntent();
        final String username = intent.getStringExtra("username");
        final String password = intent.getStringExtra("password");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    login(username,password);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gradeTableView = (WebView) findViewById(R.id.courseTable);
                            html = "<table border='1' cellpadding='1' cellspacing='0' width='100%' style='font-weight:800;'>"+
                                    html +
                                    "</table>";
                            WebSettings webSettings = gradeTableView.getSettings();
                            //自适应屏幕
                            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                            webSettings.setUseWideViewPort(true);
                            webSettings.setLoadWithOverviewMode(true);
                            //缩放操作
                            webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
                            webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
                            webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

                            gradeTableView.loadData(html, "text/html; charset=UTF-8", null);
                            //Log.d("SearchCourse",html);
                        }
                    });
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //登陆并且爬取成绩表
    public void login(String username, String password) throws IOException {

        /*
         * 第一次请求
         * grab login form page first
         * 获取登陆提交的表单信息，及修改其提交data数据（login，password）
         */
        // get the response, which we will post to the action URL(rs.cookies())
        Connection con = Jsoup.connect(LOGIN_URL);  // 获取connection
        con.header(USER_AGENT, USER_AGENT_VALUE);   // 配置模拟浏览器
        Connection.Response rs = con.execute();                // 获取响应
        Document d1 = Jsoup.parse(rs.body());       // 转换为Dom树
        List<Element> eleList = d1.select("form");  // 获取提交form表单，可以通过查看页面源码代码得知

        // 获取cooking和表单属性
        // lets make data map containing all the parameters and its values found in the form
        Map<String, String> datas = new HashMap<>();
        for (Element e : eleList.get(0).getAllElements()) {
            // 设置用户名
            if (e.attr("name").equals("USERNAME")) {
                e.attr("value", username);
            }
            // 设置用户密码
            if (e.attr("name").equals("PASSWORD")) {
                e.attr("value", password);
            }
            // 排除空值表单属性
            if (e.attr("name").length() > 0) {
                datas.put(e.attr("name"), e.attr("value"));
            }
        }

        /*
         * 第二次请求，以post方式提交表单数据以及cookie信息
         */
        Connection con2 = Jsoup.connect("http://jwxt.gdufe.edu.cn/jsxsd/xk/LoginToXkLdap");
        // Connection con2 = Jsoup.connect("http://jwxt.gdufe.edu.cn/jsxsd/xskb/xskb_list.do");
        con2.header(USER_AGENT, USER_AGENT_VALUE);
        // 设置cookie和post上面的map数据
        Connection.Response login = con2.ignoreContentType(true).followRedirects(true).method(Connection.Method.POST)
                .data(datas).cookies(rs.cookies()).execute();

        // 打印，登陆成功后的信息
        // parse the document from response
        //System.out.println(login.body());
        Document dc = Jsoup.parse(login.body());
        //System.out.println(dc.select("div.wap a").attr("href"));

        Connection con1 = Jsoup.connect("http://jwxt.gdufe.edu.cn/jsxsd/kscj/cjcx_list");  // 获取connection
        con1.header(USER_AGENT, USER_AGENT_VALUE);   // 配置模拟浏览器
        Connection.Response login1 = con1.ignoreContentType(true).followRedirects(true).method(Connection.Method.POST)
                .data(datas).cookies(rs.cookies()).execute();
        Document d11 = Jsoup.parse(login1.body());       // 转换为Dom树
        //System.out.println(d11);
        //System.out.println(d11.select("table.Nsb_r_list.Nsb_table").html());

//        // 登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
//        Map<String, String> map = login.cookies();
//        for (String s : map.keySet()) {
//            System.out.println(s + " : " + map.get(s));
//        }

        html = d11.select("table.Nsb_r_list.Nsb_table").html();
        //Log.d("SearchCourse",html);
        System.out.print(html);
    }
}
