package com.example.quicknready;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QR_Scan extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;
    private String redLobster = "Red Lobster";
    SurfaceView cameraView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr__scan);
        if (checkPermission()){
            //main logic or main code

            // . write your main code to execute, It will execute if the permission is already given.

        } else {
            requestPermission();
        }


        //initialize the camera, text and view
        cameraView = (SurfaceView) findViewById(R.id.cameraPreview);
        textView = (TextView) findViewById(R.id.cameraText);

        //set barcode to detect QR codes
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();

        //puts barcode detector into camera source, and builds the resolution
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640,480).build();



        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //checks for permission
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest
                .permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                //try to start camera source, but catches error if output isn't given/no permission
                try {
                    cameraSource.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //stops camera if holder is terminated
                cameraSource.stop();
            }
        });

        //barcode API detects processor array using object Barcode
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                //to hold specific individual values
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                //if array isn't empty
                if(qrCodes.size()!=0){
                    textView.post(new Runnable() {
                        @Override
                        //vibrates device when there is a value in array aka found QR
                        public void run() {
                            Vibrator vibrate = (Vibrator) getApplicationContext()
                                    .getSystemService(Context.VIBRATOR_SERVICE);
                            //vibrate for 1 second
                            vibrate.vibrate(1000);
                            //display decoded QR text
                            textView.setText(qrCodes.valueAt(0).displayValue);

                            //checks for decrypted QR code to match menu and switch over to menu view
                            if(textView.getText().toString().compareTo(redLobster)==0){
                                Intent intent = new Intent(QR_Scan.this, MenuActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }

    //method to check permission for camera
    private boolean checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    //method to request permission for camera
    private void requestPermission(){

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }
}
