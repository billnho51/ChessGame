package ChessGame.main.pieces;

public abstract class Piece {
    //since other chess piece inherit from Piece
    public enum Color {
        WHITE, BLACK
    }

    public enum Type {
        KING, ROOK, BISHOP, QUEEN, KNIGHT, PAWN,
    }


    protected Color color;
    protected Type type;
    protected boolean capture;

    public Piece(Color color) {
        this.color = color;
        this.capture = false;
    }
    public String getImageFileName() {
        String fileName = "/pieces/";
        switch (color) {
            case WHITE:
                fileName += "white_";
                break;
            case BLACK:
                fileName += "black_";
                break;
        }
        switch (type) {
            case KING:
                fileName += "king";
                break;
            case ROOK:
                fileName += "rook";
                break;
            case BISHOP:
                fileName += "bishop";
                break;
            case QUEEN:
                fileName += "queen";
                break;
            case KNIGHT:
                fileName += "knight";
                break;
            case PAWN:
                fileName += "pawn";
                break;
        }
        fileName += ".png";
        return fileName;
    }

    public Color getColor() {
        return color;
    }
    public boolean isCapture() {
        return capture;
    }
    public void setCapture(boolean isCapture){
        this.capture = isCapture;
    }
    public Type getType() {
        return type;
    }

}
