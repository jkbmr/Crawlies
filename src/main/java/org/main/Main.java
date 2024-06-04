package org.main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Crawlies");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        SimulationPanel simulationPanel = new SimulationPanel();

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        JButton buttonStart = new JButton("Start");
        buttonStart.addActionListener(e -> simulationPanel.unpauseSimulation());
        buttons.add(buttonStart);
        buttons.add(Box.createVerticalStrut(10));

        JButton buttonPause = new JButton("Pause");
        buttonPause.addActionListener(e -> simulationPanel.pauseSimulation());
        buttons.add(buttonPause);
        buttons.add(Box.createVerticalStrut(10));

        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(e -> simulationPanel.resetSimulation());
        buttons.add(buttonReset);
        buttons.add(Box.createVerticalStrut(10));

        JButton buttonPrint = new JButton("Print");
        buttonPrint.addActionListener(e -> simulationPanel.printInfo());
        buttons.add(buttonPrint);
        buttons.add(Box.createVerticalStrut(10));

        sidePanel.add(buttons);

        mainPanel.add(simulationPanel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(sidePanel);

        window.add(mainPanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        simulationPanel.startThread();
    }
}