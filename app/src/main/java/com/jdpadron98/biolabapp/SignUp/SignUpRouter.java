package com.jdpadron98.biolabapp.SignUp;

import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

import com.jdpadron98.biolabapp.app.AppMediator;

public class SignUpRouter implements SignUpContract.Router {

    public static String TAG = SignUpRouter.class.getSimpleName();

    private AppMediator mediator;

    public SignUpRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(SignUpState state) {
        mediator.setSignUpState(state);
    }

    @Override
    public SignUpState getDataFromPreviousScreen() {
        SignUpState state = mediator.getSignUpState();
        return state;
    }

    @Override
    public void notRegistered(String msg) {
        Toast.makeText(mediator.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goLogin(String msg) {
        Toast.makeText(mediator.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
