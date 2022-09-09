public class Rook extends Piece {

    private boolean isFirstMove;
    private boolean isCastle;

    public Rook(int X, int Y, boolean isWhite, Board board, String image) {

        super(X, Y, isWhite, board, image);
        isFirstMove = true;
        isCastle = true;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    public boolean isCastle() {
        return isCastle;
    }

    public void setCastle(boolean castle) {
        isCastle = castle;
    }

    @Override
    public boolean isValidMove(int destinationX, int destinationY) {
        Piece inTheWay;
        int changeInX =Math.abs(this.getX() - destinationX);
        int changeInY =Math.abs(this.getY() - destinationY);

        Piece possiblePiece=board.getSquare(destinationX, destinationY);
        if (possiblePiece != null) {
            if(possiblePiece.isWhite() == this.isWhite())
            {
                //if the pieces are the same colour it is not a valid move
                return false;
            }
        }




        if (changeInX > 0  && changeInY > 0) {
            //if both X and Y final have change from the initial position,
            // then the piece is not moving in line
            return false;
        }

        if (destinationY > this.getY()) {
            //going north on the board, the Y position is changing

            for(int i= 1; i < changeInY ; i++)
            {
                inTheWay=board.getSquare(this.getX(), this.getY()+i);
            if( inTheWay != null){
                //checking if their is something in the path.
                return false;
            }
            }

            this.setY(destinationY);
                        return true;
        }

        if (this.getY() > destinationY) {
            //going south on the board, the Y position is changing
            for(int i= 1; i < changeInY  ; i++)
            {

                inTheWay=board.getSquare(this.getX(), this.getY()-i);
                if(inTheWay != null){
                    //checking if their is something in the path.
                    return false;
                }
            }
            this.setY(destinationY);
                        return true;
        }

        if (destinationX > this.getX()){
            //going east on the board, the X position is changing
            for(int i= 1; i < changeInX ; i++)
            {

                inTheWay=board.getSquare(this.getX()+i, this.getY());
                if(inTheWay != null){
                    //checking if their is something in the path.
                    return false;
                }
            }

            this.setX(destinationX);
                       return true;
        }

        if(this.getX() > destinationX){
            //going west on the board, the X position is changing
            for(int i= 1; i < changeInX ; i++)
            {

                inTheWay=board.getSquare(this.getX()-i, this.getY());
                if(inTheWay != null){
                    //checking if their is something in the path.
                    return false;
                }
            }

            this.setX(destinationX);
            return true;
        }

        return false;
    }
}
