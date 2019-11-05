package com.jdpadron98.biolabapp.Main;


import com.jdpadron98.biolabapp.Contract;

import java.lang.ref.WeakReference;

public class MainPresenter implements MainContract.Presenter {

    public static String TAG = MainPresenter.class.getSimpleName();

    private WeakReference<MainContract.View> view;
    private MainViewModel viewModel;
    private MainContract.Model model;
    private MainContract.Router router;

    public MainPresenter(MainState state) {
        viewModel = state;
    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");
/*
        // use passed state if is necessary
        MainState state = router.getDataFromPreviousScreen();
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
    public void guestLogin() {
        router.enterAsGuest(true);
    }

    @Override
    public void goToLogin() {
        router.goToLogin();
    }

    @Override
    public void injectView(WeakReference<MainContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MainContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(MainContract.Router router) {
        this.router = router;
    }
}
