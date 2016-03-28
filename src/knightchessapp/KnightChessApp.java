package knightchessapp;

import java.util.InputMismatchException;
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
        System.out.print("\nEnter board size (8 for 8x8 board): ");
        int size = getInt();

        System.out.print("Enter the beginning square (1 to " + size * size + "): ");
        int position = getPos(size); //Gets the starting position.

        long moves = 0; //Keeps track of total moves made

        Board b = new Board(size); //Creates board class
        Stack stack = new Stack(size);
        Knight knight = new Knight(position);
        stack.push(knight);
        b.mark(stack.peak().getPosition());
        
        while(!stack.isFull()){
            moves++;
            int tempPos = stack.peak().getNextPos();
            if(tempPos != -1){
                //stack.peak().cycle--;
                knight = new Knight(tempPos);
                stack.push(knight);
                b.mark(stack.peak().getPosition());
            } else{
                b.unmark(stack.pop().getPosition());
            }
            if(stack.isFull()){
                System.out.print("\nSUCCESS:\nTotal Number of Moves = " + moves + "\nMoving Sequence: ");
                stack.printStack();
            }else if (stack.isEmpty()){
                System.out.print("\nFAILURE:\nTotal Number of Moves = " + moves + "\n");
                break;
            }
        }//End While
    }//End of main

    private static int getInt() {// Gets board size from user.
        Scanner in = new Scanner(System.in);
        int val;
        while (true) {
            try {
                val = in.nextInt();
                if (val > 0) {
                    return val; //Returns a positive integer val
                } else {
                    System.out.print("Please input an integer greater than '0': ");
                }
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.print("Please input an integer: ");
            } //End of try-catch block
        } //End of while
    }// End of size()

    private static int getPos(int size) {
        int val;
        while (true) {
            val = getInt();
            if (val <= size * size) { //Makes sure value is within the bounds of the board
                return val;
            } else {
                System.out.print("Please input an integer between 1 and " + size * size + ": ");
            }
        }//End while
    }//End of position()
}//End of KnightChessApp

//--------------------------------------------------------------
class Knight {//Starts of Knight class

    private int position;
    public int cycle;
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
        //cycle = 0; // reset move index
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

    public void unmark(int position) { //Marks the array spot as false (not occupied)
        int x = (position - 1) % size;
        int y = (position - 1) / size;
        chessBoard[x][y] = false;
    } // End of unmark()

    public void mark(int position) { //Marks the array spota as true (occupied)
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
class Stack {

    private Knight[] stackArray;
    private int current;

    public Stack(int s) {
        stackArray = new Knight[s * s]; // s*s is equal to the number of positions on the board
        current = -1;
    }// End of Stack constructor

    public void push(Knight k) {
        current++;
        stackArray[current] = k; //Puts node at the current end of stack
    }//End of push()

    public Knight pop() {
        current--; //Moves reference back up stack
        return stackArray[current+1]; // Returns the now dereferenced node
    }//End of pop()

    public Knight peak() {
        return stackArray[current];
    }//End of peak

    public boolean isFull() {
        if (current == stackArray.length - 1) { // is the stack full?
            return true; //yes
        } else {
            return false; // no
        }
    }

    public boolean isEmpty() {
        if (current == -1) {
            return true;
        } else {
            return false;
        }
    }
    
    public void printStack(){
        for(int i = 0; i < stackArray.length; i++){
            System.out.print(stackArray[i].getPosition() + " ");
        }
    }
}// End of Stack class
