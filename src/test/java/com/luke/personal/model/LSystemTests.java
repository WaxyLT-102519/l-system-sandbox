package com.luke.personal.model;

import com.luke.personal.model.fixtures.Rules;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Tests for LSystem")
public class LSystemTests {

    @Nested
    @DisplayName("Tests for constructor")
    class ConstructorTests {

        @Test
        @DisplayName("Should throw NullPointerException when null axiom is given")
        void shouldThrowNullPointerException_whenNullAxiomIsGiven() {
            assertThatThrownBy(() -> new LSystem(null, Rules.NONE))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("axiom");
        }

        @Test
        @DisplayName("Should throw NullPointerException when null rules are given")
        void shouldThrowNullPointerException_whenNullRulesAreGiven() {
            assertThatThrownBy(() -> new LSystem("ab", null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("rules");
        }
    }



    @Nested
    @DisplayName("Tests for applyRules")
    class ApplyRulesTests {

        @Test
        @DisplayName("Should return equivalent LSystem when there are no production rules")
        void shouldReturnEquivalentLSystem_whenThereAreNoProductionRules() {
            var lsystem = new LSystem("ab", Rules.NONE);

            var nextLSystem = lsystem.applyRules();

            assertThat(lsystem).isEqualTo(nextLSystem);
        }

        @Test
        @DisplayName("Should return equivalent LSystem when the production rules do not apply")
        void shouldReturnEquivalentLSystem_whenTheProductionRulesDoNotApply() {
            var rules = ProductionRules.builder()
                    .mapping('c', "def")
                    .build();
            var lsystem = new LSystem("ab", rules);

            var nextLSystem = lsystem.applyRules();

            assertThat(lsystem).isEqualTo(nextLSystem);
        }

        @Test
        @DisplayName("Should return next generation when some rules are applied")
        void shouldReturnNextGeneration_whenSomeRulesAreApplied() {
            var rules = ProductionRules.builder()
                    .mapping('a', "ab")
                    .mapping('c', "def")
                    .build();
            var lsystem = new LSystem("ab", rules);
            var expected = new LSystem("abb", rules);

            var nextLSystem = lsystem.applyRules();

            assertThat(nextLSystem).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should return next generation when all rules are applied")
        void shouldReturnNextGeneration_whenAllRulesAreApplied() {
            var rules = Rules.AB;
            var lsystem = new LSystem("ab", rules);
            var expected = new LSystem("aba", rules);

            var nextLSystem = lsystem.applyRules();

            assertThat(nextLSystem).isEqualTo(expected);
        }
    }
}
