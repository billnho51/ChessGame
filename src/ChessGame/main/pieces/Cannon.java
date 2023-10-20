package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class Cannon extends Piece{
    public Cannon(Color color) {
        super(color);
    }

    @Override
    public boolean validateMove(Move move) {
        return false;
    }
}
