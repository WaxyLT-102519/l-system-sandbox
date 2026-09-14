package com.luke.personal.presenter;

import com.luke.personal.view.axiom.AxiomView;

public class AxiomViewSpy implements AxiomView {
    Runnable nextGen, prevGen;
    String axiom;
    int generationNumber;
    boolean isPreviousEnabled;

    @Override
    public void onNextGenerationClicked(Runnable handler) {
        this.nextGen = handler;
    }

    @Override
    public void onPreviousGenerationClicked(Runnable handler) {
        this.prevGen = handler;
    }

    @Override
    public void showAxiom(String axiom) {
        this.axiom = axiom;
    }

    @Override
    public void showGenerationNumber(int generation) {
        this.generationNumber = generation;
    }

    @Override
    public void enablePrevious(boolean enabled) {
        this.isPreviousEnabled = enabled;
    }
}
