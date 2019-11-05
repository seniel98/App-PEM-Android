package com.jdpadron98.biolabapp.Login;

import androidx.fragment.app.FragmentActivity;

import com.jdpadron98.biolabapp.Contract;
import com.jdpadron98.biolabapp.Repository;
import com.jdpadron98.biolabapp.app.AppMediator;

import java.lang.ref.WeakReference;


public class LoginScreen {

    public static void configure(LoginContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        LoginState state = mediator.getLoginState();
        Contract repository = Repository.getInstance(context.get());


        LoginContract.Router router = new LoginRouter(mediator);
        LoginContract.Presenter presenter = new LoginPresenter(state);
        LoginContract.Model model = new LoginModel(repository);
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
