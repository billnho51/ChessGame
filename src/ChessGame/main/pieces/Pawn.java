package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class Pawn extends Piece{
    public Pawn(Color color) {
        super(color);
        this.type = Type.PAWN;
    }

    @Override
    public boolean validateMove(Move move) {
        // executeMove or capture
        if ((move.getCapturedPiece() == null)
                || (move.getCapturedPiece() != null
                && !move.getPiece().getColor().equals(move.getCapturedPiece().getColor()))) {
            // diagonal executeMove
            if (Math.abs(move.getDestinationFile() - move.getOriginFile())
                    == Math.abs(move.getDestinationRank() - move.getOriginRank())) {
                return true;
            }
        }

        // all other cases
        return false;
    }
}
