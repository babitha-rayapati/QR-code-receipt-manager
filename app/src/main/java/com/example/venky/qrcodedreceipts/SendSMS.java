package com.example.venky.qrcodedreceipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SendSMS extends AppCompatActivity {
    EditText ph,msg;
    Button send_sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        ph=(EditText)findViewById(R.id.editText5);
        msg=(EditText)findViewById(R.id.editText10);
        send_sms=(Button)findViewById(R.id.sendmsg);
        send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendsms();
            }
        });
    }
    protected  void sendsms()
    {
        String number=ph.getText().toString();
        String message=msg.getText().toString();

        try {
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(getApplicationContext(), "sent", Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "sending failed", Toast.LENGTH_LONG).show();
        }
    }
}
