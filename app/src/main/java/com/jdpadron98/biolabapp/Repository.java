package com.jdpadron98.biolabapp;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Repository implements Contract {

    private static Repository INSTANCE;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Context context;
    private DatabaseReference databaseReference;
    private DatabaseReference usersRef;
    private DatabaseReference rootRef;
    private DatabaseReference id_prodRef;
    private int x = 1;
    private ArrayList<String> cell_line_list = new ArrayList<>();
    private ArrayList<String> gL50_list = new ArrayList<>();
    private ArrayList<String> id_exp_list = new ArrayList<>();


    public static Contract getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(context);
        }
        return INSTANCE;
    }

    private Repository(Context context) {

        databaseReference = FirebaseDatabase.getInstance().getReference(); // DB reference

        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();

        usersRef = databaseReference.child("users");

        rootRef = databaseReference.child("products");

        this.context = context;

    }

    /**
     * Method that registers a user in our application
     * @param email
     * @param password
     * @param registerCallback
     */
    public void register(final String email, final String password, final Contract.RegisterCallback registerCallback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            usersRef.child(auth.getCurrentUser().getUid()).child("email").setValue(email);
                            usersRef.child(auth.getCurrentUser().getUid()).child("id");
                            registerCallback.registerError(false, "Registered succesfully");
                        } else {
                            registerCallback.registerError(true, task.getException().getMessage());
                        }
                    }
                });
    }

    /**
     * Method that LogIn a user in our application
     * @param email
     * @param password
     * @param loginCallback
     */
    public void login(String email, String password, final Contract.LoginCallback loginCallback) {
        auth.signInWithEmailAndPassword(email,
                password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginCallback.loginError(false, "Login successfully");
                        } else {
                            loginCallback.loginError(true, task.getException().getMessage());
                        }
                    }
                });
    }

    /**
     * Method that retrieves the data from Firebase depending on the ID and the add it to the variables
     * of the class Table1Data and Table2Data
     * @param table1Data
     * @param table2Data
     * @param dataCallback
     */
    public void getData(final Table1Data table1Data, final Table2Data table2Data, final Contract.DataCallback dataCallback) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Here we check if the values of the db are null
                if (dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                        .child("id_sample").getValue(String.class) != null &&
                        dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                                .child("smiles").getValue(String.class) != null &&
                        dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                                .child("soluble").getValue(String.class) != null &&
                        dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                                .child("notes").getValue(String.class) != null) {

                    table1Data.setId_sample(dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                            .child("id_sample").getValue(String.class));
                    table1Data.setSmiles(dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                            .child("smiles").getValue(String.class));
                    table1Data.setSoluble(dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                            .child("soluble").getValue(String.class));
                    table1Data.setNotes(dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                            .child("notes").getValue(String.class));

                    while (dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                            .child("cell_line_" + x).getValue(String.class) != null &&
                            dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                                    .child("gL50_" + x).getValue(String.class) != null &&
                            dataSnapshot.child(table1Data.getUserIdBiolab()).child(table1Data.getNumIdBiolab())
                                    .child("id_exp_" + x).getValue(String.class) != null) {

                        cell_line_list.add(dataSnapshot.child(table1Data.getUserIdBiolab()).
                                child(table1Data.getNumIdBiolab()).child("cell_line_" + x).getValue(String.class));
                        gL50_list.add(dataSnapshot.child(table1Data.getUserIdBiolab())
                                .child(table1Data.getNumIdBiolab()).child("gL50_" + x).getValue(String.class));
                        id_exp_list.add(dataSnapshot.child(table1Data.getUserIdBiolab())
                                .child(table1Data.getNumIdBiolab()).child("id_exp_" + x).getValue(String.class));
                        x++;
                    }
                    table2Data.setCell_line_list(cell_line_list);
                    table2Data.setgL50_list(gL50_list);
                    table2Data.setId_exp_list(id_exp_list);
                    dataCallback.getDataError(false);
                } else {
                    dataCallback.getDataError(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        rootRef.addListenerForSingleValueEvent(valueEventListener);

    }

    /**
     * Method that retrieves the ID depending of the user or if the user is logged in or not
     * @param table1Data
     * @param getIDCallback
     */
    public void getID(final Table1Data table1Data, final Contract.getIDCallback getIDCallback) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (auth.getCurrentUser() != null) {
                    //There is an active session
                    if (dataSnapshot.child(auth.getCurrentUser().getUid()).child("id").getValue(String.class) == null) {
                        getIDCallback.getIDError(true,"");
                    } else {
                        String userID = dataSnapshot.child(auth.getCurrentUser().getUid()).child("id").getValue(String.class);
                        getIDCallback.getIDError(false,userID);
                    }

                } else {
                    //There is no active session (GUEST MODE)
                    String userID = dataSnapshot.child("guest").child("id").getValue(String.class);
                    System.out.println(table1Data.getUserIdBiolab());
                    getIDCallback.getIDError(false,userID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        usersRef.addValueEventListener(valueEventListener);
    }

    /**
     * Method that close the current session of the user
     * @param logoutCallback
     */
    public void logout(LogoutCallback logoutCallback) {
        if (user != null) {
            //If there is an active session you can logout
            FirebaseAuth.getInstance().signOut();
            user = null;
            logoutCallback.logout(false);
        } else {
            logoutCallback.logout(true);
        }

    }
}
