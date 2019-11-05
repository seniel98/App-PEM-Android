package com.jdpadron98.biolabapp.Guest;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;


import com.jdpadron98.biolabapp.Contract;
import com.jdpadron98.biolabapp.Repository;
import com.jdpadron98.biolabapp.app.AppMediator;

public class GuestScreen {

    public static void configure(GuestContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        GuestState state = mediator.getGuestState();
        //Here we create a repository contract object
        Contract repository = Repository.getInstance(context.get());

        GuestContract.Router router = new GuestRouter(mediator);
        GuestContract.Presenter presenter = new GuestPresenter(state);
        GuestContract.Model model = new GuestModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
