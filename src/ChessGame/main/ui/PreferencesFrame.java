package ChessGame.main.ui;

import ChessGame.main.util.Preferences;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import javax.swing.*;
import java.awt.*;

public class PreferencesFrame extends JFrame{

    //MainPanel containing BannerPanel, SettingsPanel and ButtonPanel
    private JPanel preferencesPanel;


    //Containing GameSettingsPanel and NetworkSettingsPanel
    private JPanel settingsPanel;
    private JPanel gameSettingsPanel;
    private JPanel networkSettingsPanel;
    //Containing Reset, Start Button
    private JPanel buttonPanel;
    //Containing GameModePanel
    private JPanel bannerPanel;
    //GameMode Contains Online/offline
    private JPanel gameModePanel;
    private JRadioButton onlineButton;
    private JRadioButton offlineButton;
    private JLabel onlineLabel;
    private JLabel offlineLabel;

    private JCheckBox reverseBoardCheckBox;

    private JRadioButton timerRadioButton;
    private JRadioButton countdownRadioButton;
    private JFormattedTextField timeLimitField;


    public PreferencesFrame(){
        super("GameSettings");
        Preferences pref = new Preferences();
        LoadPreferences();
        LoadPanel();

    }

    private void LoadPanel() {
        InitializedBanner();
        InitializedSettings();

        preferencesPanel = new JPanel(new BorderLayout());
        preferencesPanel.add(bannerPanel, BorderLayout.NORTH);
        preferencesPanel.add(settingsPanel, BorderLayout.SOUTH);
        preferencesPanel.setPreferredSize(new Dimension(600, 400));


        this.add(preferencesPanel);
        this.pack();
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }


    public void LoadPreferences(){


    }

    private void InitializedGameSettings(){
        gameSettingsPanel = new JPanel();

        reverseBoardCheckBox = new JCheckBox();
        timerRadioButton = new JRadioButton();
        countdownRadioButton = new JRadioButton();
        timeLimitField = new JFormattedTextField();
        timeLimitField.setColumns(3);
        //set to consume
        timeLimitField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();  // ignore event
                }
            }
        });
        gameSettingsPanel.setBackground(Color.LIGHT_GRAY);
        gameSettingsPanel.add(timeLimitField);
        gameSettingsPanel.add(reverseBoardCheckBox);
        gameSettingsPanel.add(timerRadioButton);
        gameSettingsPanel.add(countdownRadioButton);



    }

    private void InitializedNetworkSettings(){

    }

    private void InitializedSettings(){
        settingsPanel = new JPanel();
        InitializedGameSettings();

        settingsPanel.setBackground(Color.LIGHT_GRAY);
        settingsPanel.add(gameSettingsPanel);
        settingsPanel.setPreferredSize(new Dimension(600, 120));
    }

    public void InitializedBanner(){
        bannerPanel = new JPanel(new GridBagLayout());
        //constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;


        gameModePanel = new JPanel();
        onlineButton = new JRadioButton();
        offlineButton = new JRadioButton();
        onlineLabel = new JLabel("Online");
        offlineLabel= new JLabel("Offline");

        gameModePanel.add(onlineLabel);
        gameModePanel.add(onlineButton);
        gameModePanel.add(offlineLabel);
        gameModePanel.add(offlineButton);

        bannerPanel.add(gameModePanel, constraints);

        bannerPanel.setPreferredSize(new Dimension(600, 120));
    }



}
