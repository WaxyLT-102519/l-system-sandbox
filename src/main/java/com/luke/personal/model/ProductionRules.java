package com.luke.personal.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProductionRules {

    private final Map<Character, String> ruleSet;

    private ProductionRules(Map<Character, String> ruleSet) {
        this.ruleSet = ruleSet;
    }

    public String produceNext(String current) {
        Objects.requireNonNull(current, "current");

        List<Character> characters = current.chars().mapToObj(c -> (char) c).toList();
        StringBuilder sb = new StringBuilder();
        for (var c : characters) {
            sb.append(ruleSet.getOrDefault(c, c.toString()));
        }

        return sb.toString();
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<Character, String> proposedRules = new HashMap<>();

        public Builder mapping(char from, String to) {
            Objects.requireNonNull(to, "to");
            if (to.isEmpty()) {
                throw new IllegalArgumentException("Cannot map '%s' to an empty string!".formatted(from));
            }

            this.proposedRules.put(from, to);
            return this;
        }

        public ProductionRules build() {
            return new ProductionRules(Map.copyOf(proposedRules));
        }
    }
}
