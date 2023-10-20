package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class Knight extends Piece{
    public Knight(Color color) {
        super(color);
        this.type = Type.KNIGHT;
    }

    @Override
    public boolean validateMove(Move move) {
        return false;
    }
}
