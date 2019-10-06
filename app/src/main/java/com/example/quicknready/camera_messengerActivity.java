package com.example.quicknready;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import android.os.Bundle;

import java.util.ArrayList;

public class camera_messengerActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_messenger);

        Button cameraBtn = findViewById(R.id.camera);

        //on click changes to QR Scanner
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQR_activity();
            }
        });


    }

    public void open_chat(View view){
        Intent intent = new Intent(this, chatActivity.class);
        startActivity(intent);
    }
    public void open_chat(View view) {
        Intent intent = new Intent(this, chatActivity.class);
        startActivity(intent);
    }

    public void openQR_activity(){
        Intent intent = new Intent(this, QR_Scan.class);
        startActivity(intent);
    }

}
