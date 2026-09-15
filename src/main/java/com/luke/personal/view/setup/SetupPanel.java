package com.luke.personal.view.setup;

import com.luke.personal.model.LSystem;
import com.luke.personal.model.ProductionRules;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SetupPanel extends JPanel implements SetupView {
    private final JTextField axiomField = new JTextField();
    private final JPanel productionRulesPanel = new JPanel();
    private final List<ProductionRuleField> productionRuleFields = new ArrayList<>();
    private final JButton submitButton = new JButton("Submit");
    private final JButton plusButton = new JButton("+");
    private final JButton minusButton = new JButton("-");

    public SetupPanel() {
        setLayout(new BorderLayout());

        JPanel axiomPanel = new JPanel(new FlowLayout());
        JLabel axiomLabel = new JLabel("axiom:");
        axiomLabel.setLabelFor(axiomField);
        axiomField.setEditable(true);
        axiomPanel.add(axiomLabel);
        axiomPanel.add(axiomField);
        add(axiomPanel, BorderLayout.NORTH);

        JLabel productionRulesLabel = new JLabel("production rules:");
        productionRulesLabel.setLabelFor(productionRulesPanel);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(plusButton);
        buttonsPanel.add(minusButton);
        productionRulesPanel.add(buttonsPanel);

        ProductionRuleField firstRule = new ProductionRuleField();
        productionRulesPanel.add(firstRule);
        add(productionRulesPanel, BorderLayout.CENTER);

        add(submitButton, BorderLayout.SOUTH);
    }

    @Override
    public LSystem getLSystem() {
        String axiom = axiomField.getText();

        ProductionRules.Builder builder = ProductionRules.builder();
        for (var rule : productionRuleFields) {
            builder.mapping(rule.getCharacter(), rule.getRule());
        }
        ProductionRules productionRules = builder.build();

        return new LSystem(axiom, productionRules);
    }

    @Override
    public void onSubmitClicked(Runnable handler) {
        submitButton.addActionListener(e -> handler.run());
    }

    @Override
    public void onPlusClicked(Runnable handler) {
        plusButton.addActionListener(e -> handler.run());
    }

    @Override
    public void onMinusClicked(Runnable handler) {
        minusButton.addActionListener(e -> handler.run());
    }

    @Override
    public void addRuleInput() {
        ProductionRuleField productionRuleField = new ProductionRuleField();
        productionRulesPanel.add(productionRuleField);
        productionRuleFields.add(productionRuleField);
    }

    @Override
    public void removeRuleInput() {
        ProductionRuleField toRemove = productionRuleFields.getLast();
        productionRuleFields.removeLast();
        productionRulesPanel.remove(toRemove);
    }

    private static class ProductionRuleField extends JPanel {
        private final JTextField characterField = new JTextField();
        private final JTextField ruleField = new JTextField();

        public ProductionRuleField() {
            setLayout(new FlowLayout());

            JLabel characterLabel = new JLabel("character:");
            characterLabel.setLabelFor(characterField);
            characterField.setEditable(true);

            JLabel ruleLabel = new JLabel("rule:");
            ruleLabel.setLabelFor(ruleField);
            ruleField.setEditable(true);

            add(characterLabel);
            add(characterField);
            add(ruleLabel);
            add(ruleField);
        }

        public char getCharacter() {
            return characterField.getText().charAt(0);
        }

        public String getRule() {
            return ruleField.getText();
        }
    }
}
