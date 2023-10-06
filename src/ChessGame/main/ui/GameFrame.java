package ChessGame.main.ui;

import javax.swing.*;
import ChessGame.main.ui.BoardPanel;
import ChessGame.main.util.GameManager;

import java.awt.*;

public class GameFrame extends JFrame {

    private JPanel BoardPanel;
    private GameManager gameManager;
    public GameFrame() {
        super("ChessGame");
        gameManager = new GameManager();
        this.BoardPanel = new BoardPanel(gameManager);
        BoardPanel.setPreferredSize(new Dimension(850, 850));
        this.add(BoardPanel);

        //resize page to match total component
        this.pack();
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }



}
