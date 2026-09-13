package com.luke.personal.model;

public record LSystem(String axiom, ProductionRules rules) {

    public LSystem applyRules() {
        String nextGeneration = this.rules.produceNext(this.axiom);

        return new LSystem(nextGeneration, this.rules);
    }
}
