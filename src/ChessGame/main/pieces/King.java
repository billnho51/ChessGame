package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class King extends Piece{
    public King(Color color) {
        super(color);
        this.type = Type.KING;
    }

    @Override
    public boolean validateMove(Move move) {
        // executeMove or capture
        if ((move.getCapturedPiece() == null)
                || (move.getCapturedPiece() != null
                && !move.getPiece().getColor().equals(move.getCapturedPiece().getColor()))) {
            // horizontal executeMove
            if (move.getDestinationFile() == move.getOriginFile() &&
                    Math.abs(move.getDestinationRank() - move.getOriginRank()) ==1) {
                return true;
            }
            // vertical executeMove
            if (move.getDestinationRank() == move.getOriginRank() &&
                    Math.abs(move.getDestinationFile() - move.getOriginFile()) ==1) {
                return true;
            }
            // diagonal executeMove
            if (Math.abs(move.getDestinationRank()- move.getOriginRank()) == 1 &&
                    Math.abs(move.getDestinationFile() - move.getOriginFile()) ==1) {
                return true;
            }
        }

        return false;
    }
}
