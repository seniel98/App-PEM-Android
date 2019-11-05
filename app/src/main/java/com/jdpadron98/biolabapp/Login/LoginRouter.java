package com.jdpadron98.biolabapp.Login;


import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

import com.jdpadron98.biolabapp.Guest.GuestActivity;
import com.jdpadron98.biolabapp.Profile.ProfileActivity;
import com.jdpadron98.biolabapp.SignUp.SignUpActivity;
import com.jdpadron98.biolabapp.app.AppMediator;

public class LoginRouter implements LoginContract.Router {

    public static String TAG = LoginRouter.class.getSimpleName();

    private AppMediator mediator;

    public LoginRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(LoginState state) {
        mediator.setLoginState(state);
    }

    @Override
    public LoginState getDataFromPreviousScreen() {
        LoginState state = mediator.getLoginState();
        return state;
    }

    @Override
    public void goToRegister() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void login(String email) {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra("email", email);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void loginNotSuccesful(String msg) {
        Toast.makeText(mediator.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
