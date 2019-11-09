package Checkers;

public class PieceTest {
    public static void main(String[] args) {
        Piece p1=new Piece(Piece.Role.R1);
        System.out.println(p1.getRole());
        System.out.println(p1.getRank());
        p1.promote();
        System.out.println(p1.getRank());


        System.out.println();
        Piece p2=new Piece(Piece.Role.R2, Piece.Rank.PAWN);
        System.out.println(p2.getRole());
        System.out.println(p2.getRank());

        System.out.println();
        Piece k1=new Piece(Piece.Role.R2, Piece.Rank.KING);
        System.out.println(k1.getRole());
        System.out.println(k1.getRank());


    }
}
