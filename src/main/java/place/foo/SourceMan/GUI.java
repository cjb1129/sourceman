package main.java.place.foo.SourceMan;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI extends JFrame {

    SourceMan sourceMan;

    private final JComboBox<SourceGame> gameMenu;
    private final JButton startButton;
    private final JLabel statusLabel;

    private boolean enabled;

    public GUI (String title) {
        super(title);

        enabled = false;

        setDefaultLookAndFeelDecorated(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder("SourceMan"));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel gameLabel = new JLabel("Game: ");
        panel.add(gameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gameMenu = new JComboBox<>();
        gameMenu.addItem(new SourceGame("Counter-Strike: Global Offensive", 730, "common\\Counter-Strike Global Offensive\\", "csgo\\cfg\\"));
        gameMenu.addItem(new SourceGame("Counter-Strike: Source", 240, "common\\Counter-Strike Source\\", "cstrike\\cfg\\"));
        panel.add(gameMenu, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        startButton = new JButton("Start");
        panel.add(startButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        statusLabel = new JLabel("Disabled");
        panel.add(statusLabel,gbc);

        mainPanel.add(panel);

        add(mainPanel, BorderLayout.CENTER);
        //setSize(400,300);
        setResizable(false);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        pack();
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (enabled) {
                    try {
                        sourceMan.kill();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sourceMan = null;
                }
                System.exit(0);
            }
        });

        startButton.addActionListener((e) -> {
            if (enabled) {
                startButton.setText("Start");
                gameMenu.setEnabled(true);
                statusLabel.setText("Disabled");

                try {
                    sourceMan.kill();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                sourceMan = null;

                enabled = false;
            } else {
                startButton.setText("Stop");
                gameMenu.setEnabled(false);
                statusLabel.setText("Enabled");
                sourceMan = new SourceMan();
                enabled = true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
    }
}
