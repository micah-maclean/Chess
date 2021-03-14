public class Pawn extends Piece {

    private boolean isFirstMove;
    private boolean isPromotion;

    public Pawn(int X, int Y, boolean isWhite, Board board, String image) {

        super(X, Y, isWhite, board, image);
        isFirstMove = true;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public void setPromotion(boolean promotion) {
        isPromotion = promotion;
    }

    @Override
    public boolean isValidMove(int destinationX, int destinationY) {

        this.setPromotion(false);

        Piece possiblePiece = board.getSquare(destinationX , destinationY);
        int changeInX =destinationX - this.getX();
        int changeInY =Math.abs(this.getY() - destinationY);


        if(this.isWhite()){

            if(destinationX == 0){
               this.setPromotion(true);
            }

        }
        else{

            if(destinationX == 7){
                this.setPromotion(true);
            }

        }

        if (possiblePiece != null) {
            if(possiblePiece.isWhite() == this.isWhite())
            {
                return false;
            }

            if(this.isWhite()){
                if( changeInX == -1 && changeInY == 1) {
                    //piece eating
                    this.setX(destinationX);
                    this.setY(destinationY);
                    return true;
                }
            }
            else{
                if(changeInX == 1 && changeInY == 1) {
                    //piece eating
                    this.setX(destinationX);
                    this.setY(destinationY);
                    return true;
                }
            }

            return false;
        }

        if(changeInY>0){
            return false;
        }

        if(this.isWhite()){

            if (this.isFirstMove && (changeInX == -2 || changeInX == -1)) {
                this.setX(destinationX);
                isFirstMove=false;
                return true;
            }

            if(changeInX == -1){
                this.setX(destinationX);
                return true;
            }

        }
        else{

            if (this.isFirstMove && (changeInX == 2 || changeInX ==1)) {
                this.setX(destinationX);
                isFirstMove=false;

                /*if(this.getX() ==  ){
                     this = new Queen(this.getX(),this.getY(), this.isWhite(), board, "Images/Black Queen.png");
                }*/
                return true;
            }

            if(changeInX == 1){
                this.setX(destinationX);
                return true;
            }
        }
        return false;
    }
}
