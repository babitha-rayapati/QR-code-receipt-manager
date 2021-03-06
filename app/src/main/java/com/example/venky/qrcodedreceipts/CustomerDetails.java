package com.example.venky.qrcodedreceipts;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CustomerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        SharedPreferences sharedPreferences=CustomerDetails.this.getSharedPreferences(getString(R.string.pref_file),MODE_PRIVATE);
        String sn=sharedPreferences.getString(getString(R.string.pref_scanname),"");

        Bundle extras = getIntent().getExtras();
        String result_text = extras.getString("result_text1");


        String[] res_str = result_text.split(";", result_text.length());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list2, res_str);

        ListView lv = (ListView) findViewById(R.id.listView2);
        lv.setAdapter(adapter);
        Toast.makeText(getApplicationContext(), sn, Toast.LENGTH_LONG).show();
    }
}
