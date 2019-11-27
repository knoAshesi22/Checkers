package Checkers;

import Checkers.Grid;

public class GridTest {
    public static void main(String[] args) {
//        Grid tgrid=new Grid();
////        tgrid.printGrid();
////        System.out.println();
////        tgrid.setGrid();
////        tgrid.printGrid();

        Grid cgrid=new Grid(8);
        cgrid.printOccupiedv2();
//        cgrid.printPieces();
        System.out.println();
        cgrid.setGrid();
//        cgrid.printOccupiedv2();
        System.out.println();
        cgrid.printOccupied();
//        cgrid.printPieces();

        System.exit(0);



//        System.out.println();
        int[] origin={2,1};
        int[] dest={2,2};
        origin= new int[]{1, 1};

        cgrid.addPiece(origin);
        cgrid.printPieces();
        System.out.println();

        cgrid.movePiece(origin,origin);
        cgrid.printPieces();

//        cgrid.movePiece(origin,dest);
//        cgrid.printPieces();
//        System.out.println();





    }
}
