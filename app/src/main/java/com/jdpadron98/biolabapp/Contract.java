package com.jdpadron98.biolabapp;


public interface Contract {


    void register(String email, String password, Contract.RegisterCallback registerCallback);

    void login(String email, String password, Contract.LoginCallback loginCallback);

    void getData(Table1Data table1Data, Table2Data table2Data, Contract.DataCallback dataCallback);

    void getID(Table1Data table1Data, Contract.getIDCallback getIDCallback);

    void logout(LogoutCallback logoutCallback);

    interface DataCallback {
        void getDataError(boolean error);

    }

    interface getIDCallback{
        void getIDError(boolean error,String userID);
    }

    interface RegisterCallback {
        void registerError(boolean error, String msg);
    }

    interface LoginCallback {
        void loginError(boolean error, String msg);
    }

    interface LogoutCallback {
        void logout(boolean error);
    }


}

