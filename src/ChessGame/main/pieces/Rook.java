package ChessGame.main.pieces;

import ChessGame.main.util.Move;

public class Rook extends Piece{
    public Rook(Color color) {
        super(color);
        this.type = Type.ROOK;
    }

    @Override
    public boolean validateMove(Move move) {
        //normal movement
        if (move.getCapturedPiece() == null || (move.getCapturedPiece() != null && move.getCapturedPiece().getColor() != move.getPiece().getColor())){
            if (move.getDestinationRank() != move.getOriginRank()){
                System.out.println("moving vertical");
                if(move.getDestinationFile() == move.getOriginFile()) return true;
            }
            if (move.getDestinationFile() != move.getOriginFile()){
                System.out.println("moving horizontal");
                if(move.getDestinationRank() == move.getOriginRank()) return true;
            }
        }
        System.out.println("knight cant move like this");
        return false;
    }
}
