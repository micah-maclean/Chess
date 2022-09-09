import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {


    private JFrame window;

    private JPanel Board;

    private JPanel deadPiecesW;
    private JPanel deadPiecesB;

    private JMenuBar menuBar;
    private JMenuItem reset;

    //Colours:
    private Color colorGrey= Color.GRAY;
    private Color colorGreen=Color.GREEN;
    private Color colorRed=Color.RED;
    private Color colorBlack= Color.BLACK;

    //components for board display
    private JButton[][] squares= new JButton[8][8];

//components for dead white pieces
    private JButton[][] left= new JButton[8][2];

    //components for dead black pieces
    private JButton[][] right= new JButton[8][2];


    private boolean firstClick= true;
    private boolean whitePlayerTurn=true;

    private int startX;
    private int startY;
    private int destinationX;
    private int destinationY;
    private int gameStatus=1;

    private Board board;



    public Window() {
        super("Chess");
        this.resetBoard();
    }

    public void resetBoard(){
       board= new Board();

        //right Panel
        deadPiecesB=new JPanel();
        deadPiecesB.setLayout(new GridLayout(8,2));
        deadPiecesB.setPreferredSize(new Dimension(200,800));
        for(int i=0; i < 8; i++)
        {
            for(int j=0; j < 2; j++){
                right[i][j]= new JButton();
                deadPiecesB.add(right[i][j]);
            }
        }

        //Left panel
        deadPiecesW= new JPanel();
        deadPiecesW.setLayout(new GridLayout(8,2));
        deadPiecesW.setPreferredSize(new Dimension(200,800));
        for(int i=0; i < 8; i++)
        {
            for(int j=0; j<2; j++){
            left[i][j]= new JButton();
            deadPiecesW.add(left[i][j]).setBackground(colorGrey);        }

        }

        //Board Panel
        Board= new JPanel();
        Board.setBorder(BorderFactory.createLineBorder(colorBlack, 10,true));
        Board.setLayout(new GridLayout(8,8));
        Board.setPreferredSize(new Dimension(800,800));
        for (int i= 0; i < 8; i++)
        {
            for (int j= 0; j < 8; j++){
                squares[i][j] = new JButton();

                if((i+j) % 2 != 0){
                    squares[i][j].setBackground(colorGrey);
                }

                if(board.getSquare(i,j)!=null){
                    ImageIcon icon=  board.getSquare(i,j).getImage();
                    squares[i][j].setIcon(icon);
                }

                Board.add(squares[i][j]);
                //contents.add(squares[i][j]);
                squares[i][j].addActionListener(this);
            }
        }


        menuBar=new JMenuBar();
        reset= new JMenuItem("RESET");
        reset.addActionListener(this);
        menuBar.add(reset);

        setJMenuBar(menuBar);
        add(deadPiecesB, BorderLayout.LINE_START);
        add(deadPiecesW, BorderLayout.LINE_END);
        add(Board, BorderLayout.CENTER);


        setSize(1200,800);
        setResizable(false);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == reset){
           this.dispose();
            new Window();

        }

        if (gameStatus != -1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (source == squares[i][j]) {

                        if (firstClick) {

                            if (board.getSquare(i, j) != null) {

                                if (board.getSquare(i, j).isWhite() == whitePlayerTurn) {
                                    firstClick = false;
                                    squares[i][j].setBorder(BorderFactory.createLineBorder(colorGreen, 5));

                                    startX = i;
                                    startY = j;
                                }
                            }
                        } else {
                            destinationX = i;
                            destinationY = j;

                            Piece destination = board.getSquare(destinationX, destinationY);

                            if (board.move(startX, startY, destinationX, destinationY)) {
                                ImageIcon icon = board.getSquare(destinationX, destinationY).getImage();

                                if (destination instanceof King) {
                                    gameStatus=-1;
                                }

                                if (board.getSquare(destinationX, destinationY) instanceof King && destination instanceof Rook) {
                                    //castle situation
                                    ImageIcon icon1 = board.getSquare(destination.getX(), destination.getY()).getImage();
                                    squares[destinationX][destinationY].setIcon(icon);
                                    squares[startX][startY].setIcon(null);
                                    squares[destination.getX()][destination.getY()].setIcon(icon1);
                                } else {
                                    squares[destinationX][destinationY].setIcon(icon);
                                    squares[startX][startY].setIcon(null);
                                }


                                if (whitePlayerTurn) {
                                    whitePlayerTurn = false;
                                } else {
                                    whitePlayerTurn = true;
                                }

                            }
                            squares[startX][startY].setBorder(null);
                            firstClick = true;

                            for(int y=0; y < 8; y++){
                                for(int x=0; x < 2; x++){

                                     Piece[][] whiteDeadPieces= board.getWhiteDeadPieces();
                                     Piece[][] blackDeadPieces= board.getBlackDeadPieces();

                                     if(whiteDeadPieces[y][x] != null){
                                         ImageIcon icon=whiteDeadPieces[y][x].getImage();
                                         left[y][x].setIcon(icon);
                                     }
                                     if(blackDeadPieces[y][x] != null){
                                         ImageIcon icon=blackDeadPieces[y][x].getImage();
                                         right[y][x].setIcon(icon);
                                     }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
