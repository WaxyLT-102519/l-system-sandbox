package com.luke.personal.view.setup;

import com.luke.personal.model.LSystem;

public interface SetupView {
    LSystem getLSystem();
    void onSubmitClicked(Runnable handler);
    void onPlusClicked(Runnable handler);
    void onMinusClicked(Runnable handler);
    void addRuleInput();
    void removeRuleInput();
}
