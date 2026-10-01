package com.luke.personal.view.productionrule;

import com.luke.personal.view.SwingView;

public interface ProductionRuleView extends SwingView {
    char getCharacter();
    String getRule();
    void onMinusClicked(Runnable onMinus);
}
