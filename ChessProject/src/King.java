public class King extends Piece{

    private boolean isFirstMove;
    private boolean isCastle;

    public King(int X, int Y, boolean isWhite, Board board, String image) {

        super(X, Y, isWhite, board, image);
       isFirstMove = true;
       isCastle = false;
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
        Piece possiblePiece=board.getSquare(destinationX, destinationY);


        if(this.isFirstMove) {
            if (this.isWhite()) {
                if (destinationX == 7 && destinationY == 7 ) {

                    if (((Rook) possiblePiece).isFirstMove()) {
                        if(board.getSquare(7,5) == null && board.getSquare(7,6) == null) {

                            possiblePiece.setY(5);
                            ((Rook) possiblePiece).setCastle(true);

                            System.out.println( " x " + destinationX + ' ' + " y " + destinationY + ' ');

                            this.setY(destinationY);
                            this.setCastle(true);

                            return true;
                        }
                    }

                }
                if(destinationX == 7  && destinationY == 0){
                    if (((Rook) possiblePiece).isFirstMove()) {
                        if(board.getSquare(7,1) == null && board.getSquare(7,2) == null && board.getSquare(7,3) == null) {

                            possiblePiece.setY(3);
                            ((Rook) possiblePiece).setCastle(true);

                            System.out.println( " x " + destinationX + ' ' + " y " + destinationY + ' ');

                            this.setY(destinationY);
                            this.setCastle(true);

                            return true;
                        }
                    }
                }
            } else {
                if (destinationX == 0 && destinationY == 7) {
                    if (((Rook) possiblePiece).isFirstMove()) {
                        if(board.getSquare(0,5) == null && board.getSquare(0,6) == null) {

                            possiblePiece.setY(5);
                            ((Rook) possiblePiece).setCastle(true);

                            System.out.println( " x " + destinationX + ' ' + " y " + destinationY + ' ');

                            this.setY(destinationY);
                            this.setCastle(true);

                            return true;
                        }
                    }

                }

                if(destinationX == 0 && destinationY == 0){

                    if (((Rook) possiblePiece).isFirstMove()) {
                        if(board.getSquare(0,1) == null && board.getSquare(0,2) == null && board.getSquare(0,3) == null) {

                            possiblePiece.setY(3);
                            ((Rook) possiblePiece).setCastle(true);

                            System.out.println( " x " + destinationX + ' ' + " y " + destinationY + ' ');

                            this.setY(destinationY);
                            this.setCastle(true);

                            return true;
                        }
                    }
                }
            }

        }

        if (possiblePiece != null) {
            if(possiblePiece.isWhite() == this.isWhite())
            {
                return false;
            }
        }

        int changeInX =Math.abs(this.getX() - destinationX);
        int changeInY =Math.abs(this.getY() - destinationY);

        if (changeInY == 1 && changeInX == 0){
            this.setY(destinationY);
            if(possiblePiece != null){
                possiblePiece.setKilled(true);
            }

            return true;
        }

        if (changeInX == 1 && changeInY == 0){
            this.setX(destinationX);
            if(possiblePiece != null){
                possiblePiece.setKilled(true);
            }
            return true;
        }

        if(changeInX == 1 && changeInY == 1){
            this.setX(destinationX);
            this.setY(destinationY);
            if(possiblePiece != null){
                possiblePiece.setKilled(true);
            }
            return true;
        }


        return false;
    }
}
