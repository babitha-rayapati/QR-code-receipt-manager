package com.example.venky.qrcodedreceipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.String;
import java.util.Random;


public class signUp extends AppCompatActivity {
    EditText et_username,et_password,et_phone;
    String username,password,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        et_username=(EditText)findViewById(R.id.editText11);
        et_password=(EditText)findViewById(R.id.editText12);
        et_phone=(EditText)findViewById(R.id.editText3);
    }

    public void addinfo(View view)
    {
        username=et_username.getText().toString();
        password=et_password.getText().toString();
        phone=et_phone.getText().toString();
        //String method="signupmethod";
        String urlSuffix = "?username="+username+"&password="+password+"&phone="+phone;
        BackgroundTask backgroundTask=new BackgroundTask(this);
       // backgroundTask.execute(method,username,password,phone);
        backgroundTask.execute(urlSuffix);
        //finish();

    }
}





