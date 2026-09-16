package com.luke.personal.presenter;

import com.luke.personal.model.LSystemGenerations;
import com.luke.personal.routes.Navigable;
import com.luke.personal.routes.Navigator;
import com.luke.personal.view.axiom.AxiomView;

public class AxiomPresenter extends Navigable {
    private LSystemGenerations model;
    private final AxiomView view;
    private int currentGeneration = 1;

    public AxiomPresenter(AxiomView view, Navigator navigator) {
        super(navigator);
        this.view = view;
        this.view.onNextGenerationClicked(this::handleNextGeneration);
        this.view.onPreviousGenerationClicked(this::handlePreviousGeneration);
        this.view.showGenerationNumber(currentGeneration);
        this.view.enablePrevious(false);
    }

    @Override
    public void onShow(Object payload) {
        this.model = (LSystemGenerations) payload;
        this.currentGeneration = 1;
        view.showAxiom(model.generation(currentGeneration));
    }

    private void handleNextGeneration() {
        currentGeneration++;
        String nextGeneration = model.generation(currentGeneration);

        view.showAxiom(nextGeneration);
        view.showGenerationNumber(currentGeneration);

        view.enablePrevious(true);
    }

    private void handlePreviousGeneration() {
        if (currentGeneration <= 1) {
            return;
        }

        currentGeneration--;
        String previousGeneration = model.generation(currentGeneration);

        view.showAxiom(previousGeneration);
        view.showGenerationNumber(currentGeneration);

        if (currentGeneration <= 1) {
            view.enablePrevious(false);
        }
    }
}
