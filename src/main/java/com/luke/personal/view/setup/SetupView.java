package com.luke.personal.view.setup;

import com.luke.personal.model.LSystem;
import com.luke.personal.model.LSystemGenerations;

public interface SetupView {
    LSystemGenerations getLSystemGenerations();
    void onSubmitClicked(Runnable handler);
    void onPlusClicked(Runnable handler);
    void onMinusClicked(Runnable handler);
    void addRuleInput();
    void removeRuleInput();
}
