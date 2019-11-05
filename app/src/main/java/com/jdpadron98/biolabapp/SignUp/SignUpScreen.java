package com.jdpadron98.biolabapp.SignUp;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;


import com.jdpadron98.biolabapp.Contract;
import com.jdpadron98.biolabapp.Repository;
import com.jdpadron98.biolabapp.app.AppMediator;

public class SignUpScreen {

    public static void configure(SignUpContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        SignUpState state = mediator.getSignUpState();
        Contract repository = Repository.getInstance(context.get());

        SignUpContract.Router router = new SignUpRouter(mediator);
        SignUpContract.Presenter presenter = new SignUpPresenter(state);
        SignUpContract.Model model = new SignUpModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
