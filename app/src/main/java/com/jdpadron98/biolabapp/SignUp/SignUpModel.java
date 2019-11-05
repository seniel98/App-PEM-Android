package com.jdpadron98.biolabapp.SignUp;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jdpadron98.biolabapp.Contract;

public class SignUpModel implements SignUpContract.Model {

    public static String TAG = SignUpModel.class.getSimpleName();

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Contract repository;

    public SignUpModel(Contract repository) {
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        this.repository = repository;

    }

    @Override
    public void register(final String email, final String password, final Contract.RegisterCallback registerCallback) {
        repository.register(email, password, registerCallback);

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
}
