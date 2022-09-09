public class Knight extends Piece {

    public Knight(int X, int Y, boolean isWhite, Board board, String image) {

        super(X, Y, isWhite, board, image);
    }

    @Override
    public boolean isValidMove(int destinationX, int destinationY) {
        Piece possiblePiece= board.getSquare(destinationX, destinationY);

        if (possiblePiece != null) {
            if(possiblePiece.isWhite() == this.isWhite())
            {
                return false;
            }
        }


        int changeInX= Math.abs(this.getX() - destinationX);
        int changeInY= Math.abs(this.getY() - destinationY);

        if(changeInX == 1 && changeInY == 2){

            this.setX(destinationX);
            this.setY(destinationY);

                  return true;
        }

        if(changeInY == 1 && changeInX == 2){
            this.setX(destinationX);
            this.setY(destinationY);

            return true;
        }

        return false;

    }
}
