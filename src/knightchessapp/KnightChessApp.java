package knightchessapp;

import java.util.Scanner;

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

        Board b = new Board(size()); //Creates board class
        Stack stack = new Stack(Board.getSize());
    }//End of main

    private static int size() {// Gets board size from user.
        Scanner in = new Scanner(System.in);
        while (true) {
            if (in.hasNextInt()) {
                return in.nextInt();
            } else {
                System.out.println("Please input an integer.");
            }

        } //End of while
    }// End of size()
}//End of KnightChessApp

//--------------------------------------------------------------
class Knight {//Starts of Knight class

    private int position;
    private int cycle;
    private int N = Board.getSize();
    private static int[][] moves8 = {{+1, -2}, {+2, -1}, {+2, +1}, {+1, +2}, {-1, +2}, {-2, +1}, {-2, -1}, {-1, -2}};

    public Knight(int position) {
        this.position = position;
        cycle = 0;
    }

    public int getPosition() {//Start of getPosition()
        return position;
    }//End of getPosition()

    //GetNextPos() algorithm based off of code by Qing Yang
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
                if (Board.get(nextPos) == false) { // unoccupied cell?
                    return nextPos; // yes, found a move
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

    public Board(int size) { // Start of Board constructor
        chessBoard = new boolean[size][size];
        this.size = size;
    } // End of Board constructor

    public static void unmark(int position) { //Marks the array spot as false (not occupied)
        int x = (position - 1) % size;
        int y = (position - 1) / size;
        chessBoard[x][y] = false;
    } // End of unmark()

    public static void mark(int position) { //Marks the array spota as true (occupied)
        int x = (position - 1) % size;
        int y = (position - 1) / size;
        chessBoard[x][y] = true;
    } // End of mark()

    public static boolean get(int position) { //Returns true if spot is occupied and false if it is empty
        int x = (position - 1) % size;
        int y = (position - 1) / size;
        return chessBoard[x][y];
    } // End of get()

    public static int getSize() { //Retruns the size of board
        return size;
    }// End of getBoardSize()
}//End of Board class

//--------------------------------------------------------------

class Stack{
    private Knight[] stackArray;
    private int current;
    
    public Stack(int s){
        stackArray = new Knight[s*s];
        current = 0;
    }
    
    public void push(Knight k){
        stackArray[current] = k;
        current++;
    }
    
    public Knight pop(){
        current--;
        return stackArray[current+1];
    }
}
