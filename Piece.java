package Checkers;

public class Piece {

    public enum Role{R1,R2};

    public enum Rank{PAWN,KING};

    private Role role;
    private Rank rank;
    private boolean isHighlighted;

    public Piece(Role role){
        this.role=role;
        this.rank=Rank.PAWN;
        this.isHighlighted=false;
    }

    public Piece(Role role, Rank rank) {
        this.role = role;
        this.rank = rank;
    }

    public Role getRole() {
        return role;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public Rank getRank() {
        return rank;
    }

    public void promote(){
        rank=Rank.KING;
    }

    public void setHighlighted(){
        this.isHighlighted=!isHighlighted;
    }
}