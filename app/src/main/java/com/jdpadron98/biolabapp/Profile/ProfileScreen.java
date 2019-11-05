package com.jdpadron98.biolabapp.Profile;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98.biolabapp.Contract;
import com.jdpadron98.biolabapp.Repository;
import com.jdpadron98.biolabapp.app.AppMediator;

import java.lang.ref.WeakReference;


public class ProfileScreen {

    public static void configure(ProfileContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        ProfileState state = mediator.getProfileState();
        Contract repository = Repository.getInstance(context.get());

        ProfileContract.Router router = new ProfileRouter(mediator);
        ProfileContract.Presenter presenter = new ProfilePresenter(state);
        ProfileContract.Model model = new ProfileModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
