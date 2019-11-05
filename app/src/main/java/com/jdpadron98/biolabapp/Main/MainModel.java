package com.jdpadron98.biolabapp.Main;


import com.jdpadron98.biolabapp.Contract;

public class MainModel implements MainContract.Model {

    public static String TAG = MainModel.class.getSimpleName();

    private Contract repository;

    public MainModel(Contract repository) {
        this.repository = repository;
    }


    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
}
