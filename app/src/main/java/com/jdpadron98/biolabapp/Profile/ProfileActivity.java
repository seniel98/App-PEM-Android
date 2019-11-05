package com.jdpadron98.biolabapp.Profile;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jdpadron98.biolabapp.ExitDialog;
import com.jdpadron98.biolabapp.R;

public class ProfileActivity
        extends AppCompatActivity implements ProfileContract.View {

    public static String TAG = ProfileActivity.class.getSimpleName();

    private TextView usernametv, txt_field;

    private String id_biolab;
    private String qr_id;
    private int REQUEST_CAMERA = 1;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private ProfileContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        progressBar = findViewById(R.id.progressBarPr);

        usernametv = findViewById(R.id.usernametv);
        txt_field = findViewById(R.id.txt_field_id);
        usernametv.setText(firebaseUser.getEmail());

        Intent intent = getIntent();
        qr_id = intent.getStringExtra("result_qr");

        // do the setup
        ProfileScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        disableProgressBar();
        if (qr_id != null) {
            enableProgressBar();
            String qr_id_bio = "";
            String qr_id_bio_num = "";
            for (int i = 0; i < 7; i++) {
                if (i < 3) {
                    qr_id_bio = qr_id_bio + qr_id.charAt(i);
                } else {
                    qr_id_bio_num = qr_id_bio_num + qr_id.charAt(i);
                }
            }
            presenter.interactWithModelQR(qr_id_bio, qr_id_bio_num);
            setQr_id(null);
        }

        // load the data
        //presenter.fetchData();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // load the data
        //presenter.fetchData();
    }


    @Override
    public void displayData(ProfileViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    public void clickLogout(View view) {
        presenter.logout();
    }

    @Override
    public void onBackPressed() {
        ExitDialog exitDialog = new ExitDialog();
        exitDialog.show(getSupportFragmentManager(), "exit dialog");
    }

    /**
     * @param view
     */
    public void next(View view) {
        if (getID().length() != 4) {
            txt_field.setError("ID must be 4 characters long");
        } else if (getID().contains(".") || getID().contains("#") || getID().contains("$") ||
                getID().contains("[") || getID().contains("]")) {
            //Firebase Database paths must not contain '.', '#', '$', '[', or ']'
            txt_field.setError("ID must not contain '.', '#', '$', '[', or ']'");
           } else {
            enableProgressBar();
            presenter.interactWithModel();
        }
    }

    /**
     *
     * @param view
     */
    public void scanQR(View view) {
        //Condition that checks if permission is allowed or not
        if (ContextCompat.checkSelfPermission(ProfileActivity.this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            presenter.scanQR();
        } else {
            requestCameraPermission();
        }

    }

    @Override
    public void enableProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void setQr_id(String qr_id) {
        this.qr_id = qr_id;
    }

    @Override
    public String getID() {
        String id = txt_field.getText().toString().trim();
        return id;
    }

    /**
     * Method that makes an Alert dialog on the app in order to request access permission to the
     * camera.
     * THIS METHOD IS ONLY EXECUTED UNTIL THE PERMISSION IS GRANTED.
     */
    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ProfileActivity.this,
                                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    /**
     * Method that checks if the permission was Granted or not. If it is then we go to the presenter.
     * THIS METHOD IS ONLY EXECUTED UNTIL THE PERMISSION IS GRANTED OR THE FIRST TIME IS GRANTED.
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                presenter.scanQR();
            } else {
                //Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void injectPresenter(ProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }


}
