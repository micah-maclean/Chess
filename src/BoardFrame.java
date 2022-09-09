import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    // Old implementation - this code is not use anymore. I left as documentation.

public class BoardFrame implements ActionListener {


   // private Container contents;

    //Board
    //components
    private JButton[][] squares= new JButton[8][8];

    //Colours:
    private Color colorGrey= Color.GRAY;
    private Color colorGreen=Color.GREEN;
    private Color colorRed=Color.RED;

    //
    private boolean firstClick= true;


    private int startX;
    private int startY;
    private int destinationX;
    private int destinationY;

    private Board board;

    public BoardFrame(Board board){

        this.board= board;


        JPanel Board= new JPanel();
        Board.setLayout(new GridLayout(8,8));
       // contents= getContentPane();
       // contents.setLayout(new GridLayout(8,8));



        //Create and add board components:
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



        // Size and display windows:
       // Board.setSize(640, 640);
        //Board.setResizable(false);
        Board.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        Object source= e.getSource();
        //missing implementation

        for(int i= 0; i < 8; i++){
            for(int j= 0; j < 8; j++){
                if(source == squares[i][j]){

                    if(firstClick) {
                        firstClick= false;
                        squares[i][j].setBorder(BorderFactory.createLineBorder(colorGreen,5));


                        startX=i;
                        startY=j;

                    }
                    else
                    {
                        destinationX=i;
                        destinationY=j;

                        if(board.move(startX, startY, destinationX, destinationY)) {
                            ImageIcon icon=  board.getSquare(destinationX, destinationY).getImage();
                            squares[destinationX][destinationY].setIcon(icon);
                            squares[startX][startY].setIcon(null);

                        }
                        else{
                            squares[i][j].setBorder(BorderFactory.createLineBorder(colorRed,5));
                        }
                        squares[startX][startY].setBorder(null);
                        firstClick=true;
                    }
                }
            }
        }

    }


}
