package ChessGame.main.util;

import ChessGame.main.Board.Board;
import ChessGame.main.pieces.Piece;
import ChessGame.main.pieces.PieceSet;

public class MoveValidator {

    private static MoveValidator instace = new MoveValidator();

    private MoveValidator() {
        SelectedPieceColor = Piece.Color.WHITE;
    }
    private static Piece.Color SelectedPieceColor;

    public static MoveValidator getInstace() {
        return instace;
    }

    public static boolean validateMove(Move move) {
        //System.out.println("validate moving " + move.getOriginFile()+ move.getOriginRank() + " to "+ move.getDestinationFile() + move.getDestinationRank());
        //check if out of bound
        if(move.getDestinationFile() < 'a' || move.getDestinationFile() > 'h' || move.getDestinationRank() < 1 || move.getDestinationRank() >8){
            //System.out.println("out of bound");
            return false;
        }
        //check if selecting null pieces
        if(move.getPiece() == null){
            //System.out.println("piece is null ??");
            return false;
        }
        //check if correct turn
        if(move.getPiece().getColor() != SelectedPieceColor){
            //System.out.println("other player turn");
            return false;
        }
        //check for pieces rules
        if(!move.getPiece().validateMove(move)){
            //System.out.println("move not allow for this piece");
            return false;
        }
        if(!validateClearPath(move)) return false;

        if(isCheckMate(move)) System.out.println("king is checkmate");

        if(move.getPiece().getColor() == Piece.Color.BLACK){
            SelectedPieceColor = Piece.Color.WHITE;
        }
        else{
            SelectedPieceColor = Piece.Color.BLACK;
        }
        return true;
    }

    /**
     * Check if the move is execute would cause a checkmate
     * @param move a check execute move
     * @return whether move would lead to checkmate for targeting king
     */
    private static boolean isCheckMate(Move move){
        Piece piece = move.getPiece();
        boolean check = false;
        boolean checkmate = false;

        char OponentKingFile = PieceSet.getOpponentKingFile(move.getPiece().getColor());
        int OponentKingRank = PieceSet.getOpponentKingRank(move.getPiece().getColor());
        Piece OponentKing = Board.getSquare(OponentKingFile,OponentKingRank).getCurrentPiece();

        if(validateKingCheck(move, 0, 0)){

            check = true;
            if (!validateKingCheck(move, -1, 1)) checkmate = true;
            if (!validateKingCheck(move, 0, 1))checkmate = true;
            if (!validateKingCheck(move, 1, 1))checkmate = true;
            if (!validateKingCheck(move, -1, 0))checkmate = true;
            if (!validateKingCheck(move, 1, 0))checkmate = true;
            if (!validateKingCheck(move, -1, -1))checkmate = true;
            if (!validateKingCheck(move, 0, -1))checkmate = true;
            if (!validateKingCheck(move, 1, -1))checkmate = true;
        }
        else{
            check = false;
        }

        if (check && checkmate){
            System.out.println("king is checkmate");
        }
        else if (check && !checkmate){
            System.out.println("king is in check");
        }

        return checkmate;
    }

    /**
     * Check whether King is being Check based on move
     * @param move
     * @param fileDiff
     * @param rankDiff
     * @return
     */
    private static boolean validateKingCheck(Move move, int fileDiff, int rankDiff){

        if ((PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff) > 'h'
        || (PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff) < 'a'
        || (PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff) < 1
        ||(PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff) > 8){
            System.out.println("out of bound at: " + (char)(PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff)+(PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff));
            return false;
        }

        if (move.getPiece().getType() == Piece.Type.BISHOP){
            Move tempMove = new Move(
                    move.getPiece(),
                    move.getDestinationFile(),
                    move.getDestinationRank(),
                    (char)(PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff),
                    PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff
                    //PieceSet.getOpponentKingFile(move.getPiece().getColor()),
                    //PieceSet.getOpponentKingRank(move.getPiece().getColor())
            );
            if (move.getPiece().validateMove(tempMove) && validateClearPath(tempMove)){
                System.out.println("bishop check");
                return true;
            }
        }
        if (move.getPiece().getType() == Piece.Type.CANNON){
            Move tempMove = new Move(
                    move.getPiece(),
                    move.getDestinationFile(),
                    move.getDestinationRank(),
                    (char)(PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff),
                    PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff
                    //PieceSet.getOpponentKingFile(move.getPiece().getColor()),
                    //PieceSet.getOpponentKingRank(move.getPiece().getColor())
            );
            if (move.getPiece().validateMove(tempMove) && validateClearPath(tempMove)){
                System.out.println("Cannon check");
                return true;
            }
        }
        if (move.getPiece().getType() == Piece.Type.PAWN){
            Move tempMove = new Move(
                    move.getPiece(),
                    move.getDestinationFile(),
                    move.getDestinationRank(),
                    (char)(PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff),
                    PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff
                    //PieceSet.getOpponentKingFile(move.getPiece().getColor()),
                    //PieceSet.getOpponentKingRank(move.getPiece().getColor())
            );
            if (move.getPiece().validateMove(tempMove) && validateClearPath(tempMove)){
                System.out.println("Pawn check");
                return true;
            }
        }
        if (move.getPiece().getType() == Piece.Type.KNIGHT){
            Move tempMove = new Move(
                    move.getPiece(),
                    move.getDestinationFile(),
                    move.getDestinationRank(),
                    (char)(PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff),
                    PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff
                    //PieceSet.getOpponentKingFile(move.getPiece().getColor()),
                    //PieceSet.getOpponentKingRank(move.getPiece().getColor())
            );
            if (move.getPiece().validateMove(tempMove) && validateClearPath(tempMove)){
                System.out.println("Knight check");
                return true;
            }
        }
        if (move.getPiece().getType() == Piece.Type.ROOK){
            Move tempMove = new Move(
                    move.getPiece(),
                    move.getDestinationFile(),
                    move.getDestinationRank(),
                    (char)(PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff),
                    PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff
                    //PieceSet.getOpponentKingFile(move.getPiece().getColor()),
                    //PieceSet.getOpponentKingRank(move.getPiece().getColor())
            );
            if (move.getPiece().validateMove(tempMove) && validateClearPath(tempMove)){
                System.out.println("Rook check");
                return true;
            }
        }
        if (move.getPiece().getType() == Piece.Type.QUEEN){
            Move tempMove = new Move(
                    move.getPiece(),
                    move.getDestinationFile(),
                    move.getDestinationRank(),
                    (char)(PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff),
                    PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff
                    //PieceSet.getOpponentKingFile(move.getPiece().getColor()),
                    //PieceSet.getOpponentKingRank(move.getPiece().getColor())
            );
            if (move.getPiece().validateMove(tempMove) && validateClearPath(tempMove)){
                System.out.println("Queen check");
                return true;
            }
        }
        if (move.getPiece().getType() == Piece.Type.KING){
            Move tempMove = new Move(
                    move.getPiece(),
                    move.getDestinationFile(),
                    move.getDestinationRank(),
                    (char)(PieceSet.getOpponentKingFile(move.getPiece().getColor()) + fileDiff),
                    PieceSet.getOpponentKingRank(move.getPiece().getColor()) + rankDiff
                    //PieceSet.getOpponentKingFile(move.getPiece().getColor()),
                    //PieceSet.getOpponentKingRank(move.getPiece().getColor())
            );
            if (move.getPiece().validateMove(tempMove) && validateClearPath(tempMove)){
                System.out.println("King check");
                return true;
            }
        }
        return false;
    }

    /**
     * After the move direction type is validated, check for any pieces blocking between origin and destination
     * @param move
     * @return whether the path between origin and destination is clear
     */
    private static boolean validateClearPath(Move move){
        // ignore Cannon move
        if (move.getPiece().getType().equals(Piece.Type.CANNON)) {
            return true;
        }

        int fileDirection = Integer.signum(move.getDestinationFile() - move.getOriginFile());
        int rankDirection = Integer.signum(move.getDestinationRank() - move.getOriginRank());

        // one square executeMove
        if (Math.abs(move.getDestinationFile() - move.getOriginFile()) <= 1
                && Math.abs(move.getDestinationRank() - move.getOriginRank()) <= 1) {
            return true;
        }

        // l-executeMove
        if ((Math.abs(move.getDestinationFile() - move.getOriginFile()) == 1
                && Math.abs(move.getDestinationRank() - move.getOriginRank()) == 2)
                || (Math.abs(move.getDestinationFile() - move.getOriginFile()) == 2
                && Math.abs(move.getDestinationRank() - move.getOriginRank()) == 1)) {
            return true;
        }

        // diagonal executeMove
        if (Math.abs(move.getDestinationFile() - move.getOriginFile())
                == Math.abs(move.getDestinationRank() - move.getOriginRank())) {
            for (int file = move.getOriginFile() + fileDirection, rank = move.getOriginRank() + rankDirection;
                 file != move.getDestinationFile() && rank != move.getDestinationRank();
                 file += fileDirection, rank += rankDirection) {
                if (Board.getSquare((char) file, rank).getCurrentPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // along file (different rank)
        if (move.getDestinationFile() - move.getOriginFile() == 0
                && move.getDestinationRank() - move.getOriginRank() != 0) {
            for (int rank = move.getOriginRank() + rankDirection; rank != move.getDestinationRank(); rank += rankDirection) {
                if (Board.getSquare(move.getOriginFile(), rank).getCurrentPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // along rank (different file)
        if (move.getDestinationFile() - move.getOriginFile() != 0
                && move.getDestinationRank() - move.getOriginRank() == 0) {
            for (char file = (char) (move.getOriginFile() + fileDirection); file != move.getDestinationFile(); file += fileDirection) {
                if (Board.getSquare(file, move.getOriginRank()).getCurrentPiece() != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
