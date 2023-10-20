package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class King extends Piece{
    public King(Color color) {
        super(color);
        this.type = Type.KING;
    }

    @Override
    public boolean validateMove(Move move) {
        return false;
    }
}
