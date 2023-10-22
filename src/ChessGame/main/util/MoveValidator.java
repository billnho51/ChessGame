package ChessGame.main.util;

import ChessGame.main.Board.Board;
import ChessGame.main.pieces.Piece;

public class MoveValidator {

    private static MoveValidator instace = new MoveValidator();

    private MoveValidator() {
        SelectedPieceColor = Piece.Color.WHITE;
    }
    private static Piece.Color SelectedPieceColor;

    public static MoveValidator getInstace() {
        return instace;
    }

    public static boolean validateMove(Move move) {
        System.out.println("validate moving " + move.getOriginFile()+ move.getOriginRank() + " to "+ move.getDestinationFile() + move.getDestinationRank());
        //check if out of bound
        if(move.getDestinationFile() < 'a' || move.getDestinationFile() > 'h' || move.getDestinationRank() < 1 || move.getDestinationRank() >8){
            System.out.println("out of bound");
            return false;
        }
        //check if selecting null pieces
        if(move.getPiece() == null){
            System.out.println("piece is null ??");
            return false;
        }
        //check if correct turn
        if(move.getPiece().getColor() != SelectedPieceColor){
            System.out.println("other player turn");
            return false;
        }
        //check for pieces rules
        if(!move.getPiece().validateMove(move)){
            System.out.println("move not allow for this piece");
            return false;
        }
        if(!validateClearPath(move)) return false;

        if(move.getPiece().getColor() == Piece.Color.BLACK){
            SelectedPieceColor = Piece.Color.WHITE;
        }
        else{
            SelectedPieceColor = Piece.Color.BLACK;
        }

        return true;
    }


    private static boolean validateClearPath(Move move){
        // ignore Cannon move
        if (move.getPiece().getType().equals(Piece.Type.CANNON)) {
            return true;
        }

        int fileDirection = Integer.signum(move.getDestinationFile() - move.getOriginFile());
        int rankDirection = Integer.signum(move.getDestinationRank() - move.getOriginRank());

        // one square executeMove
        if (Math.abs(move.getDestinationFile() - move.getOriginFile()) <= 1
                && Math.abs(move.getDestinationRank() - move.getOriginRank()) <= 1) {
            return true;
        }

        // l-executeMove
        if ((Math.abs(move.getDestinationFile() - move.getOriginFile()) == 1
                && Math.abs(move.getDestinationRank() - move.getOriginRank()) == 2)
                || (Math.abs(move.getDestinationFile() - move.getOriginFile()) == 2
                && Math.abs(move.getDestinationRank() - move.getOriginRank()) == 1)) {
            return true;
        }

        // diagonal executeMove
        if (Math.abs(move.getDestinationFile() - move.getOriginFile())
                == Math.abs(move.getDestinationRank() - move.getOriginRank())) {
            for (int file = move.getOriginFile() + fileDirection, rank = move.getOriginRank() + rankDirection;
                 file != move.getDestinationFile() && rank != move.getDestinationRank();
                 file += fileDirection, rank += rankDirection) {
                if (Board.getSquare((char) file, rank).getCurrentPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // along file (different rank)
        if (move.getDestinationFile() - move.getOriginFile() == 0
                && move.getDestinationRank() - move.getOriginRank() != 0) {
            for (int rank = move.getOriginRank() + rankDirection; rank != move.getDestinationRank(); rank += rankDirection) {
                if (Board.getSquare(move.getOriginFile(), rank).getCurrentPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // along rank (different file)
        if (move.getDestinationFile() - move.getOriginFile() != 0
                && move.getDestinationRank() - move.getOriginRank() == 0) {
            for (char file = (char) (move.getOriginFile() + fileDirection); file != move.getDestinationFile(); file += fileDirection) {
                if (Board.getSquare(file, move.getOriginRank()).getCurrentPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
