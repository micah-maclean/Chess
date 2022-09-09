public class Bishop extends Piece {


    public Bishop(int X, int Y, boolean isWhite, Board board, String image) {

        super(X, Y, isWhite, board, image);
    }



    @Override
    public boolean isValidMove(int destinationX, int destinationY) {

        Piece inTheWay;
        int changeInX =Math.abs(this.getX() - destinationX);
        int changeInY =Math.abs(this.getY() - destinationY);

        Piece possiblePiece=board.getSquare(destinationX, destinationY);
        if(possiblePiece != null) {
            if(possiblePiece.isWhite() == this.isWhite())
            {
                return false;
            }
        }


        if(changeInX != changeInY) {
            //for a move to be diagonal the sides have to form a square
            return false;
        }

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
}
