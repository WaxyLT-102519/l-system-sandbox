package com.luke.personal.model;

import java.util.ArrayList;
import java.util.List;

public class LSystemGenerations {

    private final List<LSystem> generations;

    public LSystemGenerations(LSystem lsystem) {
        this.generations = new ArrayList<>();
        this.generations.add(lsystem);
    }

    public String firstGeneration() {
        return this.generation(1);
    }

    public String generation(int generation) {
        if (generation <= 0) {
            throw new IllegalArgumentException("Generation must be greater than 0, but was " + generation);
        }
        int normalizedIndex = generation - 1;

        if (this.generations.size() >= generation) {
            return this.generations.get(normalizedIndex).axiom();
        }

        while (this.generations.size() < generation) {
            LSystem previousGeneration = this.generations.getLast();
            this.generations.add(previousGeneration.applyRules());
        }

        return this.generations.get(normalizedIndex).axiom();
    }
}
