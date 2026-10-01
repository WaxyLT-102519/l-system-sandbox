package com.luke.personal.view.axiom;

import com.luke.personal.view.SwingView;

public interface AxiomView extends SwingView {
    void onNextGenerationClicked(Runnable handler);
    void onPreviousGenerationClicked(Runnable handler);

    void showAxiom(String axiom);
    void showGenerationNumber(int generation);

    void enablePrevious(boolean enabled);
}
