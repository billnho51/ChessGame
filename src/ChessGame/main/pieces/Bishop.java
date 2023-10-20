package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class Bishop extends Piece{

    public Bishop(Color color) {
        super(color);
        this.type = Type.BISHOP;
    }

    @Override
    public boolean validateMove(Move move) {
        return false;
    }


}
