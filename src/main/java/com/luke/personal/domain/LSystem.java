package com.luke.personal.domain;

public record LSystem(String axiom, ProductionRules rules) {

    public LSystem applyRules() {
        String nextGeneration = this.rules.produceNext(this.axiom);

        return new LSystem(nextGeneration, this.rules);
    }
}
