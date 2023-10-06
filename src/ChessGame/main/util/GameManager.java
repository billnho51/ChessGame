package ChessGame.main.util;

import ChessGame.main.Board.Board;
import ChessGame.main.pieces.Piece;
import ChessGame.main.ui.BoardPanel;
import ChessGame.main.ui.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager {
    private Preferences preferences;

    private GameFrame gameFrame;
    private BoardPanel boardPanel;
    //private ControlPanel controlPanel;
    //private MoveHistoryPanel moveHistoryPanel;
    //private WaitingDialog waitingDialog;

    private Timer whiteTimer;
    private Timer blackTimer;

    //private NetworkHandler networkHandler;
    private String opponentName;

    public GameManager() {
        this.preferences = Core.getPreferences();
        initialize();
    }

    private void initialize() {
        //initializeTimers();
        //initializeUIComponents();
    }


    public void onMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        switch (preferences.getGameMode()) {
            case ONLINE:
                //onOutboundRemoteMoveRequest(originFile, originRank, destinationFile, destinationRank);
                break;
            case OFFLINE:
                onLocalMoveRequest(originFile, originRank, destinationFile, destinationRank);
                break;
        }
    }

    private void onLocalMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        System.out.println("hello??");
        Move move = new Move(originFile, originRank, destinationFile, destinationRank);
        executeMove(move);
        //if (MoveValidator.validateMove(move)) {
        //    executeMove(move);
        //} else {
            //
        //}
    }

    private void executeMove(Move move) {
        //MoveLogger.addMove(move);
        Board.executeMove(move);
        //moveHistoryPanel.printMove(move);
        boardPanel.executeMove(move);
        switchTimer(move);
        //if (MoveValidator.isCheckMove(move)) {
        //    if (MoveValidator.isCheckMate(move)) {
                // checkmate
        //    }
            // check
        //    gameFrame.showCheckDialog();
        //}
    }


    public Piece queryPiece(char file, int rank) {
        return Board.getSquare(file, rank).getCurrentPiece();
    }


    private void switchTimer(Move move) {
        if (move.getPiece().getColor().equals(Piece.Color.BLACK)) {
            whiteTimer.start();
            blackTimer.stop();
        } else {
            blackTimer.start();
            whiteTimer.stop();
        }
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }
}
