package com.example.venky.qrcodedreceipts;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Intent;


public class Customer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
    }
    public void show_MyQR(View v)
    {
        Intent i4=new Intent(Customer.this,MyQR.class);
        startActivity(i4);
    }
    public void scan_receipt(View v)
    {
        Intent i5=new Intent(Customer.this,ScanReceipts.class);
        startActivity(i5);
    }

    public void update_password(View v)
    {
        Intent i2=new Intent(Customer.this,ChangePassword.class);
        startActivity(i2);
    }

    public void prvw(View v)
    {
        Intent i2=new Intent(Customer.this,ScanPreview.class);
        startActivity(i2);
    }

    public void logout_meth(View view)
    {
        /*SharedPreferences sharedPreferences=Customer.this.getSharedPreferences(getString(R.string.pref_file),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();*/

        Intent intent=new Intent(Customer.this,login.class);
        startActivity(intent);
    }

    public void searchUsing(View v)
    {
        String un;
        SharedPreferences sharedPreferences=Customer.this.getSharedPreferences(getString(R.string.pref_file),MODE_PRIVATE);
        un=sharedPreferences.getString(getString(R.string.pref_username),"");
        String urls="?username="+un;
        new BackgroundTask1(this).execute(urls);


        /*Intent intent=new Intent(Customer.this,searchActivity.class);
        startActivity(intent);*/
    }


    public class BackgroundTask1 extends AsyncTask<String,Void,String> {

        private Context context;
        ProgressDialog loading;
        String resultnew="";
        public BackgroundTask1(Context cxt)
        {
            context=cxt;
        }
        private static final String GETDATA_URL="http://cabvit.esy.es/retrieveproductaBabi.php";

        @Override
        protected void onPreExecute() {
// TODO Auto-generated method stub
            super.onPreExecute();
            loading=ProgressDialog.show(context, "Please Wait","Loading", true, true);

        }
        @Override
        protected String doInBackground(String... arg0) {
// TODO Auto-generated method stub
            BufferedReader br=null;
            StringBuffer sb;
            String s=arg0[0];
            try
            {

                URL url=new URL(GETDATA_URL+s);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                sb=new StringBuffer();
                while((line=br.readLine())!=null)
                {
                    sb.append(line+"\n");
                }
            }
            catch (Exception e) {
// TODO: handle exception
                return e.toString();
            }
            return sb.toString().trim();

        }
        @Override
        protected void onProgressUpdate(Void... values) {
// TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
// TODO Auto-generated method stub
            super.onPostExecute(result);
            loading.dismiss();
            Intent intent = new Intent(Customer.this, CustometList.class);
            intent.putExtra("result_text",result);
            startActivity(intent);

        }

    }

}
