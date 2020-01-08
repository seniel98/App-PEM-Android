package com.jdpadron98.biolabapp.SignUp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.jdpadron98.biolabapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity
        extends AppCompatActivity implements SignUpContract.View {

    public static String TAG = SignUpActivity.class.getSimpleName();

    private ProgressBar progressBar;

    private TextView emailtv, passwordtv, repeatpassowrdtv;

    private String email,password;

    private SignUpContract.Presenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressBar = findViewById(R.id.progressBar2);
        emailtv = findViewById(R.id.email_tv);
        passwordtv = findViewById(R.id.password_tv);
        repeatpassowrdtv = findViewById(R.id.repeatpassword);

        // do the setup
        SignUpScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        disableProgressBar();

    }
    @Override
    public void displayData(SignUpViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    public void register(View view) {
        if (validateForm()) {
            enableProgressBar();
            presenter.interactWithModel();
            onBackPressed();
        }
    }

    /**
     * Method that validates the data inserted for the register
     *
     * @return true if the form is correct or false if it is incorrect filled
     */

    private boolean validateForm() {
        boolean valid = true;

        email = emailtv.getText().toString().trim();
        if (!isEmailValid(email)) {
            emailtv.setError("Email not vaild");
            valid = false;
        } else {
            emailtv.setError(null);
        }
        password = passwordtv.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            passwordtv.setError("Required.");
            valid = false;
        } else if (!isPasswordValid(password)) {
            passwordtv.setError("Password must be 8 characters long and contain lower case,upper case and number");
            valid = false;
        } else {
            passwordtv.setError(null);
        }
        String reppassword = repeatpassowrdtv.getText().toString().trim();
        if (TextUtils.isEmpty(reppassword)) {
            repeatpassowrdtv.setError("Required.");
            valid = false;
        } else if (!reppassword.equals(password)) {
            repeatpassowrdtv.setError("Passwords doesn´t match");
            valid = false;
        } else {
            repeatpassowrdtv.setError(null);
        }
        if (valid) {
            System.out.println(("Is Valid!"));
        }

        return valid;
    }

    /**
     * Method that checks if the format of email is correct
     *
     * @param email gets the email. This must be nnnn@nnnn.nn to be okay
     * @return true if the format matches with the standard or false if not
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Method that checks if the password has at least 8 characters and at least 1 lower case
     * letter, an upper case letter and a number
     *
     * @param password The password that is inserted
     * @return true if its valid or false if not
     */
    public static boolean isPasswordValid(String password) {
        boolean valid = true;
        //Reg ex that checks if password is alphanumeric
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        /**
         * ^                  # start-of-string
         * (?=.*[0-9])       # a digit must occur at least once
         * (?=.*[a-z])       # a lower case letter must occur at least once
         * (?=.*[A-Z])       # an upper case letter must occur at least once
         * (?=\S+$)          # no whitespace allowed in the entire string
         * .{8,}             # anything, at least eight places though
         * $                 # end-of-string
         * */

        //Checks to see if password matches with our regular expression .
        if (!password.matches(pattern)) {
            valid = false;
        }
        return valid;
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
    public String getEmail() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void injectPresenter(SignUpContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
