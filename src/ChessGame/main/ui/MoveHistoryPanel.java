package ChessGame.main.ui;

import ChessGame.main.util.GameManager;
import ChessGame.main.util.Move;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MoveHistoryPanel extends JPanel implements Observer {
    GameManager gameManager;
    private JPanel MoveHistoryDisplayPanel;
    private JPanel MoveHolder;

    public MoveHistoryPanel (GameManager gameManager){
        this.gameManager = gameManager;
        this.setPreferredSize(new Dimension(320,500));
        TitledBorder titled;
        titled = BorderFactory.createTitledBorder("Move");
        titled.setTitleFont(titled.getTitleFont().deriveFont(15f));
        this.setBorder(titled);
        InitializedUI();

    }

    private void InitializedUI(){
        this.MoveHistoryDisplayPanel = new JPanel();
        this.MoveHistoryDisplayPanel.setLayout(new BoxLayout(MoveHistoryDisplayPanel,BoxLayout.Y_AXIS));
        this.MoveHistoryDisplayPanel.setPreferredSize(new Dimension(300,490));
        //this.MoveHistoryDisplayPanel = new JPanel();
        this.add(MoveHistoryDisplayPanel, BorderLayout.CENTER);
    }

    public void PrintMove(Move move){
        JLabel previousMove = new JLabel();
        previousMove.setText(move.getPiece().getColor().name()
                +" "
                + move.getPiece().getType().name()
                + ": "
                + move.getOriginFile()
                + move.getOriginRank()
                + " to " + move.getDestinationFile()
                + move.getDestinationRank()
        );
        MoveHolder = new JPanel(new BorderLayout());
        MoveHolder.add(previousMove, BorderLayout.WEST);
        MoveHistoryDisplayPanel.add(previousMove);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
