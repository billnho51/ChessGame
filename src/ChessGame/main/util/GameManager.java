package ChessGame.main.util;

import ChessGame.main.Board.Board;
import ChessGame.main.pieces.Piece;
import ChessGame.main.ui.BoardPanel;
import ChessGame.main.ui.GameFrame;
import ChessGame.main.ui.MoveHistoryPanel;
import ChessGame.main.ui.TimerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager {
    private Preferences preferences;

    private GameFrame gameFrame;
    private BoardPanel boardPanel;

    private TimerPanel timerPanel;
    //private ControlPanel controlPanel;
    private MoveHistoryPanel moveHistoryPanel;
    //private WaitingDialog waitingDialog;

    private Timer whiteTimer;
    private Timer blackTimer;

    //private NetworkHandler networkHandler;
    private String opponentName;

    public GameManager(Preferences pref) {
        this.preferences = pref;
        initialize();
    }

    private void initialize() {
        initializeUIComponents();
        initializeTimers();

    }

    private void initializeTimers() {
        whiteTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerPanel.UpdateTimeWhite();
            }
        });
        blackTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerPanel.UpdateTimeBlack();
            }
        });
    }

    private void initializeUIComponents() {
        boardPanel = new BoardPanel(this);
        timerPanel = new TimerPanel(this);
        moveHistoryPanel = new MoveHistoryPanel(this);
        gameFrame = new GameFrame(this);
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
        Move move = new Move(originFile, originRank, destinationFile, destinationRank);
        //executeMove(move);
        if (MoveValidator.validateMove(move)) {
            System.out.println(move.getCapturedPiece());
            executeMove(move);

        } else {
            System.out.println("move not allowed");

        }
    }

    private void executeMove(Move move) {
        //move.SetupMoveAfterValidate();
        //MoveLogger.addMove(move);
        Board.executeMove(move);
        moveHistoryPanel.PrintMove(move);
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
    public TimerPanel getTimerPanel(){
        return timerPanel;
    };

    public MoveHistoryPanel getMoveHistoryPanel(){
        return moveHistoryPanel;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }
}
