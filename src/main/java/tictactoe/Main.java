package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        boolean newGame = true;
        do {
            game.playGame();
            System.out.print("Do you want to play a new game (y/n)? ");
            String ans = "";
            do {
                ans = new Scanner(System.in).nextLine();
                if (!(ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("y"))) {
                    System.err.print("Please enter (y) or (n): ");
                }
                if (ans.equalsIgnoreCase("n")) newGame = false;
            } while (!(ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("y")));
        } while (newGame);
        System.out.println("Thank You for playing Tic Tac Toe");
    }
}
