package com.luke.personal.model;

import com.luke.personal.model.fixtures.Rules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
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

            var generations = new LSystemGenerations(lsystem);

            assertThat(generations)
                    .isNotNull()
                    .isInstanceOf(LSystemGenerations.class);
        }
    }



    @Nested
    @DisplayName("Tests for generation")
    class GenerationTests {

        private LSystemGenerations generations;

        @BeforeEach
        void setup() {
            var lsystem = new LSystem("ab", Rules.AB);
            this.generations = new LSystemGenerations(lsystem);
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("supply_shouldThrowIllegalArgumentException_whenGenerationIsLessThanOrEqualToZero")
        @DisplayName("Should throw IllegalArgumentException when generation is less than or equal to zero")
        void shouldThrowIllegalArgumentException_whenGenerationIsLessThanOrEqualToZero(
                String testName,
                int failingValue,
                String exceptionMessage
        ) {
            assertThatThrownBy(() -> generations.generation(failingValue))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(exceptionMessage);
        }

        private static Stream<Arguments> supply_shouldThrowIllegalArgumentException_whenGenerationIsLessThanOrEqualToZero() {
            String exceptionMessage = "Generation must be greater than 0, but was %d.";

            return Stream.of(
                    Arguments.of("0", 0, exceptionMessage.formatted(0)),
                    Arguments.of("-1", -1, exceptionMessage.formatted(-1)),
                    Arguments.of("-42", -42, exceptionMessage.formatted(-42)),
                    Arguments.of("Integer.MIN_VALUE", Integer.MIN_VALUE, exceptionMessage.formatted(Integer.MIN_VALUE))
            );
        }

        @Test
        @DisplayName("Should return the cached axiom of the given generation when called with the same generation")
        void shouldReturnTheCachedAxiomOfTheGivenGeneration_whenCalledWithTheSameGeneration() {
            int generation = 4;
            String expected = "abaababa";
            this.generations.generation(generation); // pre-fill cache

            // this test is similar to the one below, but it covers
            // a different branch of the method body.
            String actual = this.generations.generation(generation);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should return the cached axiom of the given generation when cache has been pre-filled to a higher generation")
        void shouldReturnTheCachedAxiomOfTheGivenGeneration_whenCacheHasBeenPreFilledToAHigherGeneration() {
            int generation = 4;
            String expected = "abaababa";
            this.generations.generation(6); // pre-fill cache above the target

            // this test is similar to the one below, but it covers
            // a different branch of the method body
            String actual = this.generations.generation(generation);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should return the axiom of the given generations when called")
        void shouldReturnTheAxiomOfTheGivenGenerations_whenCalled() {
            int generation = 4;
            String expected = "abaababa";

            String actual = this.generations.generation(generation);

            assertThat(actual).isEqualTo(expected);
        }
    }
}
