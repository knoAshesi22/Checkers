package Checkers;

public class GridTest {
    public static void main(String[] args) {
        Grid tgrid=new Grid();
//        tgrid.printGrid();
//        System.out.println();
//        tgrid.setGrid();
//        tgrid.printGrid();

        Grid cgrid=new Grid(10);
//        cgrid.printOccupied();
        cgrid.printPieces();
        System.out.println();
        cgrid.setGrid();
//        cgrid.printOccupied();
        cgrid.printPieces();


        System.out.println();
        int[] origin={2,1};
        int[] dest={3,0};
        tgrid.movePiece(origin,dest);
        tgrid.printPieces();


    }
}
