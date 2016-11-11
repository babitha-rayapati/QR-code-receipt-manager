package com.example.venky.qrcodedreceipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class retailer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer);
    }
    public void generate_receipts(View v)
    {
        Intent i1=new Intent(retailer.this,ReceiptGeneration.class);
        startActivity(i1);
    }

    public void signout(View v)
    {
        Intent i3=new Intent(retailer.this,login.class);
        startActivity(i3);
    }
    public void scan_cust(View view)
    {
        Intent intent=new Intent(retailer.this,scanCustomers.class);
        startActivity(intent);
    }
    public void send_sms(View view)
    {
        Intent intent=new Intent(retailer.this,SendSMS.class);
        startActivity(intent);
    }

}
