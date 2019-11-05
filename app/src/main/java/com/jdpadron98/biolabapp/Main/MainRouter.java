package com.jdpadron98.biolabapp.Main;

import android.content.Intent;
import android.content.Context;

import com.jdpadron98.biolabapp.Login.LoginActivity;
import com.jdpadron98.biolabapp.Guest.GuestActivity;
import com.jdpadron98.biolabapp.Profile.ProfileActivity;
import com.jdpadron98.biolabapp.app.AppMediator;

public class MainRouter implements MainContract.Router {

    public static String TAG = MainRouter.class.getSimpleName();

    private AppMediator mediator;

    public MainRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(MainState state) {
        mediator.setMainState(state);
    }

    @Override
    public MainState getDataFromPreviousScreen() {
        MainState state = mediator.getMainState();
        return state;
    }

    @Override
    public void enterAsGuest(boolean isguest) {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, GuestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("isguest", isguest);
        context.startActivity(intent);
    }

    @Override
    public void goToLogin() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
