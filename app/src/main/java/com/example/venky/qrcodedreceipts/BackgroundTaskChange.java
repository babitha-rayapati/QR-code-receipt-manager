package com.example.venky.qrcodedreceipts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by venky on 04-11-2016.
 */
public class BackgroundTaskChange extends AsyncTask<String,Void,String> {

    private Context ctx;
    ProgressDialog loading;
    private static final String REGISTER_URL ="http://cabvit.esy.es/updatepasswordBabi.php";
    public BackgroundTaskChange(Context ctx)
    {
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(ctx, "Please Wait",null, true, true);
    }

    @Override
    protected String doInBackground(String... params) {

        String s = params[0];
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(REGISTER_URL+s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            return sb.toString();
        }
        catch(Exception e)
        {
            return e.toString();

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        loading.dismiss();
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }

}
