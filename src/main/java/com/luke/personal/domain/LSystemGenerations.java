package com.luke.personal.domain;

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
        if (this.generations.size() >= generation) {
            return this.generations.get(generation - 1).axiom();
        }

        while (this.generations.size() < generation) {
            LSystem previousGeneration = this.generations.getLast();
            this.generations.add(previousGeneration.applyRules());
        }

        return this.generations.get(generation - 1).axiom();
    }
}
