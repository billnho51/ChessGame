package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class Shield extends Piece{
    public Shield(Color color) {
        super(color);
    }

    @Override
    public boolean validateMove(Move move) {
        return false;
    }
}
