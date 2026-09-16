package com.luke.personal.presenter;

import com.luke.personal.model.LSystem;
import com.luke.personal.model.LSystemGenerations;
import com.luke.personal.model.fixtures.Rules;
import com.luke.personal.routes.NavigatorSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for AxiomPresenter")
public class AxiomPresenterTests {

    private LSystemGenerations model;

    @BeforeEach
    void setup() {
        model = new LSystemGenerations(new LSystem("ab", Rules.AB));
    }



    @Nested
    @DisplayName("Tests for constructor")
    class ConstructorTests {

        @Test
        @DisplayName("Should initialize view when constructed")
        void shouldInitializeView_whenConstructed() {
            AxiomViewSpy spy = new AxiomViewSpy();
            NavigatorSpy nav = new NavigatorSpy();

            new AxiomPresenter(spy, nav);

            assertThat(spy.generationNumber).isEqualTo(1);
            assertThat(spy.isPreviousEnabled).isFalse();
            assertThat(spy.nextGen).isNotNull();
            assertThat(spy.prevGen).isNotNull();
        }
    }



    @Nested
    @DisplayName("Tests for onShow")
    class OnShowTests {

        @Test
        @DisplayName("Should reset the current model and generation when called")
        void shouldResetTheCurrentModelAndGeneration_whenCalled() {
            AxiomViewSpy spy = new AxiomViewSpy();
            NavigatorSpy nav = new NavigatorSpy();

            AxiomPresenter presenter = new AxiomPresenter(spy, nav);
            presenter.onShow(model);

            assertThat(spy.axiom).isEqualTo("ab");
            assertThat(spy.generationNumber).isEqualTo(1);
        }
    }



    @Nested
    @DisplayName("Tests for handleNextGeneration")
    class HandleNextGenerationTests {

        @Test
        @DisplayName("Should show the next generation when called")
        void shouldShowTheNextGeneration_whenCalled() {
            AxiomViewSpy spy = new AxiomViewSpy();
            NavigatorSpy nav = new NavigatorSpy();
            AxiomPresenter presenter = new AxiomPresenter(spy, nav);
            presenter.onShow(model);

            // presenter.handleNextGeneration()
            spy.nextGen.run();

            assertThat(spy.axiom).isEqualTo("aba");
            assertThat(spy.generationNumber).isEqualTo(2);
            assertThat(spy.isPreviousEnabled).isTrue();
        }
    }



    @Nested
    @DisplayName("Tests for handlePreviousGeneration")
    class HandlePreviousGenerationTests {

        @Test
        @DisplayName("Should do nothing when current generation is the first generation")
        void shouldDoNothing_whenCurrentGenerationIsTheFirstGeneration() {
            AxiomViewSpy spy = new AxiomViewSpy();
            NavigatorSpy nav = new NavigatorSpy();
            AxiomPresenter presenter = new AxiomPresenter(spy, nav);
            presenter.onShow(model);

            // no generations have been incremented yet

            // gather state before the change (shouldn't actually change)
            String axiomBefore = spy.axiom;
            int generationNumberBefore = spy.generationNumber;
            boolean isPreviousEnabledBefore = spy.isPreviousEnabled;

            // presenter.handlePreviousGeneration()
            spy.prevGen.run();

            assertThat(spy.axiom).isEqualTo(axiomBefore);
            assertThat(spy.generationNumber).isEqualTo(generationNumberBefore);
            assertThat(spy.isPreviousEnabled).isEqualTo(isPreviousEnabledBefore);
        }

        @Test
        @DisplayName("Should show the previous generation and disable the previous button when moving from the second to first generation")
        void shouldShowThePreviousGenerationAndDisableThePreviousButton_whenMovingFromTheSecondToFirstGeneration() {
            AxiomViewSpy spy = new AxiomViewSpy();
            NavigatorSpy nav = new NavigatorSpy();
            AxiomPresenter presenter = new AxiomPresenter(spy, nav);
            presenter.onShow(model);
            spy.nextGen.run(); // setup step, move generation to 2

            // presenter.handlePreviousGeneration()
            spy.prevGen.run();

            assertThat(spy.axiom).isEqualTo("ab");
            assertThat(spy.generationNumber).isEqualTo(1);
            assertThat(spy.isPreviousEnabled).isFalse();
        }

        @Test
        @DisplayName("Should show the previous generation when called")
        void shouldShowThePreviousGeneration_whenCalled() {
            AxiomViewSpy spy = new AxiomViewSpy();
            NavigatorSpy nav = new NavigatorSpy();
            AxiomPresenter presenter = new AxiomPresenter(spy, nav);
            presenter.onShow(model);
            spy.nextGen.run(); // "aba", generation 2
            spy.nextGen.run(); // "abaab", generation 3
            spy.nextGen.run(); // "abaababa", generation 4

            // presenter.handlePreviousGeneration()
            spy.prevGen.run();

            assertThat(spy.axiom).isEqualTo("abaab");
            assertThat(spy.generationNumber).isEqualTo(3);
            assertThat(spy.isPreviousEnabled).isTrue();
        }
    }
}
