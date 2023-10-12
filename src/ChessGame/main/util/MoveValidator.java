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
        System.out.println("move not allowed");
        return false;
    }
}
