package com.luke.personal.presenter;

import com.luke.personal.model.LSystemGenerations;
import com.luke.personal.view.AxiomView;

public class AxiomPresenter {
    private LSystemGenerations model;
    private AxiomView view;
    private int currentGeneration = 1;

    public AxiomPresenter(LSystemGenerations model, AxiomView view) {
        this.model = model;
        this.view = view;
        this.view.showAxiom(model.generation(currentGeneration));
        this.view.showGenerationNumber(currentGeneration);
        this.view.onNextGenerationClicked(this::handleNextGeneration);
        this.view.onPreviousGenerationClicked(this::handlePreviousGeneration);
        this.view.enablePrevious(false);
    }

    public void handleNextGeneration() {
        currentGeneration++;
        String nextGeneration = model.generation(currentGeneration);

        view.showAxiom(nextGeneration);
        view.showGenerationNumber(currentGeneration);

        if (currentGeneration > 1) {
            view.enablePrevious(true);
        }
    }

    public void handlePreviousGeneration() {
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
