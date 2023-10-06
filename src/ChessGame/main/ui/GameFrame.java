package ChessGame.main.ui;

import javax.swing.*;
import ChessGame.main.ui.BoardPanel;
import ChessGame.main.util.GameManager;

import java.awt.*;

public class GameFrame extends JFrame {

    private JPanel BoardPanel;
    private JPanel TimerPanel;
    private JPanel ControlPanel;
    private JPanel moveHistoryPanel;
    private GameManager gameManager;
    public GameFrame(GameManager gameManager){
        super("ChessGame");
        //gameManager = new GameManager();
        this.BoardPanel = gameManager.getBoardPanel();
        BoardPanel.setPreferredSize(new Dimension(850, 850));
        this.add(BoardPanel);

        //resize page to match total component
        this.pack();
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }



}
