package com.jdpadron98.biolabapp.Guest;

import com.jdpadron98.biolabapp.Contract;
import com.jdpadron98.biolabapp.Table1Data;
import com.jdpadron98.biolabapp.Table2Data;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public interface GuestContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(GuestViewModel viewModel);

        String getID();

        void enableProgressBar();

        void disableProgressBar();

    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void interactWithModel();
    }

    interface Model {
        String fetchData();

        void readData(Table1Data table1Data,Table2Data table2Data, final Contract.DataCallback dataCallback);


    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(GuestState state);

        GuestState getDataFromPreviousScreen();

        void goToTable1(String id_biolab, ArrayList<String> arrayListT1, ArrayList<String> cell_line_list,
                        ArrayList<String> gL50_list, ArrayList<String> id_exp_list);

        void displayNoDataFound();
    }
}
