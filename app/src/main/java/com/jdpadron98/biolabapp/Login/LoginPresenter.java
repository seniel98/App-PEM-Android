package com.jdpadron98.biolabapp.Login;

import com.jdpadron98.biolabapp.Contract;

import java.lang.ref.WeakReference;

public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = LoginPresenter.class.getSimpleName();

    private WeakReference<LoginContract.View> view;
    private LoginViewModel viewModel;
    private LoginContract.Model model;
    private LoginContract.Router router;

    public LoginPresenter(LoginState state) {
        viewModel = state;
    }

    @Override
    public void fetchData() {
      /*
        // Log.e(TAG, "fetchData()");

        // use passed state if is necessary
        LoginState state = router.getDataFromPreviousScreen();
        if (state != null) {

            // update view and model state
            viewModel.data = state.data;

            // update the view
            view.get().displayData(viewModel);

            return;
        }

        // call the model  
        String data = model.fetchData();

        // set view state
        viewModel.data = data;

        // update the view
        view.get().displayData(viewModel);
*/
    }

    @Override
    public void goToRegister() {
        router.goToRegister();
    }

    @Override
    public void login() {
        model.doLogin(view.get().getEmail(), view.get().getPassword(), new Contract.LoginCallback() {
            @Override
            public void loginError(boolean error, String msg) {
                if (!error) {
                    router.login(view.get().getEmail());
                } else {
                    router.loginNotSuccesful(msg);
                    view.get().disableProgressBar();
                }
            }
        });

    }

    @Override
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(LoginContract.Router router) {
        this.router = router;
    }
}
