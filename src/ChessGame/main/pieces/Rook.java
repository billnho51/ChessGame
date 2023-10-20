package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class Rook extends Piece{
    public Rook(Color color) {
        super(color);
        this.type = Type.ROOK;
    }

    @Override
    public boolean validateMove(Move move) {
        return false;
    }
}
