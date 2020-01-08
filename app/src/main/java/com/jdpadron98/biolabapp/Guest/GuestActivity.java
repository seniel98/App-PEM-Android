package com.jdpadron98.biolabapp.Guest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jdpadron98.biolabapp.R;


public class GuestActivity
        extends AppCompatActivity implements GuestContract.View {

    public static String TAG = GuestActivity.class.getSimpleName();

    private GuestContract.Presenter presenter;
    private TextView tv;
    private boolean guestLogin;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        tv = findViewById(R.id.txt_field_id);
        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        guestLogin = intent.getBooleanExtra("isguest", false);


        // do the setup
        GuestScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        disableProgressBar();
        // load the data
        //presenter.fetchData();
    }

    /**
     *
     * @param view
     */
    public void next(View view) {
        if (getID().length() != 4) {
            tv.setError("ID must be 4 characters long");
        } else if (getID().contains(".") || getID().contains("#") || getID().contains("$") || getID().contains("[") || getID().contains("]")) {
            //Firebase Database paths must not contain '.', '#', '$', '[', or ']'
            tv.setError("ID must not contain '.', '#', '$', '[', or ']'");
        } else {
            enableProgressBar();
            presenter.callGetId();
            presenter.callReadData();
        }
    }

    /**
     *
     * @return
     */
    public String getID() {
        String id = tv.getText().toString().toUpperCase();
        return id;
    }

    public void enableProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void disableProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void injectPresenter(GuestContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
