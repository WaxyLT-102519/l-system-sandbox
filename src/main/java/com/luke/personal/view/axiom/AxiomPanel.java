package com.luke.personal.view.axiom;

import javax.swing.*;
import java.awt.*;

public class AxiomPanel extends JPanel implements AxiomView {

    private final JTextArea axiomDisplay = new JTextArea();
    private final JLabel generationLabel = new JLabel();
    private final JButton previousButton = new JButton("Previous");
    private final JButton nextButton = new JButton("Next");

    public AxiomPanel() {
        setLayout(new BorderLayout());

        axiomDisplay.setEditable(false);
        axiomDisplay.setLineWrap(true);
        axiomDisplay.setWrapStyleWord(false);
        axiomDisplay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        add(new JScrollPane(axiomDisplay), BorderLayout.NORTH);

        generationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(previousButton, BorderLayout.WEST);
        buttonPanel.add(generationLabel, BorderLayout.CENTER);
        buttonPanel.add(nextButton, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void onNextGenerationClicked(Runnable handler) {
        nextButton.addActionListener(e -> handler.run());
    }

    @Override
    public void onPreviousGenerationClicked(Runnable handler) {
        previousButton.addActionListener(e -> handler.run());
    }

    @Override
    public void showAxiom(String axiom) {
        axiomDisplay.setText(axiom);
    }

    @Override
    public void showGenerationNumber(int generation) {
        generationLabel.setText(Integer.toString(generation));
    }

    @Override
    public void enablePrevious(boolean enabled) {
        previousButton.setEnabled(enabled);
    }
}
