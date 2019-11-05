package com.jdpadron98.biolabapp.Main;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;


import com.jdpadron98.biolabapp.Contract;
import com.jdpadron98.biolabapp.Repository;
import com.jdpadron98.biolabapp.app.AppMediator;

public class MainScreen {

    public static void configure(MainContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        MainState state = mediator.getMainState();
        Contract repository = Repository.getInstance(context.get());

        MainContract.Router router = new MainRouter(mediator);
        MainContract.Presenter presenter = new MainPresenter(state);
        MainContract.Model model = new MainModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
