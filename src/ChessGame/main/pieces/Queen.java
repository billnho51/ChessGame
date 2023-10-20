package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class Queen extends Piece{
    public Queen(Color color) {
        super(color);
        this.type = Type.QUEEN;
    }

    @Override
    public boolean validateMove(Move move) {
        return false;
    }
}
