package com.example.liangweibin.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class TranslationActivity extends AppCompatActivity {
    Button confirm;
    Button reset;
    EditText unknow_content;
    EditText show_translate;

    String unkonw_ct;
    String konw_answer;

    Document doc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);

        confirm = (Button) findViewById(R.id.confirmButton);
        reset = (Button)findViewById(R.id.reset);
        unknow_content = (EditText) findViewById(R.id.unknow_content);
        show_translate = (EditText)findViewById(R.id.show_translate);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unknow_content.setText("");
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unkonw_ct = unknow_content.getText().toString();
                //Toast.makeText(TranslationActivity.this,unkonw_ct,Toast.LENGTH_SHORT).show();
                GetTranslation(unkonw_ct);

            }
        });
    }

    public void GetTranslation(String unkonwWord) {
        final String urlGet = "http://dict-co.iciba.com/search.php?word=" + unkonwWord + "&submit=%E6%8F%90%E4%BA%A4";

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL(urlGet);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    if(reader != null){
                        try {
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection != null)
                        connection.disconnect();
                }
            }
        }).start();

    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Document doc = Jsoup.parse(response);
                String output1 = doc.text();
                String output2 = output1.replace("爱词霸 翻译","");
                String output3 = output2.replace("返回查词首页","");
                show_translate.setText(output3);
            }
        });
    }

}


