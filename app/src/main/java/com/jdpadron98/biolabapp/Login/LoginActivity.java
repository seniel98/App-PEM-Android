package com.jdpadron98.biolabapp.Login;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jdpadron98.biolabapp.R;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    private TextView emailtv, passwordtv;
    private ProgressBar progressBar;

    private String email, password;

    private LoginContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailtv = findViewById(R.id.email_tv);
        passwordtv = findViewById(R.id.password_tv);
        progressBar = findViewById(R.id.progressBar3);


        // do the setup
        LoginScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        disableProgressBar();

        // load the data
        //presenter.fetchData();
    }

    @Override
    public void displayData(LoginViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    public void clickLogin(View view) {
        email = emailtv.getText().toString();
        password = passwordtv.getText().toString();

        if (TextUtils.isEmpty(emailtv.getText().toString())) {
            emailtv.setError("Email must not be empty");
        } else if (TextUtils.isEmpty(getPassword())) {
            emailtv.setError("Password must not be empty");
        } else {
            enableProgressBar();
            presenter.login();

        }

    }

    public void clickRegister(View view) {
        presenter.goToRegister();
    }

    @Override
    public void enableProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableProgressBar(){
        progressBar.setVisibility(View.GONE);
    }
    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }


}
