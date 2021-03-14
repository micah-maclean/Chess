public class Queen extends Piece {

    public Queen(int X, int Y, boolean isWhite, Board board, String image) {

        super(X, Y, isWhite, board, image);
    }


    @Override
    public boolean isValidMove(int destinationX, int destinationY){
        int changeInX =Math.abs(this.getX() - destinationX);
        int changeInY =Math.abs(this.getY() - destinationY);
        Piece inTheWay;

        Piece possiblePiece=board.getSquare(destinationX , destinationY);
        if (possiblePiece != null) {
            if(possiblePiece.isWhite() == this.isWhite())
            {
                return false;
            }
        }


        if(changeInX == changeInY) {

            if(this.getX() > destinationX && this.getY() > destinationY) {
                //going southWest
                for(int i= 1; i < changeInX; i++){

                    inTheWay=board.getSquare(this.getX() -i, this.getY() -i);

                    if(inTheWay != null){
                        return false;
                    }

                }
            }
            else if(this.getX() > destinationX && destinationY > this.getY()){
                //going northWest
                for(int i= 1; i < changeInX; i++){

                    inTheWay=board.getSquare(this.getX() -i, this.getY() +i);

                    if(inTheWay != null){
                        return false;
                    }

                }
            }
            else if(destinationX > this.getX() && destinationY > this.getY() ){
                //going northEast
                for(int i= 1; i < changeInX; i++){

                    inTheWay=board.getSquare(this.getX() +i , this.getY() +i);

                    if(inTheWay != null){
                        System.out.println("X:"+(this.getX()+i) +" & Y:" + (this.getY() +i));
                        //checking if their is something in the path.
                        return false;
                    }

                }
            }
            else if(destinationX > this.getX() && this.getY() > destinationY){
                //going southEast
                for(int i= 1; i < changeInX; i++){

                    inTheWay=board.getSquare(this.getX() +i, this.getY() -i);

                    if(inTheWay != null){
                        return false;
                    }

                }
            }

            this.setX(destinationX);
            this.setY(destinationY);
            return true;
        }

        if(changeInX== 0) {
            if (destinationY > this.getY()) {
                //going north on the board, the Y position is changing

                for (int i = 1; i < changeInY; i++) {

                    if (board.getSquare(this.getX(), this.getY() + i) != null) {
                        //checking if their is something in the path.
                        return false;
                    }
                }

                this.setY(destinationY);
                return true;
            }

            if (this.getY() > destinationY) {
                //going south on the board, the Y position is changing
                for (int i = 1; i < changeInY; i++) {
                    if (board.getSquare(this.getX(), this.getY() - i) != null) {
                        //checking if their is something in the path.
                        return false;
                    }
                }

                this.setY(destinationY);
                                return true;
            }
        }

        if(changeInY == 0){
            if (destinationX > this.getX()){
                //going east on the board, the X position is changing
                for(int i= 1; i < changeInX ; i++)
                {
                    if(board.getSquare(this.getX()+i, this.getY()) != null){
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
                    if(board.getSquare(this.getX()-i, this.getY()) != null){
                        //checking if their is something in the path.
                        return false;
                    }
                }

                this.setX(destinationX);
                               return true;
            }
        }



        return false;

    }
}
