package com.luke.personal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Tests for ProductionRules")
public class ProductionRulesTests {

    @Nested
    @DisplayName("Tests for builder")
    class BuilderTests {

        @Test
        @DisplayName("Should return a ProductionRules.Builder when called")
        void shouldReturnAProductionRulesBuilder_whenCalled() {
            var shouldBeBuilder = ProductionRules.builder();

            assertThat(shouldBeBuilder)
                    .isNotNull()
                    .isInstanceOf(ProductionRules.Builder.class);
        }

        @Test
        @DisplayName("Should throw NullPointerException when to is null")
        void shouldThrowNullPointerException_whenToIsNull() {
            var builder = ProductionRules.builder();

            assertThatThrownBy(() -> builder.mapping('a', null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("to");
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when to is empty")
        void shouldThrowIllegalArgumentException_whenToIsEmpty() {
            var builder = ProductionRules.builder();

            assertThatThrownBy(() -> builder.mapping('a', ""))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Cannot map 'a' to an empty string!");
        }

        @Test
        @DisplayName("Should return the same builder when a mapping is created")
        void shouldReturnTheSameBuilder_whenAMappingIsCreated() {
            var builder = ProductionRules.builder();

            var other = builder.mapping('a', "ab");

            // checks referential equality
            assertThat(other).isEqualTo(builder);
        }

        @Test
        @DisplayName("Should return ProductionRules when build is called")
        void shouldReturnProductionRules_whenBuildIsCalled() {
            var builder = ProductionRules.builder();

            builder.mapping('a', "ab")
                    .mapping('b', "a");

            ProductionRules productionRules = builder.build();

            assertThat(productionRules)
                    .isNotNull()
                    .isInstanceOf(ProductionRules.class);
        }
    }


    
    @Nested
    @DisplayName("Tests for produceNext")
    class ProduceNextTests {

        ProductionRules productionRules;

        @BeforeEach
        void setup() {
            productionRules = ProductionRules.builder()
                    .mapping('a', "ab")
                    .mapping('b', "a")
                    .build();
        }

        @Test
        @DisplayName("Should throw NullPointerException when current is null")
        void shouldThrowNullPointerException_whenCurrentIsNull() {
            assertThatThrownBy(() -> productionRules.produceNext(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("current");
        }

        @Test
        @DisplayName("Should return empty string when current is empty")
        void shouldReturnEmptyString_whenCurrentIsEmpty() {
            String next = productionRules.produceNext("");

            assertThat(next).isEqualTo("");
        }

        @Test
        @DisplayName("Should return current unchanged when none of the rules apply")
        void shouldReturnCurrentUnchanged_whenNoneOfTheRulesApply() {
            String current = "def";

            String next = productionRules.produceNext(current);

            assertThat(next).isEqualTo(current);
        }

        @Test
        @DisplayName("Should return next string when some of the rules apply")
        void shouldReturnNextString_whenSomeOfTheRulesApply() {
            String current = "bcd";
            String expected = "acd";

            String next = productionRules.produceNext(current);

            assertThat(next).isEqualTo(expected);
        }

        @Test
        @DisplayName("Should return next string when all of the rules apply")
        void shouldReturnNextString_whenAllOfTheRulesApply() {
            String current = "aba";
            String expected = "abaab";

            String next = productionRules.produceNext(current);

            assertThat(next).isEqualTo(expected);
        }
    }
}
