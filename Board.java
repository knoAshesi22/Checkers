package Checkers;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class Board {

    /*
    // NOTE: GridPane adds Nodes from upper-left, so Grid rows must be inverted to simulate properly Checkers Board
     */

    private int rNum;
    private int cNum;
    private double tileSize;
    private Color[] colorchoice;
    GridPane pane;
    Checkers.Grid grid;
    boolean p1Turn;
    int[] oClick;
    int[] dClick;
    int[][] pairClicks={oClick,dClick};

    private static int[] testCoord={2,0};

    //Color Schemes for the Board Tiles
    private final Color[][] TILECOLORS ={
            {Color.BLACK, Color.WHITE},
            {Color.WHITE, Color.RED}

    };

    //Color Schemes for the Board Pieces
    private final Color[][] PIECECOLORS ={
            {Color.BLACK, Color.WHITE},
            {Color.WHITE, Color.RED}

    };

    class GameMoves{
        int [][] gamemoves;

        public void addMoves(){

        }


    }





    public Board(int numTiles, int colorchoice, int bsize){

        // Set column and row size
        this.cNum =numTiles;
        this.rNum =numTiles;

        // Set tile size
        this.tileSize=bsize/numTiles;

        //Sets Player 1 turn to true
        p1Turn=true;

        // Create Grid class of appropriate size; populate with Piece objects
        this.grid=new Grid(numTiles);

        //Set color scheme for board
        this.colorchoice= TILECOLORS[colorchoice];


        pane=new GridPane();

//        pane.getColumnConstraints().add(new ColumnConstraints(numTiles));
//        pane.getRowConstraints().add(new RowConstraints(numTiles));

        setBoard(this.colorchoice);
        System.out.println();

        pane.addEventHandler(MOUSE_CLICKED,this::handle);

    }

    //PLACE TO CONCENTRATE ON
    //
    public boolean checkIfGameEnded(){
        if (p1Turn){
            if (grid.getNumP1Pieces()==0 || grid.getNumP2Pieces()==0){
                return true;
            }
            else if(getNumPieces()<=3){
                return true;
            }
            return false;
        }
    }
    
    public void playerWin(){
        if(grid.getNumP1Pieces()==0){
            System.out.print("PLAYER 2 IS THE WINNER!!!");
        }
        
        else if(grid.getNumP2Pieces()==0){
            System.out.print("PLAYER 1 IS THE WINNER!!!");
        }
        
        else if(grid.getNumPieces()<=3){
            System.out.print("IT'S A DRAW!!!");
        }
    }

    public void automaticPlay(){
        return;
    }

    private void showJumps(int[][] jumps){
        for (int[] jump:jumps) {
            int bRow=rNum-1-jump[0];
            Rectangle chosen=getTile(bRow,jump[1]);
            chosen.setFill(Color.CYAN);
        }
//        int bRow=rNum-1-row;
//        Rectangle chosen=getTile(bRow,col);
//        chosen.setFill(Color.CYAN);
    }



    //
    //PLACE TO CONCENTRATE ON

    public void movePiece(int[] origin, int[] dest){

        Circle piece=getPiece(origin[0],origin[1]);
        if(piece==null){
            return;
        }
        int gdx=(rNum-1-dest[0])%rNum;
        grid.movePiece(origin,dest);
        GridPane.setRowIndex(piece,(gdx)%rNum);
        GridPane.setColumnIndex(piece,(dest[1])%cNum);

        System.out.println();
//        grid.printPieces();

    }



    public void removePiece(int[] cell){
        grid.removePiece(cell);
        Circle piece=getPiece(cell[0],cell[1]);
        pane.getChildren().remove(piece);

    }


    private int[] getClick(MouseEvent event){
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != pane) {
            // Gets row and column of node  where mouse clicks
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            Integer colIndex = GridPane.getColumnIndex(clickedNode);

            //Apply transformation and return cell
            int[] cell={rNum-1-rowIndex,colIndex};
            return cell;
        }
        else {
            return null;
        }
    }

    private void setBoard(Color[] colorchoice){

        //Populates grid with appropriate pieces
        grid.setGrid2();


        Rectangle tile;

        for (int row = 0; row <rNum ; row++) {
            int gRow=rNum-1-row;
            for (int col = 0; col <cNum ; col++) {

                //Assigns Rectangle object of right color
                if(grid.isBlackCell(gRow,col)){
                    tile=drawTile(0);
                }
                else {
                    tile=drawTile(1);
                }

                //Assigns tile to the corresponding row and column of the gridpane

                GridPane.setRowIndex(tile, row);
                GridPane.setColumnIndex(tile, col);

                pane.getChildren().addAll(tile);

                //Generate piece graphic
                if (grid.isOccupied(gRow,col)){
                    Piece tp=grid.getPiece(gRow,col);
                    Color pcolor;
                    if(tp.getRole()== Piece.Role.R1){
                        pcolor=Color.RED;
                    }
                    else{
                        pcolor=Color.GREEN;
                    }
                    Circle piece=drawPiece(pcolor);
                    GridPane.setRowIndex(piece,row);
                    GridPane.setColumnIndex(piece,col);



                    //Add pieces to board graphic
                    pane.getChildren().addAll(piece);
                }

            }
        }
    }


    private void setBoardv2(Color[] colorchoice){
        Rectangle tile;


        for (int row = 0; row <rNum ; row++) {
            int gRow=rNum-1-row;
            for (int col = 0; col <cNum ; col++) {

                //Assigns Rectangle object of right color
                if(grid.isBlackCell(gRow,col)){
                    tile=drawTile(0);
                }
                else {
                    tile=drawTile(1);
                }

                //Assigns tile to the corresponding row and column of the gridpane

                GridPane.setRowIndex(tile, row);
                GridPane.setColumnIndex(tile, col);

                pane.getChildren().addAll(tile);

                //Generate piece graphic
//                if (grid.isOccupied(gRow,col)){
//                    Piece tp=grid.getPiece(gRow,col);
//                    Color pcolor;
//                    if(tp.getRole()== Piece.Role.R1){
//                        pcolor=Color.RED;
//                    }
//                    else{
//                        pcolor=Color.GREEN;
//                    }
//                    Circle piece=drawPiece(pcolor);
//                    GridPane.setRowIndex(piece,row);
//                    GridPane.setColumnIndex(piece,col);
//
//
//
//                    //Add pieces to board graphic
//                    pane.getChildren().addAll(piece);
//                }

            }

        }


        //Add pieces to board graphic
        int[] c1={0,0};
        int[] c2={1,1};
        int[] c3={2,0};
        int[] c4={7,1};

        Circle piece=drawPiece(Color.GREEN);
        GridPane.setRowIndex(piece,7);
        GridPane.setColumnIndex(piece,0);
        pane.getChildren().addAll(piece);
        grid.addPiece(c1);
//        pane.getChildren().addAll(piece);

        piece=drawPiece(Color.RED);
        grid.addPiece(c4, Piece.Role.R2);
        GridPane.setRowIndex(piece,0);
        GridPane.setColumnIndex(piece,1);
        pane.getChildren().addAll(piece);

        System.out.println();
//        grid.printPieces();
        piece=drawPiece(Color.RED);
//        GridPane.setRowIndex(piece,6);
////        GridPane.setColumnIndex(piece,1);



//        GridPane.setRowIndex(piece,5);
//        GridPane.setColumnIndex(piece,0);
//        pane.getChildren().addAll(piece);



    }

    private void turnBlue(int row, int col){
        int bRow=rNum-1-row;
        Rectangle chosen=getTile(bRow,col);
        chosen.setFill(Color.BLUE);
    }

    //Methods to change tile colors
    private void turnBlack(int row, int col){
        int bRow=rNum-1-row;
        Rectangle chosen=getTile(bRow,col);
        chosen.setFill(Color.BLACK);
    }

    private void turnOther(int row, int col){
        int bRow=rNum-1-row;
        Rectangle chosen=getTile(bRow,col);
        chosen.setFill(Color.CYAN);
    }







    private void highlightTile(int row, int col) {

        int bRow=rNum-1-row;
        Rectangle chosen=getTile(bRow,col);

        Piece piece=grid.getPiece(row,col);

        if(!piece.isHighlighted()){
            chosen.setFill(Color.BLUE);
            piece.setHighlighted();
        }
        else {
            chosen.setFill(Color.BLACK);
            piece.setHighlighted();
        }
//        System.out.println(piece.isHighlighted());
//        piece.setHighlighted();


//
//        for (Node node : pane.getChildren()) {
//            int trow = GridPane.getRowIndex(node);
//            int tcol = GridPane.getColumnIndex(node);
//            if (row == trow && col == tcol) {
//
////                Rectangle chosen = (Rectangle) node;
////                chosen.setFill(Color.BLUE);
//            }
//
//        }
////        if (grid.isOccupied(row, col)) {
////
////        }
    }






    private Rectangle getTile(int row, int col){

        //Iterate through GridPane until node with matching index found
        for (Node node : pane.getChildren()) {
            int trow = GridPane.getRowIndex(node);
            int tcol = GridPane.getColumnIndex(node);
            if (row == trow && col == tcol) {
                Rectangle chosen = (Rectangle) node;
//                System.out.println(chosen.getClass());
                return chosen;
            }

        }
        return null;
    }

    private boolean inMoveset(int[] move, Grid.Moveset moveset){
        int[] nmove={rNum-1-move[0],move[1]};
        int[][] moves=moveset.getMoves();
        boolean found=false;
        for (int[] vMove:moves) {
            found=Arrays.equals(vMove,move);
            if(found==true){
                return found;
            }
        }
        return found;
    }

    private void getAllCircles(){
        String tclass="class javafx.scene.shape.Circle";
        tclass="class javafx.scene.shape.Circle";
        for (Node node : pane.getChildren()){
            String nclass=node.getClass().toString();
//            System.out.println(nclass);
            if(nclass.equals(tclass)){
                int trow = GridPane.getRowIndex(node);
                int tcol = GridPane.getColumnIndex(node);
                System.out.println(trow+"\t"+tcol+"\n");
//                Circle c1=(Circle) node;
//                c1.setFill(Color.GOLD);
            }
        }
    }

    private Circle getPiece(int row, int col){
        String tclass="class javafx.scene.shape.Circle";
        row=rNum-1-row;

        //Iterate through GridPane until node with matching index and class found
        for (Node node : pane.getChildren()){
            String nclass=node.getClass().toString();
            if(nclass.equals(tclass)){
                int trow = GridPane.getRowIndex(node);
                int tcol = GridPane.getColumnIndex(node);

                if (row == trow && col == tcol){
                    Circle chosen = (Circle) node;
                    return chosen;
                }
            }
        }
        return null;
    }


    public Board(){
        this(8,0,64);
    }

    public Board(int numTiles, int bsize){
        this(numTiles,0,bsize);
    }

    public Board(int bsize){
        this(8,0,bsize);

    }

    private int anInt=0;

    private void movePiecev4(int[] o,int[] d){
        grid.movePiece(o,d);

        grid.printOccupiedv2();
        System.out.println(

        );
        Circle piece=getPiece(o[0],o[1]);
        pane.getChildren().remove(piece);
        int gdx=(rNum-1-d[0])%rNum;
        GridPane.setRowIndex(piece,(gdx));
        GridPane.setColumnIndex(piece,(d[1])%cNum);
        pane.getChildren().addAll(piece);

    }

    private int[] genMid(int[] c1,int[] c2){
        int mr=c1[0]+c2[0];
        mr=mr/2;
        mr=mr%rNum;
        int mc=c1[1]+c2[1];
        mc=mc/2;
        mc=mc%rNum;
        return new int[]{mr,mc};
    }

    private void handle(MouseEvent e){

        int[] cell=getClick(e);
        int row=cell[0];int col=cell[1];
        int bRow=rNum-1-row;
        Piece sPiece;
        Piece.Role cRole;

        if(p1Turn){
            cRole= Piece.Role.R1;
        }
        else {
            cRole= Piece.Role.R2;
        }

        if(false){
            return;
        }

        if(!grid.isBlackCell(row,col)){
            return;
        }
        if(oClick==null){
            sPiece=grid.getPiece(row,col);
            if(sPiece!=null){
                if(sPiece.getRole()!=cRole){
                    return;
                }
                oClick=cell;
//                highlightTile(oClick[0],oClick[1]);
                turnBlue(oClick[0],oClick[1]);
                showMoves(oClick);

            }
            return;
        }
        else {
            dClick=cell;
            Grid.Moveset moves=grid.genMoves(oClick);

            boolean sameTile=Arrays.equals(oClick,dClick);
            if(sameTile){
                hideMoves(oClick);
                turnBlack(oClick[0],oClick[1]);
                dClick=oClick=null;
                return;
            }

            if(!inMoveset(cell,moves)){
                return;
            }
            else if(grid.isJump(oClick,dClick)){
                int[] toRemove=genMid(oClick,dClick);
                removePiece(new int[]{toRemove[0],toRemove[1]});
                if(grid.isGameOver()){
                    System.out.println("Game over");
                    System.exit(0);
                }
                hideMoves(oClick);
                turnBlack(oClick[0],oClick[1]);
                movePiecev4(oClick,dClick);

                int[][] pmoves=grid.genMoves(dClick).getMoves();
                for(int[] move:pmoves){
                    if(grid.isJump(dClick,move)){
//                        hideMoves(oClick);
//                        turnBlack(oClick[0],oClick[1]);
//                        turnBlack(dClick[0],dClick[1]);

                        oClick=dClick;
//                        showMoves(oClick);
                        turnOther(move[0],move[1]);

                        turnBlue(oClick[0],oClick[0]);
                        dClick=null;
                        return;
                    }
                }
//                hideMoves(oClick);
                turnBlack(dClick[0],dClick[1]);
                p1Turn=!p1Turn;
                oClick=dClick=null;
                return;

            }
            else {
                hideMoves(oClick);
                turnBlack(oClick[0],oClick[1]);
                movePiecev4(oClick,dClick);
                oClick=dClick=null;
                p1Turn=!p1Turn;
                return;
            }

        }
    }


    public Rectangle drawTile(int ind){
        /*
        /Method to draw tiles using Rectangle object
         */
        Rectangle tile = new Rectangle();
        tile.setWidth(tileSize);
        tile.setHeight(tileSize);
        tile.setFill(colorchoice[ind]);

        return tile;
    }

    public Circle drawPiece(Color color){
        /*
        /Method to draw pieces using Circle object
         */
        Circle piece=new Circle();
        double radius=tileSize/2.0;
        piece.setRadius(radius);
        piece.setFill(color);

        piece.addEventHandler(MOUSE_CLICKED,e -> {
            int[] cell=this.getClick(e);
            int row=cell[0];int col=cell[1];
//            highlightTile(row,col);
        });

        return piece;
    }

    public Circle drawPiece(){
        return drawPiece(Color.RED);
    }


    private void showMoves(int[] cell){
        int row=cell[0];
        int col=cell[1];
        Grid.Moveset moveset=null;

        if(grid.isOccupied(row,col)){
            Piece chosen=grid.getPiece(row,col);
            moveset=grid.genMoves(cell);
//            highlightTile(cell);
        }

        for (int[] move:moveset.getMoves()) {
            turnOther(move[0],move[1]);

        }

    }

    private void hideMoves(int[] cell){
        int row=cell[0];
        int col=cell[1];
        Grid.Moveset moveset=null;

        if(grid.isOccupied(row,col)){
            Piece chosen=grid.getPiece(row,col);
            moveset=grid.genMoves(cell);
//            highlightTile(cell);
        }

        for (int[] move:moveset.getMoves()) {
            turnBlack(move[0],move[1]);

        }

    }


    public Pane getPane(){
        return pane;
    }


    private void resizeBoard(){
        // Rescales board to window, if window size is altered.
    }


}
