package ChessGame.main.ui;

import ChessGame.main.pieces.Piece;
import ChessGame.main.pieces.PieceSet;
import ChessGame.main.util.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class BoardPanel extends JPanel implements Observer {
    public static final int SQUARE_DIMENSION = 100;
    private GameManager gameManager;



    private boolean boardReversed;
    private JLayeredPane boardLayeredPane;
    private JPanel boardPanel;
    private JPanel[][] squarePanels;
    public BoardPanel(GameManager gameManager){
        this.gameManager = gameManager;
        this.boardReversed = gameManager.getPreferences().isReverseBoard();
        InitializedBoardPanel();
        InitializedChessBoard();
        initializePieces();


    }

    /**
     * Submit a move request to the GameModel, called by the PieceDragAndDropListener
     *
     * @param originFile
     * @param originRank
     * @param destinationFile
     * @param destinationRank
     */
    public void submitMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        //System.out.println("submitting move");
        //pre-check for valid move
        if (getSquarePanel(originFile, originRank).getComponentCount() != 0) {
            getSquarePanel(originFile, originRank).getComponent(0).setVisible(true);
            gameManager.onMoveRequest(originFile, originRank, destinationFile, destinationRank);
        }
        else{
            System.out.println("theres no piece on selected square");
        }
    }

    /**
     * Execute the move on the board, called by the GameModel
     *
     * @param move A validated Move object
     */
    public void executeMove(Move move) {
        //System.out.println("moving piece ??");
        JPanel originSquarePanel = getSquarePanel(move.getOriginFile(), move.getOriginRank());
        JPanel destinationSquarePanel = getSquarePanel(move.getDestinationFile(), move.getDestinationRank());
        destinationSquarePanel.removeAll();
        destinationSquarePanel.add(originSquarePanel.getComponent(0));
        destinationSquarePanel.repaint();
        originSquarePanel.removeAll();
        originSquarePanel.repaint();
    }

    /**
     * Execute an undo of the given move, called by the GameModel
     *
     * @param move A validated Move object of the last move just made
     */
    public void executeUndo(Move move) {
        JPanel originSquarePanel = getSquarePanel(move.getOriginFile(), move.getOriginRank());
        JPanel destinationSquarePanel = getSquarePanel(move.getDestinationFile(), move.getDestinationRank());
        originSquarePanel.add(destinationSquarePanel.getComponent(0));
        destinationSquarePanel.removeAll();
        if (move.getCapturedPiece() != null) {
            destinationSquarePanel.add(getPieceImageLabel(move.getCapturedPiece()));
        }
        originSquarePanel.repaint();
        destinationSquarePanel.repaint();
    }

    /**
     * Prepare for the drag effect by adding an image label of the dragged piece to the drag layer
     *
     * @param originFile The index of the square on the board
     * @param originRank The index of the square on the board
     * @param dragX The coordinates where the drag begins
     * @param dragY The coordinates where the drag begins
     */
    public void preDrag(char originFile, int originRank, int dragX, int dragY) {
        //System.out.println("pre dragging");
        Piece originPiece = gameManager.queryPiece(originFile, originRank);
        //setting piece for drag
        if (originPiece != null) {
            getSquarePanel(originFile, originRank).getComponent(0).setVisible(false);
            JLabel draggedPieceImageLabel = getPieceImageLabel(originPiece);
            draggedPieceImageLabel.setLocation(dragX, dragY);
            draggedPieceImageLabel.setSize(SQUARE_DIMENSION, SQUARE_DIMENSION);
            boardLayeredPane.add(draggedPieceImageLabel, JLayeredPane.DRAG_LAYER);
        }
    }

    /**
     * Execute a drag effect by moving the dragged piece's image label, called by the PieceDragAndDropListener
     *
     * @param dragX the new coordinates of the dragged piece's image label
     * @param dragY the new coordinates of the dragged piece's image label
     */
    public void executeDrag(int dragX, int dragY) {
        if(boardLayeredPane.getComponentsInLayer(JLayeredPane.DRAG_LAYER).length == 0) return;
        JLabel draggedPieceImageLabel = (JLabel) boardLayeredPane.getComponentsInLayer(JLayeredPane.DRAG_LAYER)[0];
        if (draggedPieceImageLabel != null) {
            draggedPieceImageLabel.setLocation(dragX, dragY);
        }
    }

    /**
     * Remove the dragged piece's image label from the drag layer, called by the PieceDragAndDropListener
     */
    public void postDrag() {
        if(!IsSelectingPiece()) return;
        JLabel draggedPieceImageLabel = (JLabel) boardLayeredPane.getComponentsInLayer(JLayeredPane.DRAG_LAYER)[0];
        boardLayeredPane.remove(draggedPieceImageLabel);
        boardLayeredPane.repaint();
    }

    public boolean IsSelectingPiece(){
        if(boardLayeredPane.getComponentsInLayer(JLayeredPane.DRAG_LAYER).length == 0) return false;
        return true;
    }

    //draw each panel and add to the board
    //since GridLayout is populated left - right , up - down keep adding till reach requirements
    private void InitializedSingleSquarePanel(int f, int r){
        squarePanels[f][r] = new JPanel(new GridLayout(1, 1));
        squarePanels[f][r].setPreferredSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
        squarePanels[f][r].setSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
        squarePanels[f][r].setBackground(f % 2 == r % 2 ? Color.getHSBColor(89/360.0f, .4f, .55f) : Color.getHSBColor(60/360.0f,0.09f,0.92f));
        boardPanel.add(squarePanels[f][r]);
    }

    public JPanel getSquarePanel(char file, int rank) {
        file = Character.toLowerCase(file);
        if (file < 'a' || file > 'h' || rank < 1 || rank > 8) {
            return null;
        } else {
            return squarePanels[file - 'a'][rank - 1];
        }
    }

    private void initializePieces() {{
        // pawns
        Iterator<Piece> whitePawnsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.PAWN).iterator();
        Iterator<Piece> blackPawnsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.PAWN).iterator();
        for (char file = 'a'; file <= 'h'; file++) {
            getSquarePanel(file, 2).add(getPieceImageLabel(whitePawnsIterator.next()));
            getSquarePanel(file, 7).add(getPieceImageLabel(blackPawnsIterator.next()));
        }

        // rooks
        Iterator<Piece> whiteRooksIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.ROOK).iterator();
        Iterator<Piece> blackRooksIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.ROOK).iterator();
        getSquarePanel('a', 1).add(getPieceImageLabel(whiteRooksIterator.next()));
        getSquarePanel('h', 1).add(getPieceImageLabel(whiteRooksIterator.next()));
        getSquarePanel('a', 8).add(getPieceImageLabel(blackRooksIterator.next()));
        getSquarePanel('h', 8).add(getPieceImageLabel(blackRooksIterator.next()));

        // knights
        Iterator<Piece> whiteKnightsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.KNIGHT).iterator();
        Iterator<Piece> blackKnightsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.KNIGHT).iterator();
        getSquarePanel('b', 1).add(getPieceImageLabel(whiteKnightsIterator.next()));
        getSquarePanel('g', 1).add(getPieceImageLabel(whiteKnightsIterator.next()));
        getSquarePanel('b', 8).add(getPieceImageLabel(blackKnightsIterator.next()));
        getSquarePanel('g', 8).add(getPieceImageLabel(blackKnightsIterator.next()));

        // bishops
        Iterator<Piece> whiteBishopsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.BISHOP).iterator();
        Iterator<Piece> blackBishopsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.BISHOP).iterator();
        getSquarePanel('c', 1).add(getPieceImageLabel(whiteBishopsIterator.next()));
        getSquarePanel('f', 1).add(getPieceImageLabel(whiteBishopsIterator.next()));
        getSquarePanel('c', 8).add(getPieceImageLabel(blackBishopsIterator.next()));
        getSquarePanel('f', 8).add(getPieceImageLabel(blackBishopsIterator.next()));

        // queens
        Iterator<Piece> whiteQueenIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.QUEEN).iterator();
        Iterator<Piece> blackQueenIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.QUEEN).iterator();
        getSquarePanel('d', 1).add(getPieceImageLabel(whiteQueenIterator.next()));
        getSquarePanel('d', 8).add(getPieceImageLabel(blackQueenIterator.next()));

        // kings
        Iterator<Piece> whiteKingIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.KING).iterator();
        Iterator<Piece> blackKingIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.KING).iterator();
        getSquarePanel('e', 1).add(getPieceImageLabel(whiteKingIterator.next()));
        getSquarePanel('e', 8).add(getPieceImageLabel(blackKingIterator.next()));
    }}
    private void InitializedChessBoard(){
        squarePanels = new JPanel[8][8];
        if (boardReversed) {
            for (int r = 0; r < 8; r ++) {
                for (int f = 7; f >= 0; f--) {
                    InitializedSingleSquarePanel(f, r);
                }
            }
        } else {
            for (int r = 7; r >= 0; r --) {
                for (int f = 0; f < 8; f++) {
                    InitializedSingleSquarePanel(f, r);
                }
            }
        }
    }

    private void InitializedBoardPanel(){
        boardPanel = new JPanel(new GridLayout(8, 8));
        boardPanel.setBounds(0, 0, 800, 800);
        boardLayeredPane = new JLayeredPane();
        boardLayeredPane.setPreferredSize(new Dimension(800, 800));
        boardLayeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
        PieceDragandDropListener pieceDragAndDropListener = new PieceDragandDropListener(this);
        boardLayeredPane.addMouseListener(pieceDragAndDropListener);
        boardLayeredPane.addMouseMotionListener(pieceDragAndDropListener);
        boardLayeredPane.setVisible(true);
        this.add(boardLayeredPane, BorderLayout.CENTER);
    }

    public boolean isBoardReversed() {
        return boardReversed;
    }

    private JLabel getPieceImageLabel(Piece piece) {
        Image pieceImage = new ImageIcon(getClass().getResource(piece.getImageFileName())).getImage();
        pieceImage = pieceImage.getScaledInstance(SQUARE_DIMENSION, SQUARE_DIMENSION, Image.SCALE_SMOOTH);
        JLabel pieceImageLabel = new JLabel(new ImageIcon(pieceImage));
        return pieceImageLabel;
    }
    @Override
    public void update(Observable o, Object arg) {
        executeMove((Move) arg);
    }
}
