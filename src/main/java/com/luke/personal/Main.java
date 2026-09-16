package com.luke.personal;

import com.luke.personal.model.LSystem;
import com.luke.personal.model.LSystemGenerations;
import com.luke.personal.model.ProductionRules;
import com.luke.personal.presenter.AxiomPresenter;
import com.luke.personal.presenter.SetupPresenter;
import com.luke.personal.routes.Navigator;
import com.luke.personal.routes.Route;
import com.luke.personal.routes.SwingNavigator;
import com.luke.personal.view.axiom.AxiomPanel;
import com.luke.personal.view.setup.SetupPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Navigator navigator = new SwingNavigator();

            SetupPanel setupPanel = new SetupPanel();
            SetupPresenter setupPresenter = new SetupPresenter(setupPanel, navigator);
            navigator.register(Route.SETUP, setupPanel, setupPresenter);

            AxiomPanel axiomPanel = new AxiomPanel();
            AxiomPresenter axiomPresenter = new AxiomPresenter(axiomPanel, navigator);
            navigator.register(Route.AXIOM, axiomPanel, axiomPresenter);

            JFrame mainFrame = new JFrame("L-System Setup Page");
            mainFrame.setSize(800, 600);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
            mainFrame.setContentPane(navigator.getNavigationPanel());

            navigator.goTo(Route.SETUP);
        });
    }

    private static LSystemGenerations initLSystemGenerations() {
        ProductionRules rules = ProductionRules.builder()
                .mapping('a', "ab")
                .mapping('b', "a")
                .build();
        LSystem lsystem = new LSystem("ab", rules);

        return new LSystemGenerations(lsystem);
    }
}