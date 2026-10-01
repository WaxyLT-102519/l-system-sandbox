package com.luke.personal.view.productionrule;

import javax.swing.*;
import java.awt.*;

public class ProductionRulePanel extends JPanel implements ProductionRuleView {

    private final JButton minusButton = new JButton("-");
    private final JTextField characterField = new JTextField();
    private final JTextField ruleField = new JTextField();

    public ProductionRulePanel() {
        setLayout(new FlowLayout());

        add(minusButton);

        JLabel characterLabel = new JLabel("character:");
        characterLabel.setLabelFor(characterField);
        characterField.setEditable(true);
        characterField.setColumns(10);

        JLabel ruleLabel = new JLabel("rule:");
        ruleLabel.setLabelFor(ruleField);
        ruleField.setEditable(true);
        ruleField.setColumns(10);

        add(characterLabel);
        add(characterField);
        add(ruleLabel);
        add(ruleField);
    }

    @Override
    public char getCharacter() {
        return characterField.getText().charAt(0);
    }

    @Override
    public String getRule() {
        return ruleField.getText();
    }

    @Override
    public void onMinusClicked(Runnable onMinus) {
        minusButton.addActionListener(e -> onMinus.run());
    }
}
