package test.java;

import static test.java.utils.ChessTestTemplate.runTest;

import java.io.IOException;
import main.Bishop;
import main.King;
import main.Knight;
import main.Queen;
import main.Rook;

public class ChessTest {

    public static void main(String[] args) throws IOException {
        King king = new King();
        Knight knight = new Knight();
        Rook rook = new Rook();
        Bishop bishop = new Bishop();
        Queen queen = new Queen();
        System.out.println("----King tests----");
        runTest("king", 10, king::getMoves);
        System.out.println("----Knight tests----");
        runTest("knight", 10, knight::getMoves);
        System.out.println("----Rook tests----");
        runTest("rook", 10, rook::getMoves);
        System.out.println("----Bishop tests----");
        runTest("bishop", 10, bishop::getMoves);
        System.out.println("----Queen tests----");
        runTest("queen", 10, queen::getMoves);
    }

}
