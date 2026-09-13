package com.luke.personal.model;

import java.util.Objects;

public record LSystem(String axiom, ProductionRules rules) {

    public LSystem {
        Objects.requireNonNull(axiom, "axiom");
        Objects.requireNonNull(rules, "rules");
    }

    public LSystem applyRules() {
        String nextGeneration = this.rules.produceNext(this.axiom);

        return new LSystem(nextGeneration, this.rules);
    }
}
