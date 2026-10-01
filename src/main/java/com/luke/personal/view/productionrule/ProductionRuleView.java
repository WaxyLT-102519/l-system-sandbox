package com.luke.personal.view.productionrule;

public interface ProductionRuleView {
    char getCharacter();
    String getRule();
    void onMinusClicked(Runnable onMinus);
}
