package com.jdpadron98.biolabapp.SignUp;

import com.jdpadron98.biolabapp.Contract;

import java.lang.ref.WeakReference;

public interface SignUpContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(SignUpViewModel viewModel);

        void enableProgressBar();

        void disableProgressBar();

        String getEmail();

        String getPassword();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void interactWithModel();

        void fetchData();
    }

    interface Model {
        String fetchData();

        void register(String email, String password, Contract.RegisterCallback registerCallback);
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(SignUpState state);

        SignUpState getDataFromPreviousScreen();

        void notRegistered(String msg);

        void goLogin(String msg);
    }
}
