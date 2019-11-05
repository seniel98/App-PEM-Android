package com.jdpadron98.biolabapp.Login;

import com.jdpadron98.biolabapp.Contract;

import java.lang.ref.WeakReference;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(LoginViewModel viewModel);

        String getEmail();

        String getPassword();

        void enableProgressBar();

        void disableProgressBar();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void goToRegister();

        void login();
    }

    interface Model {
        String fetchData();

        void doLogin(String email, String password, Contract.LoginCallback logincallback);
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(LoginState state);

        LoginState getDataFromPreviousScreen();

        void goToRegister();

        void login(String email);

        void loginNotSuccesful(String msg);
    }
}
