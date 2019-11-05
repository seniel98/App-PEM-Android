package com.jdpadron98.biolabapp.Login;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.jdpadron98.biolabapp.Contract;

public class LoginModel implements LoginContract.Model {

    public static String TAG = LoginModel.class.getSimpleName();

    private FirebaseAuth firebaseAuth;
    private Contract repository;

    public LoginModel(Contract repository) {
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        this.repository = repository;


    }

    @Override
    public void doLogin(final String email, final String password, final Contract.LoginCallback logincallback) {
        repository.login(email, password, logincallback);
    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
}
