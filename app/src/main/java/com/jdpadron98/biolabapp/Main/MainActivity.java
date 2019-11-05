package com.jdpadron98.biolabapp.Main;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jdpadron98.biolabapp.ExitDialog;
import com.jdpadron98.biolabapp.R;

public class MainActivity
        extends AppCompatActivity implements MainContract.View {

    public static String TAG = MainActivity.class.getSimpleName();

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // do the setup
        MainScreen.configure(this);



    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        //presenter.fetchData();
    }

    @Override
    public void displayData(MainViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    public void guestClick(View view){
        presenter.guestLogin();
    }

    public void userClick(View view){
        presenter.goToLogin();
    }

    @Override
    public void injectPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onBackPressed() {
        ExitDialog exitDialog = new ExitDialog();
        exitDialog.show(getSupportFragmentManager(), "exit dialog");
    }
}
