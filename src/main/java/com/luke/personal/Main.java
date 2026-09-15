package com.luke.personal;

import com.luke.personal.model.LSystem;
import com.luke.personal.model.LSystemGenerations;
import com.luke.personal.model.ProductionRules;
import com.luke.personal.presenter.SetupPresenter;
import com.luke.personal.routes.LoggingNavigator;
import com.luke.personal.routes.Navigator;
import com.luke.personal.view.setup.SetupPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LSystemGenerations generations = initLSystemGenerations();
            SetupPanel setup = new SetupPanel();
            Navigator navigator = new LoggingNavigator();
            new SetupPresenter(navigator, setup);

            JFrame mainFrame = new JFrame("L-System Setup Page");
            mainFrame.add(setup);
            mainFrame.setSize(800, 600);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
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