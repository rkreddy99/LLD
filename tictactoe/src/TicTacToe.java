import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

import models.Player;
import models.Board;
import models.PieceType;

public class TicTacToe {
    Deque<Player> players;
    Board board;
    
    public TicTacToe(int noOfPlayers, int boardSize) {
        players = new ArrayDeque<>();
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Player("Player " + i, PieceType.values()[i]));
            board = new Board(boardSize);
        }
    }
    
    public void play() {
        boolean isGameOver = false;
        while (!isGameOver) {
            Player playerTurn = players.peekFirst();
            List<List<Integer>> emptyCells = board.getEmptyCells();
            board.printBoard();
            if (emptyCells.size() == 0) {
                System.out.println("Game Over! It's a draw!");
                isGameOver = true;
                break;
            }
            System.out.println(playerTurn.name + " turn. Enter the row and column to place your piece. Ex 1,2");
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();
            String[] values = input.split(",");
            int row = Integer.parseInt(values[0]);
            int column = Integer.parseInt(values[1]);
            if (row < 0 || row >= board.size || column < 0 || column >= board.size) {
                System.out.println("Invalid input. Please enter a valid row and column");
                continue;
            }
            boolean validMove = board.placePiece(row, column, playerTurn.pieceType);
            if (!validMove) {
                System.out.println("Invalid move. Please enter a valid row and column");
                continue;
            }
            if (checkWinner(row, column, playerTurn.pieceType)) {
                System.out.println(playerTurn.name + " wins!");
                isGameOver = true;
                break;
            }
            players.addLast(players.removeFirst());
        }
    }

    public boolean checkWinner(int row, int column, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<board.size;i++) {

            if(board.board[row][i] == null || board.board[row][i] != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<board.size;i++) {

            if(board.board[i][column] == null || board.board[i][column] != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<board.size;i++,j++) {
            if (board.board[i][j] == null || board.board[i][j] != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=board.size-1; i<board.size;i++,j--) {
            if (board.board[i][j] == null || board.board[i][j] != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;

    }
}
