import java.util.ArrayList;
public class Board {

    private Piece [][] board= new Piece[8][8];
    private Piece[][] whiteDeadPieces = new Piece[8][2];
    private Piece[][] blackDeadPieces= new Piece[8][2];

    private int whiteY=0;
    private int whiteX=0;

    private int blackY=0;
    private int blackX=0;

    public Board()
    {
        this.resetBoard();
    }

    public Piece getSquare(int X, int Y){
        return board[X][Y];
    }

    public Piece[][] getBlackDeadPieces() {
        return blackDeadPieces;
    }

    public Piece[][] getWhiteDeadPieces() {
        return whiteDeadPieces;
    }

    private void resetBoard() {

        //Black pieces

        board[0][0] = new Rook(0,0,false, this, "Images/Black Rook.png");
        board[0][1] = new Knight(0,1, false, this, "Images/Black Knight.png");
        board[0][2] = new Bishop(0, 2,false, this, "Images/Black Bishop.png");
        board[0][3] = new Queen(0, 3,false, this, "Images/Black Queen.png");
        board[0][4] = new King(0,4,false, this, "Images/Black King.png");
        board[0][5] = new Bishop(0,5,false, this, "Images/Black Bishop.png");
        board[0][6] = new Knight(0,6,false, this, "Images/Black Knight.png");
        board[0][7] = new Rook(0,7,false, this, "Images/Black Rook.png");

        for(int i=0; i<8; i++){
            //Row of pawns
            board[1][i] = new Pawn(1, i,false, this, "Images/Black Pawn.png");
        }

        //free spaces in the middle of the board
        for(int i=2; i<6; i++)
        {
            for (int j=0; j<8; j++)
            {
                board[i][j] = null;
            }
        }

        //White pieces

        for(int i=0; i<8; i++){
            //Row of pawns
            board[6][i] = new Pawn(6, i,true, this, "Images/White Pawn.png");
        }

    board[7][0] = new Rook(7,0,true, this, "Images/White Rook.png");
    board[7][1] = new Knight(7,1,true, this, "Images/White Knight.png");
    board[7][2] = new Bishop(7, 2, true, this, "Images/White Bishop.png");
    board[7][3] = new Queen(7,3,true, this, "Images/White Queen.png");
    board[7][4] = new King(7,4,true, this, "Images/White King.png");
    board[7][5] = new Bishop(7, 5, true, this, "Images/White Bishop.png");
    board[7][6] = new Knight(7,6,true, this, "Images/White Knight.png");
    board[7][7] = new Rook(7,7,true, this, "Images/White Rook.png");

    }

    boolean move(int startX, int startY, int destinationX, int destinationY){
        Piece origin= this.getSquare(startX, startY);
        Piece destination= this.getSquare(destinationX,destinationY);

        if(origin==null){
            return false;
        }
        else{

            if(origin.isValidMove(destinationX, destinationY)){

                if( origin instanceof King && destination instanceof Rook){
                    if(((King) origin).isCastle() && ((Rook) destination).isCastle()){
                        board[destinationX][destinationY]=origin;
                        board[startX][startY]=null;
                        board[destination.getX()][destination.getY()]=destination;
                    }

                }
                else{

                    if(origin instanceof Pawn && ((Pawn) origin).isPromotion()){
                        if(origin.isWhite()){
                            board[destinationX][destinationY]= new Queen(destinationX, destinationY, true,this,"Images/White Queen.png" );
                            if(destination!= null){
                                whiteDeadPieces[whiteY][whiteX]= destination;
                                whiteY++;
                                if(whiteY ==8){
                                    whiteX=1 ;
                                    whiteY=0;

                                }
                            }
                        }
                        else{
                            board[destinationX][destinationY]= new Queen(destinationX, destinationY,false ,this,"Images/Black Queen.png" );
                            if(destination!= null){
                                blackDeadPieces[blackY][blackX]= destination;
                                blackY++;

                                if(blackY ==8 ){
                                    blackX=1 ;
                                    blackY=0;
                                }
                            }
                        }

                        board[startX][startY]=null;
                    }
                    else {
                        if(origin.isWhite()){
                            if(destination!= null){
                                whiteDeadPieces[whiteY][whiteX]= destination;
                                whiteY++;
                                if(whiteY ==8){
                                    whiteX=1 ;
                                    whiteY=0;

                                }
                            }
                        }
                        else{
                            if(destination!= null){
                                blackDeadPieces[blackY][blackX]= destination;
                                blackY++;

                                if(blackY ==8 ){
                                    blackX=1 ;
                                    blackY=0;
                                }
                            }
                        }
                        board[destinationX][destinationY]=origin;
                        board[startX][startY]=null;
                    }
                }
                return true;
            }
        }

            return false;

    }

    // each square with 80 by 80
    //the total size of the page would be 640 by 640 (8 x 80)
    //the png pictures are 60 by 60
}
