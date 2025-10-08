package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    private final Scanner scanner;
    char[][] grid = new char[3][3];
    private char player_1;
    private char player_2;
    private char currentPlayer;
    private int freeSpaces;

    public TicTacToe() {
        player_1 = 'X';
        player_2 = 'O';
        scanner = new Scanner(System.in);
        freeSpaces = 9;
        currentPlayer = ' ';
    }

//    public TicTacToe(char playerChar1, char playerChar2) {
//        player_1 = playerChar1;
//        player_2 = playerChar2;
//        scanner = new Scanner(System.in);
//    }

    private void newGame() {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    private void printGame() {
        for (int i = 0; i < grid.length; i++) {
            System.out.println("-------------------");
            System.out.printf("|  %c  |  %c  |  %c  |", grid[i][0], grid[i][1], grid[i][2]);
            System.out.println();
        }
        System.out.println("-------------------");
    }

    private boolean checkGame() {

        System.out.println("Checked grid");

        boolean isRowWin = checkRows();
        if (isRowWin) return true;

        boolean isColWin = checkCols();
        if (isColWin) return true;

        boolean isLeadingDiagonalWin = checkLeadingDiagonal(); // Top-Left to Bottom-Right
        if (isLeadingDiagonalWin) return true;

        boolean isTrailingDiagonalWin = checkTrailingDiagonal(); // Top-Right to Bottom-Left
        if (isTrailingDiagonalWin) return true;

        return false;
    }

    private boolean checkTrailingDiagonal() {
        return (grid[0][2] == currentPlayer) && (grid[1][1] == currentPlayer) && (grid[2][0] == currentPlayer);
    }

    private boolean checkLeadingDiagonal() {
        return (grid[0][0] == currentPlayer) && (grid[1][1] == currentPlayer) && (grid[2][2] == currentPlayer);
    }

    private boolean checkCols() {
        for (int j = 0; j < grid[0].length; j++) {
            if ((grid[0][j] != currentPlayer)) continue;
            if ((grid[0][j] == currentPlayer) && (grid[1][j] == currentPlayer) && (grid[2][j] == currentPlayer))
                return true;
        }
        return false;
    }

    private boolean checkRows() {
        for (int i = 0; i < grid.length; i++) {
            if ((grid[i][0] != currentPlayer)) continue;
            if ((grid[i][0] == currentPlayer) && (grid[i][1] == currentPlayer) && (grid[i][2] == currentPlayer))
                return true;
        }
        return false;
    }

    public void playGame() {
        boolean continuePlaying = true;
        newGame();
        coinToss();
        printGame();
        do {
            getInput();
            System.out.println("Number of free spaces: " + freeSpaces);
            if (freeSpaces <= 0) {
                continuePlaying = false;
            } else if (freeSpaces < 5) {
                boolean playerWin = checkGame();
                if (playerWin) continuePlaying = false;
            }

            if (currentPlayer == player_1) currentPlayer = player_2;
            else currentPlayer = player_1;

            printGame();
        } while (continuePlaying);
        gameOver();
    }

    private void coinToss() {
        double randomVal = Math.random() * 100;
        if (randomVal > 50) {
            currentPlayer = player_2;
            System.out.println("Player " + player_2 + " goes first");
        } else {
            currentPlayer = player_1;
            System.out.println("Player" + player_1 + " goes first");
        }
    }

    private void placeInput(int num) throws Exception {
        num--;
        int row = num / 3;
        int col = num % 3;

        char gridVal = grid[row][col];
        if (gridVal == player_1 || gridVal == player_2) {
            System.err.println("ERROR: Place Taken. Enter a different number.");
            throw new Exception();
        } else {
            grid[row][col] = currentPlayer;
        }

        freeSpaces--;
    }

    private int getInput() {

        int number = -1;
        boolean inputNumWrong = false;

        do {
            try {
                System.out.print(String.format("Player %c, Enter a grid number: ", currentPlayer));
                number = scanner.nextInt();
                if (0 >= number || number > 9) {
                    inputNumWrong = true;
                    System.err.println("Number is out of range [1 - 9]");
                    throw new Exception();
                } else {
                    inputNumWrong = false;
                    placeInput(number);
                }
            } catch (InputMismatchException e) {
                System.err.println("ERROR: Please Enter a number.");
                scanner.next(); // remove the buffer.
            } catch (Exception _) {
            }
        } while (inputNumWrong);
        return number;
    }

    public void gameOver() {
        System.out.println();
        System.out.println("Congratulations! Player " + currentPlayer + " has won the game.");
        System.out.println();
    }

//    public void setPlayer1(char letter) {
//        player_1 = letter;
//    }
//
//    public void setPlayer2(char letter) {
//        player_1 = letter;
//    }
}
