package ChessGame.main.ui;

import ChessGame.main.pieces.Piece;
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
    private int turnCounter =1;
    private int ComponentCounter = 0;

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
        JButton previousMove = new JButton();
        previousMove.setFont(previousMove.getFont().deriveFont(15f));
        String PieceName = "";
        ///
        TitledBorder titled;
        titled = BorderFactory.createTitledBorder("");

        ////
        if (move.getPiece().getColor() == Piece.Color.WHITE){
            MoveHolder = new JPanel(new GridLayout(1,3));
            ComponentCounter = 0;

            JLabel turnLabel = new JLabel();
            turnLabel.setText(turnCounter+". ");
            turnCounter++;
            turnLabel.setBorder(titled);
            //turnLabel.setMaximumSize(new Dimension(this.getMaximumSize().width, MoveHolder.getMinimumSize().height));
            //System.out.println("turn Label Size is: "+ turnLabel.getSize());
            MoveHolder.add(turnLabel);
            JPanel temp = new JPanel(new GridLayout());
            temp.setBorder(titled);
            MoveHolder.add(temp);
            JPanel temp2 = new JPanel(new GridLayout());
            temp2.setBorder(titled);
            MoveHolder.add(temp2);
            ComponentCounter++;

        }
        switch (move.getPiece().getType()){
            case KING:
                if (move.getPiece().getColor() == Piece.Color.BLACK) PieceName = "\u265A";
                else PieceName = "\u2654";
                break;
            case ROOK:
                if (move.getPiece().getColor() == Piece.Color.BLACK) PieceName = "\u265C";
                else PieceName = "\u2656";
                break;
            case QUEEN:
                if (move.getPiece().getColor() == Piece.Color.BLACK) PieceName = "\u265B";
                else PieceName = "\u2655";
                break;
            case PAWN:
                if (move.getPiece().getColor() == Piece.Color.BLACK) PieceName = "\u265F";
                else PieceName = "\u2659";
                break;
            case BISHOP:
                if (move.getPiece().getColor() == Piece.Color.BLACK) PieceName = "\u265D";
                else PieceName = "\u2657";
                break;
            //case CANNON:
            //    if (move.getPiece().getColor() == Piece.Color.BLACK) PieceName = "\u265A";
            //    else PieceName = "\u2654 ";
            //    break;
            case KNIGHT:
                if (move.getPiece().getColor() == Piece.Color.BLACK) PieceName = "\u265E";
                else PieceName = "\u2658";
                break;
            default:
                break;
        }
        if (move.getCapturedPiece() != null){
            previousMove.setText(
                    PieceName + "x"
                            + move.getDestinationFile()
                            + move.getDestinationRank()
            );
        }
        else{
            previousMove.setText(
                    PieceName
                            + move.getDestinationFile()
                            + move.getDestinationRank()
            );
        }

        //MoveHolder = new JPanel();

        previousMove.setHorizontalAlignment(JButton.LEFT);
        //previousMove.setMaximumSize(new Dimension(this.getMaximumSize().width, MoveHolder.getMaximumSize().height));
        previousMove.setBorder(titled);

        MoveHolder.setMaximumSize(new Dimension(this.getMaximumSize().width, previousMove.getMinimumSize().height));
        JPanel temp = (JPanel) MoveHolder.getComponent(ComponentCounter);
        temp.add(previousMove);
        ComponentCounter++;
        //System.out.println("adding: " + previousMove.getText());
        MoveHistoryDisplayPanel.add(MoveHolder);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
