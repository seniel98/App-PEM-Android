package com.jdpadron98.biolabapp.Table1;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public interface Table1Contract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(Table1ViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();


        void interactWithModel();

        String getId_biolab();

        void setId_biolab(String id_biolab);

        ArrayList<String> getCell_line_list();

        void setCell_line_list(ArrayList<String> cell_line_list);

        ArrayList<String> getgL50_list();

        void setgL50_list(ArrayList<String> gL50_list);

        ArrayList<String> getId_exp_list();

        void setId_exp_list(ArrayList<String> id_exp_list);
    }

    interface Model {
        String fetchData();
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(Table1State state);

        Table1State getDataFromPreviousScreen();

        void goToTable2(String id_biolab, ArrayList<String> cell_line_list, ArrayList<String> gL50_list, ArrayList<String> id_exp_list);
    }
}
