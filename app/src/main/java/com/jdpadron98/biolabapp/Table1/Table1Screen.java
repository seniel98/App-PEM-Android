package com.jdpadron98.biolabapp.Table1;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;


import com.jdpadron98.biolabapp.app.AppMediator;

public class Table1Screen {

    public static void configure(Table1Contract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        Table1State state = mediator.getTable1State();

        Table1Contract.Router router = new Table1Router(mediator);
        Table1Contract.Presenter presenter = new Table1Presenter(state);
        Table1Contract.Model model = new Table1Model();
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
