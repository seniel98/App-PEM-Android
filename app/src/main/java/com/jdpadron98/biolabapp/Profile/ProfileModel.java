package com.jdpadron98.biolabapp.Profile;


import com.jdpadron98.biolabapp.Contract;
import com.jdpadron98.biolabapp.Table1Data;
import com.jdpadron98.biolabapp.Table2Data;

public class ProfileModel implements ProfileContract.Model {

    public static String TAG = ProfileModel.class.getSimpleName();

    private Contract repository;

    public ProfileModel(Contract repository) {
        this.repository = repository;
    }

    /**
     *
     * @param table1Data
     * @param table2Data
     * @param dataCallback
     */
    @Override
    public void readData(Table1Data table1Data, Table2Data table2Data, final Contract.DataCallback dataCallback) {
        repository.getID(table1Data,dataCallback);
        repository.getData(table1Data, table2Data, dataCallback);
    }

    @Override
    public void logout(final Contract.LogoutCallback logoutCallback) {
        repository.logout(logoutCallback);
    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
}
