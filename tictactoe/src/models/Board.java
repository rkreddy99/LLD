package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    public int size;
    public PieceType[][] board;
    public Board(int size) {
        this.size = size;
        this.board = new PieceType[size][size];
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((board[i][j] == null ? " " : board[i][j] ) + " |");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> getEmptyCells() {
        List<List<Integer>> emptyCells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    emptyCells.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return emptyCells;
    }

    public boolean placePiece(int row, int column, PieceType pieceType) {
        if (board[row][column] != null) {
            return false;
        }
        board[row][column] = pieceType;
        return true;
    }
}
