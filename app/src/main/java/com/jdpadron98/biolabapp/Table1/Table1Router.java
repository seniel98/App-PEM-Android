package com.jdpadron98.biolabapp.Table1;

import android.content.Intent;
import android.content.Context;

import com.jdpadron98.biolabapp.app.AppMediator;

import java.util.ArrayList;

public class Table1Router implements Table1Contract.Router {

    public static String TAG = Table1Router.class.getSimpleName();

    private AppMediator mediator;

    public Table1Router(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, Table1Activity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(Table1State state) {
        mediator.setTable1State(state);
    }

    @Override
    public Table1State getDataFromPreviousScreen() {
        Table1State state = mediator.getTable1State();
        return state;
    }


    @Override
    public void goToTable2(String id_biolab, ArrayList<String> cell_line_list, ArrayList<String> gL50_list, ArrayList<String> id_exp_list) {
    }
}
