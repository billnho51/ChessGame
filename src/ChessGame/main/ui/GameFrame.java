package ChessGame.main.ui;

import javax.swing.*;
import ChessGame.main.ui.BoardPanel;
import ChessGame.main.util.GameManager;

import java.awt.*;

public class GameFrame extends JFrame {

    private JSplitPane something;
    private JPanel BoardPanel;
    private JPanel GamePanel;
    private JPanel TimerPanel;
    private JPanel ControlPanel;
    private JPanel MoveHistoryPanel;
    private GameManager gameManager;
    public GameFrame(GameManager gameManager){
        super("ChessGame");
        //gameManager = new GameManager();
        this.setLayout(new BorderLayout());
        this.GamePanel = new JPanel(new GridBagLayout());
        this.BoardPanel = gameManager.getBoardPanel();
        this.TimerPanel = gameManager.getTimerPanel();
        this.MoveHistoryPanel = gameManager.getMoveHistoryPanel();
        //BoardPanel.setPreferredSize(new Dimension(800, 800));

        //Board Constraints
        GridBagConstraints BoardConstraints = new GridBagConstraints();
        BoardConstraints.anchor = GridBagConstraints.WEST;
        BoardConstraints.weightx = 1;
        BoardConstraints.weighty = 1;
        BoardConstraints.gridx = 0;
        BoardConstraints.gridy = 0;

        //Timer Constraints
        GridBagConstraints TimerConstraints = new GridBagConstraints();
        TimerConstraints.anchor = GridBagConstraints.NORTHEAST;
        TimerConstraints.weightx = 1;
        TimerConstraints.weighty = 1;
        TimerConstraints.gridx = 1;
        TimerConstraints.gridy = 0;

        //MoveHistory Constraints
        GridBagConstraints MoveHistoryConstraints = new GridBagConstraints();
        MoveHistoryConstraints.anchor = GridBagConstraints.SOUTHEAST;
        MoveHistoryConstraints.weightx = 1;
        MoveHistoryConstraints.weighty = 1;
        MoveHistoryConstraints.gridx = 1;
        MoveHistoryConstraints.gridy = 0;


        this.GamePanel.add(this.BoardPanel, BoardConstraints);
        this.GamePanel.add(this.TimerPanel, TimerConstraints);
        this.GamePanel.add(this.MoveHistoryPanel, MoveHistoryConstraints);


        this.add(GamePanel, BorderLayout.CENTER);


        //resize page to match total component
        this.pack();
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}
