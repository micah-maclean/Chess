import javax.swing.*;

public  abstract class Piece {

    private int X;
    private int Y;

    private boolean isWhite;
    private boolean isKilled;

    protected Board board;
    private ImageIcon image;




    public Piece(int X, int Y, boolean isWhite, Board board, String image) {
        this.X= X;
        this.Y= Y;
        this.isWhite= isWhite;
        isKilled= false;
        this.board= board;
        this.image= new ImageIcon(image);
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public ImageIcon getImage() {
        return image;
    }

    public Board getBoard() {
        return board;
    }

    public void setImage(String image) {
        this.image = new ImageIcon(image);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public  abstract boolean isValidMove(int destinationX, int destinationY);

}

