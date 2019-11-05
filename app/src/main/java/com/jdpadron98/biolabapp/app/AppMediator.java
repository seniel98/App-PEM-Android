package com.jdpadron98.biolabapp.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.jdpadron98.biolabapp.Login.LoginState;
import com.jdpadron98.biolabapp.Main.MainActivity;
import com.jdpadron98.biolabapp.Main.MainState;
import com.jdpadron98.biolabapp.Guest.GuestState;
import com.jdpadron98.biolabapp.Profile.ProfileActivity;
import com.jdpadron98.biolabapp.Profile.ProfileState;
import com.jdpadron98.biolabapp.SignUp.SignUpState;
import com.jdpadron98.biolabapp.Table1.Table1State;

public class AppMediator extends Application {

    private ProfileState profileState;
    private GuestState guestState;
    private LoginState loginState;
    private SignUpState signUpState;
    private MainState mainState;
    private Table1State table1State;


    public GuestState getGuestState() {
        return guestState;
    }

    public void setGuestState(GuestState guestState) {
        this.guestState = guestState;
    }

    public Table1State getTable1State() {
        return table1State;
    }

    public void setTable1State(Table1State table1State) {
        this.table1State = table1State;
    }

    public MainState getMainState() {
        return mainState;
    }

    public void setMainState(MainState mainState) {
        this.mainState = mainState;
    }

    public LoginState getLoginState() {
        return loginState;
    }

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    public SignUpState getSignUpState() {
        return signUpState;
    }

    public void setSignUpState(SignUpState signUpState) {
        this.signUpState = signUpState;
    }

    public ProfileState getProfileState() {
        return profileState;
    }

    public void setProfileState(ProfileState profileState) {
        this.profileState = profileState;
    }


    /**
     * Here we override this method to check if there is a user logged in or not.
     */

    @Override
    public void onCreate() {
        super.onCreate();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Context context = getApplicationContext();
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            Context context = getApplicationContext();
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }


}
