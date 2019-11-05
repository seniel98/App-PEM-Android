package com.jdpadron98.biolabapp.Profile;

import com.jdpadron98.biolabapp.Contract;
import com.jdpadron98.biolabapp.Table1Data;
import com.jdpadron98.biolabapp.Table2Data;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public interface ProfileContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(ProfileViewModel viewModel);

        void enableProgressBar();

        void disableProgressBar();

        String getID();

    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void logout();

        void scanQR();

        void interactWithModelQR(String qr_id_bio, String qr_id_bio_num);

        void interactWithModel();
    }

    interface Model {
        String fetchData();

        void logout(final Contract.LogoutCallback logoutCallback);

        void readData(Table1Data table1Data, Table2Data table2Data, final Contract.DataCallback dataCallback);

    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(ProfileState state);

        ProfileState getDataFromPreviousScreen();

        void logout();

        void scanQR();

        void goToTable1(String id_biolab, ArrayList<String> arrayListT1, ArrayList<String> cell_line_list,
                        ArrayList<String> gL50_list, ArrayList<String> id_exp_list);

        void displayNoDataFound();
    }
}
