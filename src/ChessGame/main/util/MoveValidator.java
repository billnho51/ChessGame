package ChessGame.main.util;

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
        System.out.println("moving to " + move.getDestinationFile() + move.getDestinationRank());
        if(move.getDestinationFile() < 'a' || move.getDestinationFile() > 'h' || move.getDestinationRank() < 1 || move.getDestinationRank() >8){
            System.out.println("out of bound");
            return false;
        }
        if(move.getPiece() == null){
            System.out.println("piece is null ??");
            return false;
        }
        if(move.getPiece().getColor() != SelectedPieceColor){
            System.out.println("other player turn");
            return false;
        }
        if(!move.getPiece().validateMove(move)){
            System.out.println("move not allow for this piece");
            return false;
        }

        if(move.getPiece().getColor() == Piece.Color.BLACK){
            SelectedPieceColor = Piece.Color.WHITE;
        }
        else{
            SelectedPieceColor = Piece.Color.BLACK;
        }

        return true;
    }
}
