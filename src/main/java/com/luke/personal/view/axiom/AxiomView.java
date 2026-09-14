package com.luke.personal.view.axiom;

public interface AxiomView {
    void onNextGenerationClicked(Runnable handler);
    void onPreviousGenerationClicked(Runnable handler);

    void showAxiom(String axiom);
    void showGenerationNumber(int generation);

    void enablePrevious(boolean enabled);
}
