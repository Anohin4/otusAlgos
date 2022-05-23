package main;

public class Queen {

    public long getMoves(int n) {
        Rook rook = new Rook();
        Bishop bishop = new Bishop();
        return rook.getMoves(n) | bishop.getMoves(n);
    }

}
