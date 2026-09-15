package com.luke.personal.presenter;

import com.luke.personal.model.LSystem;
import com.luke.personal.model.LSystemGenerations;
import com.luke.personal.routes.Navigable;
import com.luke.personal.routes.Navigator;
import com.luke.personal.routes.Route;
import com.luke.personal.view.setup.SetupView;

public class SetupPresenter implements Navigable {
    private final Navigator navigator;
    private final SetupView view;

    public SetupPresenter(Navigator navigator, SetupView view) {
        this.navigator = navigator;
        this.view = view;
        this.view.onSubmitClicked(this::handleSubmitClicked);
        this.view.onPlusClicked(this::handlePlusClicked);
        this.view.onMinusClicked(this::handleMinusClicked);
    }

    @Override
    public void onShow(Object payload) {

    }

    private void handleSubmitClicked() {
        LSystemGenerations generations = view.getLSystemGenerations();
        navigator.goTo(Route.AXIOM, generations);
    }

    private void handlePlusClicked() {
        view.addRuleInput();
    }

    private void handleMinusClicked() {
        view.removeRuleInput();
    }
}
