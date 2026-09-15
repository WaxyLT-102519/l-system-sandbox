package com.luke.personal.presenter;

import com.luke.personal.model.LSystem;
import com.luke.personal.routes.Navigator;
import com.luke.personal.view.setup.SetupView;

public class SetupPresenter {
    private final Navigator navigator;
    private final SetupView view;

    public SetupPresenter(Navigator navigator, SetupView view) {
        this.navigator = navigator;
        this.view = view;
        this.view.onSubmitClicked(this::handleSubmitClicked);
        this.view.onPlusClicked(this::handlePlusClicked);
        this.view.onMinusClicked(this::handleMinusClicked);
    }

    private void handleSubmitClicked() {
        LSystem userInput = view.getLSystem();
        navigator.goToAxiom(userInput);
    }

    private void handlePlusClicked() {
        view.addRuleInput();
    }

    private void handleMinusClicked() {
        view.removeRuleInput();
    }
}
