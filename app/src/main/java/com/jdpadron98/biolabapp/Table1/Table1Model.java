package com.jdpadron98.biolabapp.Table1;


public class Table1Model implements Table1Contract.Model {

    public static String TAG = Table1Model.class.getSimpleName();

    public Table1Model() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
}
