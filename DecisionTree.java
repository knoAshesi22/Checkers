package Checkers;
import Checkers.Piece;
import Checkers.Grid;

public class DecisionTree {
    Grid.Moveset moveset;

    class Node{
        int[] coord;
        Node[] nextMoves;


    }

    public DecisionTree(Grid.Moveset moveset){
        this.moveset=moveset;
    }





}
