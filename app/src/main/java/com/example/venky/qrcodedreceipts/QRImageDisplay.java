package com.example.venky.qrcodedreceipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import android.graphics.Bitmap;

public class QRImageDisplay extends AppCompatActivity {
    Button gen_btn;
    ImageView image;
    String text2Qr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrimage_display);
        Bundle extras = getIntent().getExtras();
        String username= extras.getString("username");
        String category= extras.getString("category");
        String productname=extras.getString("productname");
        String price= extras.getString("price");
        String date= extras.getString("date");
        final String tot=username+";"+category+";"+productname+";"+price+";"+date;

        gen_btn=(Button)findViewById(R.id.buttondisplay);
        image=(ImageView)findViewById(R.id.imagedisplay);
        gen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //text2Qr=text.getText().toString().trim();
                MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix=multiFormatWriter.encode(tot, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                    Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                }
                catch (WriterException e)
                {
                    e.printStackTrace();
                }
            }
        });


    }
}
