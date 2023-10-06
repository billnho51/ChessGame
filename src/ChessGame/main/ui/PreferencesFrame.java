package ChessGame.main.ui;

import ChessGame.main.util.GameManager;
import ChessGame.main.util.Preferences;
import ChessGame.main.util.Core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JLabel notImplementedLabel;

    //Game Settings component
    private JCheckBox reverseBoardCheckBox;
    private JPanel reverseBoardPanel;
    private JRadioButton timerRadioButton;
    private JRadioButton countdownRadioButton;
    private JPanel timerModePanel;
    private JLabel timeLimitLabel;
    private JLabel timerModeLabel;
    private JFormattedTextField timeLimitField;

    //Button Panel Section
    private JButton playButton;
    private JButton returnButton;


    public PreferencesFrame(){
        super("GameSettings");
        LoadPreferences();
        LoadPanel();

    }

    private void LoadPanel() {
        InitializedBanner();
        InitializedSettings();
        InitialzedButton();

        preferencesPanel = new JPanel(new BorderLayout());
        preferencesPanel.add(bannerPanel, BorderLayout.PAGE_START);
        preferencesPanel.add(settingsPanel, BorderLayout.CENTER);
        preferencesPanel.add(buttonPanel, BorderLayout.PAGE_END);
        preferencesPanel.setPreferredSize(new Dimension(600, 400));


        this.add(preferencesPanel);
        this.pack();
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }


    public void LoadPreferences(){
        Preferences pref = Core.getPreferences();


    }

    private void InitializedGameSettings(){
        gameSettingsPanel = new JPanel();

        reverseBoardCheckBox = new JCheckBox("Reverse board");
        reverseBoardPanel = new JPanel();
        timerModePanel = new JPanel();
        timerModeLabel = new JLabel("Timer Mode: ");
        timeLimitLabel = new JLabel("min");
        timerRadioButton = new JRadioButton("Timer");
        countdownRadioButton = new JRadioButton("CountDown");
        timeLimitField = new JFormattedTextField();
        timeLimitField.setEnabled(false);
        timeLimitField.setColumns(3);

        countdownRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLimitField.setEnabled(true);
            }
        });
        timerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLimitField.setEnabled(false);
            }
        });

        //set to consume non integer value
        timeLimitField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();  // ignore event
                }
            }
        });
        ButtonGroup matchSettingsGroup = new ButtonGroup();
        matchSettingsGroup.add(timerRadioButton);
        matchSettingsGroup.add(countdownRadioButton);
        gameSettingsPanel.setBackground(Color.LIGHT_GRAY);
        reverseBoardPanel.add(reverseBoardCheckBox,BorderLayout.WEST);
        timerModePanel.add(timerModeLabel);
        timerModePanel.add(timerRadioButton);
        timerModePanel.add(countdownRadioButton);
        timerModePanel.add(timeLimitLabel);
        timerModePanel.add(timeLimitField);
        gameSettingsPanel.add(reverseBoardPanel, BorderLayout.CENTER);
        gameSettingsPanel.add(timerModePanel, BorderLayout.PAGE_END);






    }

    private void InitializedNetworkSettings(){

    }

    private void InitializedSettings(){
        settingsPanel = new JPanel();
        InitializedGameSettings();

        settingsPanel.setBackground(Color.LIGHT_GRAY);
        settingsPanel.add(gameSettingsPanel, BorderLayout.NORTH);
        settingsPanel.setPreferredSize(new Dimension(600, 120));
    }

    public void InitializedBanner(){
        bannerPanel = new JPanel(new GridBagLayout());
        bannerPanel.setBackground(Color.CYAN);
        //constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;


        gameModePanel = new JPanel();
        notImplementedLabel = new JLabel();
        onlineButton = new JRadioButton();
        onlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notImplementedLabel = new JLabel("Not Implemented");
                bannerPanel.add(notImplementedLabel);
                enablePlay(false);
            }
        });
        offlineButton = new JRadioButton();
        offlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enablePlay(true);
            }
        });
        onlineLabel = new JLabel("Online");
        offlineLabel= new JLabel("Offline");
        ButtonGroup gameModeButtonGroup = new ButtonGroup();
        gameModeButtonGroup.add(offlineButton);
        gameModeButtonGroup.add(onlineButton);
        gameModePanel.add(onlineLabel);
        gameModePanel.add(onlineButton);
        gameModePanel.add(offlineLabel);
        gameModePanel.add(offlineButton);

        bannerPanel.add(gameModePanel, constraints);
        bannerPanel.add(notImplementedLabel);

        bannerPanel.setPreferredSize(new Dimension(600, 120));
    }

    public void InitialzedButton(){
        buttonPanel = new JPanel();
        returnButton = new JButton("Return");
        playButton = new JButton("Play");

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LaunchPage launchPage = Core.getLaunchPage();
                launchPage = new LaunchPage();
                setVisible(false);
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ValidatePreferrences()){
                    Core.StartGame();
                    //GameFrame gameFrame= Core.getGameFrame();
                    //gameFrame = new GameFrame();
                    setVisible(false);
                }
                else{
                    showIncompleteDialog();
                }

            }
        });

        playButton.setEnabled(false);

        buttonPanel.add(returnButton);
        buttonPanel.add(playButton);

    }

    public boolean ValidatePreferrences(){
        Preferences pref = Core.getPreferences();
        if (onlineButton.isSelected()){
            System.out.println("online not implemented yet, setting to offline");
            pref.setGameMode(Preferences.GameMode.OFFLINE);
        }
        if (offlineButton.isSelected()){
            pref.setGameMode(Preferences.GameMode.OFFLINE);
        }
        if(reverseBoardCheckBox.isSelected()){
            pref.setReverseBoard(true);
        }
        if(timerRadioButton.isSelected()){
            pref.setTimerMode(Preferences.TimerMode.TIMER);
        }
        if(countdownRadioButton.isSelected()){
            pref.setTimerMode(Preferences.TimerMode.COUNTDOWN);
            if(!timeLimitField.getText().isEmpty()) pref.setTimeLimit(Integer.parseInt(timeLimitField.getText()));

        }
        return pref.isPreferenceComplete();
    }


    private void enablePlay(boolean value){
        playButton.setEnabled(value);
    }

    private void showIncompleteDialog() {
        JOptionPane.showMessageDialog(this, "Please set all necessary preferences. ", "Unfinished Preferences", JOptionPane.WARNING_MESSAGE);
    }






}
