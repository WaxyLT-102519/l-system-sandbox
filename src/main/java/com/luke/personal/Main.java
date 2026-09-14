package com.luke.personal;

import com.luke.personal.model.LSystem;
import com.luke.personal.model.LSystemGenerations;
import com.luke.personal.model.ProductionRules;
import com.luke.personal.presenter.AxiomPresenter;
import com.luke.personal.view.axiom.AxiomPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LSystemGenerations generations = initLSystemGenerations();
            AxiomPanel panel = new AxiomPanel();

            new AxiomPresenter(generations, panel);

            JFrame mainFrame = new JFrame("L-System Axiom Viewer");
            mainFrame.add(panel);
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