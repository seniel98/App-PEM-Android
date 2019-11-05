package com.jdpadron98.biolabapp.Profile;

import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

import com.jdpadron98.biolabapp.Main.MainActivity;
import com.jdpadron98.biolabapp.QRSearch;
import com.jdpadron98.biolabapp.Table1.Table1Activity;
import com.jdpadron98.biolabapp.app.AppMediator;

import java.util.ArrayList;

public class ProfileRouter implements ProfileContract.Router {

    public static String TAG = ProfileRouter.class.getSimpleName();

    private AppMediator mediator;

    public ProfileRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(ProfileState state) {
        mediator.setProfileState(state);
    }

    @Override
    public ProfileState getDataFromPreviousScreen() {
        ProfileState state = mediator.getProfileState();
        return state;
    }

    @Override
    public void logout() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void scanQR(){
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, QRSearch.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    @Override
    public void goToTable1(String id_biolab, ArrayList<String> arrayListT1, ArrayList<String> cell_line_list,
                           ArrayList<String> gL50_list, ArrayList<String> id_exp_list) {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, Table1Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("id_biolab",id_biolab);
        intent.putExtra("T1Data", arrayListT1);
        intent.putExtra("cell_line_list", cell_line_list);
        intent.putExtra("gL50_list", gL50_list);
        intent.putExtra("id_exp_list", id_exp_list);
        context.startActivity(intent);
    }

    @Override
    public void displayNoDataFound() {
        Context context = mediator.getApplicationContext();
        Toast.makeText(context, "No data found for that ID", Toast.LENGTH_SHORT).show();


    }
}
