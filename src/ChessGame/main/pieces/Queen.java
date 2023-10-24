package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class Queen extends Piece{
    public Queen(Color color) {
        super(color);
        this.type = Type.QUEEN;
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
            //vertical movement
            if (move.getDestinationRank() != move.getOriginRank()){
                System.out.println("moving vertical");
                if(move.getDestinationFile() == move.getOriginFile()) return true;
            }
            //horizontal movement
            if (move.getDestinationFile() != move.getOriginFile()){
                System.out.println("moving horizontal");
                if(move.getDestinationRank() == move.getOriginRank()) return true;
            }
        }
        return false;
    }
}
