package com.luke.personal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LSystemGenerations {

    private final List<LSystem> generations;

    public LSystemGenerations(LSystem lsystem) {
        Objects.requireNonNull(lsystem, "lsystem");

        this.generations = new ArrayList<>();
        this.generations.add(lsystem);
    }

    public String generation(int generation) {
        if (generation <= 0) {
            throw new IllegalArgumentException("Generation must be greater than 0, but was %d.".formatted(generation));
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
