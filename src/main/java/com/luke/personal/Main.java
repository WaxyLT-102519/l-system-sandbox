package com.luke.personal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Test Window");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(800,600);
            window.setVisible(true);

            JButton button = new JButton("Click Me");
            window.add(button);
            button.addActionListener(e -> {});
            button.setSize(30,30);
        });
    }
}