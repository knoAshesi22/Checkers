package Checkers;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Board {

    private int rNum;
    private int cNum;
    private double tileSize;
    private Color[] colorchoice;
    GridPane pane;
    Checkers.Grid grid;

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

    public Board(int numTiles, int colorchoice, int bsize){

        // Set column and row size
        this.cNum =numTiles;
        this.rNum =numTiles;

        // Set tile size
        this.tileSize=bsize/numTiles;

        // Create Grid class of appropriate size; populate with Piece objects
        this.grid=new Grid(numTiles);
        grid.setGrid();

        //Set color scheme for board
        this.colorchoice= TILECOLORS[colorchoice];


        pane=new GridPane();
//        pane.getColumnConstraints().add(new ColumnConstraints(numTiles));
//        pane.getRowConstraints().add(new RowConstraints(numTiles));
        setBoard(this.colorchoice);
    }

    public Board(){
        this(8,0,64);
    }

    public Board(int bsize){
        this(8,0,bsize);

    }
    private void setBoard()
    {
        for (int row = 0; row < grid.length; row++) {
            int i1=0;
            if(row%2==0){
                i1=1;
            for (int col = 0; col < grid.length; col++) {
                Rectangle tile=drawTile(i1);
                GridPane.setRowIndex(tile, row);
                GridPane.setColumnIndex(tile, col);

                //Generate piece graphic
                if (grid[row][col]!=null){
                    Circle piece=drawPiece();
                    GridPane.setRowIndex(piece,row);
                    GridPane.setColumnIndex(piece,col);

                    //Add tiles and pieces to board graphic
                    pane.getChildren().addAll(tile);
                }
                pane.getChildren().addAll(piece);
                i1=(i1+1)%2;
                
    }
    private void setBoard(Color[] colorchoice){
        /*
        /Creates a board graphic of given color scheme, with corresponding pieces in their position
         */

        for (int row = 0; row < rNum; row++) {

            //Act as a check to generate tiles of different colors
            int i1=0;
            if(row%2==0){
                i1=1;
            }
            for (int col = 0; col < cNum; col++) {

                //Generate tile graphic
                Rectangle tile=drawTile(i1);
                GridPane.setRowIndex(tile, row);
                GridPane.setColumnIndex(tile, col);

                //Generate piece graphic
                Circle piece=drawPiece();
                GridPane.setRowIndex(piece,row);
                GridPane.setColumnIndex(piece,col);

                //Add tiles and pieces to board graphic
                pane.getChildren().addAll(tile);
                pane.getChildren().addAll(piece);
                i1=(i1+1)%2;
            }
        }

    }

    public Rectangle drawTile(int ind){

        Rectangle tile = new Rectangle();
        tile.setWidth(tileSize);
        tile.setHeight(tileSize);
        tile.setFill(colorchoice[ind]);

        return tile;
    }

    public Circle drawPiece(Color color){
        Circle piece=new Circle();
        double radius=tileSize/2.0;
        piece.setRadius(radius);
        piece.setFill(color);
        return piece;
    }

    public Circle drawPiece(){
        Circle piece=new Circle();
        double radius=tileSize/2.0;
        piece.setRadius(radius);
        piece.setFill(Color.RED);
        return piece;
    }


    public Pane getPane(){
        return pane;
    }

    private void resizeBoard(){
        // Rescales board to window, if window size is altered.
    }

}
