package com.example.liangweibin.study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SchoolLoginActivity extends AppCompatActivity {

    private Button reset;
    private  Button login;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private String username;
    private String password;

    //记住密码功能
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_login);
        reset = (Button) findViewById(R.id.reset);
        login = (Button) findViewById(R.id.login);
        usernameEdit = (EditText) findViewById(R.id.usernameEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);


        //记住密码功能
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            username = pref.getString("username","");
            password = pref.getString("password","");
            usernameEdit.setText(username);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        //重置按钮
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEdit.setText("");
                passwordEdit.setText("");
            }
        });

        //登陆按钮
       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               username = usernameEdit.getText().toString();
               password =  passwordEdit.getText().toString();
               if(username.equals("") || password.equals("")){
                   Toast.makeText(SchoolLoginActivity.this,"账号或者密码为空！！！",Toast.LENGTH_SHORT).show();
               }else{
                   Intent intent = new Intent(SchoolLoginActivity.this, LoginActivity.class);
                   intent.putExtra("username", username);
                   intent.putExtra("password", password);
                   startActivity(intent);
                   //Toast.makeText(SchoolLoginActivity.this,"登陆成功！！！",Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}
