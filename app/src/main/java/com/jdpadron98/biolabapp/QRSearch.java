package com.jdpadron98.biolabapp;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;
import com.jdpadron98.biolabapp.Profile.ProfileActivity;
import com.jdpadron98.biolabapp.Profile.ProfileContract;


import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRSearch
        extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static String TAG = QRSearch.class.getSimpleName();


    private ZXingScannerView ScannerView;
    private ProfileContract.View view;
    public String id_biolab_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this); // RegisterCallback ourselves as a handler for scan results.
        ScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        ScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    protected void onStop() {
        super.onStop();
        ScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        System.out.println(result.getText());
        setId_biolab_qr(result.getText());
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("result_qr",getId_biolab_qr());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    public String getId_biolab_qr() {
        return id_biolab_qr;
    }

    public void setId_biolab_qr(String id_biolab_qr) {
        this.id_biolab_qr = id_biolab_qr;
    }


}
