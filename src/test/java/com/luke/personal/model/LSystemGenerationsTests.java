package com.luke.personal.model;

import com.luke.personal.model.fixtures.Rules;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Tests for LSystemGenerations")
public class LSystemGenerationsTests {

    @Nested
    @DisplayName("Tests for constructor")
    class ConstructorTests {

        @Test
        @DisplayName("Should throw NullPointerException when LSystem is null")
        void shouldThrowNullPointerException_whenLSystemIsNull() {
            assertThatThrownBy(() -> new LSystemGenerations(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("lsystem");
        }

        @Test
        @DisplayName("Should create a new LSystemGenerations when called")
        void shouldCreateANewLSystemGenerations_whenCalled() {
            LSystem lsystem = new LSystem("ab", Rules.AB);
        }
    }
}
