package com.jdpadron98.biolabapp.Main;

import com.jdpadron98.biolabapp.Contract;

import java.lang.ref.WeakReference;

public interface MainContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(MainViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void guestLogin();

        void goToLogin();

    }

    interface Model {
        String fetchData();

    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(MainState state);

        MainState getDataFromPreviousScreen();

        void enterAsGuest(boolean isguest);

        void goToLogin();


    }
}
