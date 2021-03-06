package com.example.venky.qrcodedreceipts;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class scanCustomers extends AppCompatActivity {
    private Button scan_btn;
    static String scan_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_customers);
        scan_btn=(Button)findViewById(R.id.scan_cus);
        final Activity activity=this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator=new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents()==null)
            {
                Toast.makeText(this,"You cancelled the Scan",Toast.LENGTH_LONG).show();
            }
            else
            {
                String scan_text=result.getContents();
                String urlSuffix="?username="+scan_text;
               /* BackgroundTaskScanCustomer backgroundTaskScanCustomer=new BackgroundTaskScanCustomer(this);
                backgroundTaskScanCustomer.execute(urlSuffix);
                //finish();*/

                BackgroundTask1 backgroundTask1=new BackgroundTask1(this);
                backgroundTask1.execute(urlSuffix);

               // Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                //scan_name=result.getContents();
                SharedPreferences sharedPreferences=scanCustomers.this.getSharedPreferences(getString(R.string.pref_file),MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(getString(R.string.pref_scanname),result.getContents());
                editor.commit();


            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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
            Intent intent = new Intent(scanCustomers.this, CustomerDetails.class);
            intent.putExtra("result_text1",result);
            startActivity(intent);

        }

    }

}
