package Checkers;
import Checkers.Piece.*;

import java.util.Arrays;


public class Grid {

    private Piece[][] grid;
    private static final int DEFAULT_SIZE=8;

    private int numP1_pawns;
    private int numP1_kings;
    private int numP2_pawns;
    private int numP2_kings;

    class Moveset{

        int[][] moveset;
        int index;
        private final int DSIZE=0;

        public Moveset(){
            moveset=new int[DSIZE][2];
            index=0;
        }

        public void add(int x,int y){
            if(index>=moveset.length){
                expand();
            }
            moveset[index]=new int[]{x,y};
            index++;
        }

        private void expand(){
            int[][] temp=new int[moveset.length+1][];
            for (int i = 0; i <moveset.length ; i++) {
                temp[i]=moveset[i];
            }
            moveset=temp;
        }

        public void remove(int index){
            moveset[index]=null;
            for (int i = index; i <moveset.length-1 ; i++) {
                moveset[i]=moveset[i+1];
            }

            int[][] temp=new int[moveset.length][];
            for (int i = 0; i <moveset.length ; i++) {
                temp[i]=moveset[i];
            }
            moveset=temp;

        }

        public int[] getMove(int index){
            return moveset[index];
        }

        public int getLength(){
            return moveset.length;
        }

        @Override
        public String toString() {
            return "Moveset{" +
                    "moveset=" + Arrays.deepToString(moveset) +
                    '}';
        }
    }

    //Constructors

    public Grid(int size){
        grid=new Piece[size][size];
        numP1_pawns=0;
        numP1_kings=0;
        numP2_pawns=0;
        numP2_kings=0;

    }

    public Grid(){
        this(DEFAULT_SIZE);
    }


    //Accessor methods for number of pieces on the board
    public int getNumP1_pawns() {
        return numP1_pawns;
    }

    public int getNumP1_kings() {
        return numP1_kings;
    }

    public int getNumP2_pawns() {
        return numP2_pawns;
    }

    public int getNumP2_kings() {
        return numP2_kings;
    }

    public void addPiece(Piece piece, int[] coord){
        int x=coord[0];
        int y=coord[1];
        if(!isOccupied(x,y) && isBlackCell(x,y))
        {
            grid[x][y]=piece;
            if(piece.getRole()==Role.R1){
                if(piece.getRank()==Rank.KING){
                    numP1_kings++;
                }
                else {
                    numP1_pawns++;
                }
            }
            else{
                if(piece.getRank()==Rank.KING){
                    numP2_kings++;
                }
                else {
                    numP2_pawns++;
                }
            }
        }
    }

    public void addPiece(int[] coord){
        Piece piece=new Piece(Role.R1);
        addPiece(piece,coord);
    }

    public void setGrid(){
        int mid=grid.length/2;
		int start;
        for (int i = 0; i < mid-1; i++) {
        	if(i%2==0){
        		start =1;
        	}else {
        		start=0;
        	}
        	for (int j = start; j <grid.length; j=j+2) {
        			grid[i][j] = new Piece(Piece.Role.R1);
        			grid[grid.length-1-i][j-start] = new Piece(Piece.Role.R2);
        			numP1_pawns++;
        			numP2_pawns++;
        	}

        }
    }

    public boolean isBlackCell(int x, int y){
        /*
        /Returns if a cell with coordinate (x,y) is a black tile on the board.
         */
            if(!isInGrid(x,y)){
                return false;
            }
            if((x+y)%2==0){
                return true;
            }
            return false;
    }

    public Moveset genMoves(int[] cell){
        /*
        /Generates a list of possible moves from a given tile
        /Conditions for method:
        /1. Should only work for occupied tiles
        /2. Should only show valid moves,i.e., within the bounds of the grid
        /3. Should account for the player the piece belongs to,i.e., R1 pawns move forward, R2 pawns move backward
        /4. Should check if tile has a pawn or king.
        /5. Should generate possible jumps
         */

        Moveset moveset=new Moveset();

        if (isOccupied(cell[0],cell[1])){

            int x=cell[0];
            int y=cell[1];
            Piece piece=grid[x][y];
            if (piece.getRank() == Rank.PAWN){
                if (piece.getRole() == Role.R1){

                    if(isInGrid(x+1,y+1) && !isOccupied(x+1,y+1)){
                        moveset.add(x+1,y+1); //add upper-right movement
                    }
		            else if(isInGrid(x+2,y+2) && !isOccupied(x+2,y+2)){
		                Piece p2 = grid[x+1][y+1];
                        if (p2.getRole() == Role.R2){
                                moveset.add(x+2,y+2); //add upper-right jump
                        }
                    }

//		            if(isInGrid(x+1,y+1)){
//		                if(!isOccupied(x+1,y+1)){
//                            moveset.add(x+1,y+1); //add upper-right movement
//                        }
//                        else if(isInGrid(x+2,y+2) && !isOccupied(x+2,y+2)){
//                            Piece p2 = grid[x+1][y+1];
//                            if (p2.getRole() == Role.R2){
//                                moveset.add(x+2,y+2); //add upper-right jump
//                            }
//                        }
//                    }



                    if(isInGrid(x-1,y+1) && !isOccupied(x-1,y+1)){
                        moveset.add(x-1,y+1); //add upper-left movement
                    }
		            else if(isInGrid(x-2,y+2) && !isOccupied(x-2,y+2)){
                        Piece p2 = grid[x-1][y+1];
                        if (p2.getRole() == Role.R2){
                                moveset.add(x-2,y+2); //add upper-left jump
                        }
                    }
                }
		    
                else if (piece.getRole() == Role.R2){
                    if (isInGrid(x+1,y-1) && !isOccupied(x+1,y-1)){
                        moveset.add(x+1,y-1); // add lower-right movement
                    }
		            else if(isInGrid(x+2,y-2) && !isOccupied(x+2,y-2)){
                        Piece p1 = grid[x+1][y-1];
                        if (p1.getRole() == Role.R1){
                                        moveset.add(x+2,y-2); //add lower-right jump
                        }
                    }

                    if (isInGrid(x-1,y-1) && !isOccupied(x-1,y-1)){
                        moveset.add(x-1,y-1); // add lower-left movement
                    }
		            else if(isInGrid(x-2,y-2) && !isOccupied(x-2,y-2)){
                        Piece p1 = grid[x-1][y-1];
                        if (p1.getRole() == Role.R1){
                                        moveset.add(x-2,y-2); //add upper-right jump
                        }
                    }
                }
            }
        }
        return moveset;
    }


    public boolean isOccupied(int x, int y){
        /*
        /Checks if a tile is occupied
         */
        if (grid[x][y]!=null){
            return true;
        }
        return false;
    }

    public boolean isJump(int[] origin, int[] dest){
        /*
        /Checks if a move is a jump or not
         */
        int xd=Math.abs((dest[0]-origin[0]));
        int yd=Math.abs((dest[1]-origin[1]));
        if(xd==2||yd==2){
            return true;
        }
        return false;
    }

    public int getSize(){
        return grid.length;
    }

    public int getNumPieces(){
        int total=getNumP1_kings()+getNumP1_pawns()+getNumP2_kings()+getNumP2_pawns();
        return total;
    }

    public int getNumP1Pieces(){
        int p1pieces=getNumP1_kings()+getNumP1_pawns();
        return p1pieces;
    }

    public int getNumP2Pieces(){
        int p2pieces=getNumP2_kings()+getNumP2_pawns();
        return p2pieces;
    }




    public boolean isInGrid(int x,int y){
        /*
        /Checks if a cell/coordinate is in the grid/board
         */
        if (x<0||x>=grid.length){
            return false;
        }
        if (y<0||y>=grid.length){
            return false;
        }
        return true;
    }

    public void movePiece(int[] origin, int[] dest){
        /*
        /Moves a piece from one tile to another
         */
        grid[dest[0]][dest[1]]=grid[origin[0]][origin[1]];
        grid[origin[0]][origin[1]]=null;

        if(dest[0]==0||dest[0]==(grid.length-1)){
            Piece piece=grid[dest[0]][dest[0]];
            if(piece.getRank()== Piece.Rank.KING){
                return;
            }
            if(piece.getRole()== Piece.Role.R1&&dest[0]==(grid.length-1)){
                piece.promote();
                return;
            }
            if(piece.getRole()== Piece.Role.R2&&dest[0]==(0)){
                piece.promote();
                return;
            }

        }

    }

    public void removePiece(int[] coord){
        grid[coord[0]][coord[1]]=null;
    }

    public void printOccupied(){
        /*
        /Prints which squares are occupied
         */
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid.length ; j++) {
                if (grid[i][j]==null){
                    System.out.print(false+" ");
                }
                else{
                    System.out.print(true+" ");
                }
            }
            System.out.println("");
        }
    }

    public void printPieces(){
        /*
        /Prints a list of pieces on the board
         */
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid.length ; j++) {

                if(!isOccupied(i,j)){
                    System.out.print("Tile ");
                }
                else{
                    if(grid[i][j].getRole()== Piece.Role.R1){
                        System.out.print("R1 piece ");
                    }
                    else if(grid[i][j].getRole()== Piece.Role.R2){
                        System.out.print("R2 piece ");
                    }
                }
            }
            System.out.println("");
        }
    }

}
