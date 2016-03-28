package knightchessapp;

/*
 * Authors: Ben Rhuman, Isacc Sotelo, Brendan Tracey
 * CSCI 232 - Lab 3
 *
 */
public class KnightChessApp { //Start of KnightChessApp Class

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {//Start of main
        // TODO code application logic here
    }//End of main

}//End of KnightChessApp

//--------------------------------------------------------------
class Knight {//Starts of Knight class

    private int position;
    private int cycle;
    private int N = Board.getBoardSize();
    private static int[][] moves8 = {{+1, -2}, {+2, -1}, {+2, +1}, {+1, +2}, {-1, +2}, {-2, +1}, {-2, -1}, {-1, -2}};

    public Knight(int position) {
        this.position = position;
        cycle = 0;
    }

    public int getPosition() {//Start of getPosition()
        return position;
    }//End of getPosition()

    //GetNextPos() algorithm based of of code by Qing Yang
    public int getNextPos() { //Start of getNextPos()
        while (cycle < 8) {// picks next possible move.
            int dx = moves8[cycle][0]; // get move in
            int dy = moves8[cycle][1]; // (x,y) format
            int x = (position - 1) % N; // translate from j
            int y = (position - 1) / N; // to (x,y) format
            x = x + dx; // add move to
            y = y + dy; // position
            cycle++; // used this move
            if (x >= 0 && x < N && y >= 0 && y < N) // on the board?
            { // yes
                int nextPos = x + y * N + 1; // (x,y) to j
                if (Board.get(nextPos) == false) // unoccupied cell?
                { // yes
                    return nextPos; // found a move
                }
            } // end if(x>=0...)
        } // end while // no possible move
        cycle = 0; // reset move index
        return -1; // failure
    } // end getNextPos()

}//End of Knight class

//--------------------------------------------------------------
class Board {//Start of Board class

    private static boolean[][] chessBoard;
    private static int size;

    public Board(int size) {
        chessBoard = new boolean[size][size];
        this.size = size;
    }

    public static void unmark(int position) {
        int x = (position - 1) % size;
        int y = (position - 1) / size;
        chessBoard[x][y] = false;
    }
    
    public static void mark(int position){
        int x = (position - 1) % size;
        int y = (position - 1) / size;
        chessBoard[x][y] = true;
    }
    
    public static boolean get(int position){
        int x = (position - 1) % size;
        int y = (position - 1) / size;
        return chessBoard[x][y];
    }

    public static int getBoardSize() {
        return size;
    }
}//End of Board class

//--------------------------------------------------------------

