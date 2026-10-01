package com.luke.personal.view.setup;

import com.luke.personal.model.LSystemGenerations;
import com.luke.personal.view.SwingView;

public interface SetupView extends SwingView {
    LSystemGenerations getLSystemGenerations();
    void onSubmitClicked(Runnable handler);
    void onPlusClicked(Runnable handler);
    void onMinusClicked(Runnable handler);
    void addRuleInput();
    void removeRuleInput();
}
