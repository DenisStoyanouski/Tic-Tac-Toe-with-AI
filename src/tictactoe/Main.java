package tictactoe;

import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        start();
    }

    private static final String[][] table = new String [3][3];

    public static Scanner scanner = new Scanner(System.in);

    public static void start() {
        System.out.print("Enter the cells: ");
        String line = scanner.nextLine();
        int letter = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = String.valueOf(line.charAt(letter));
                letter++;
            }
        }
        printTable();
        choiceCell();
    }

    public static void printTable() {
        System.out.println("---------");
        for (String[] strings : table) {
            System.out.print("|");
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(" " + strings[j]);
            }
            System.out.print(" ");
            System.out.println("|");
        }
        System.out.println("---------");

    }

    public static void choiceCell() {
        String input;
        int cellRow = 0;
        int cellColumn = 0;

        do {
               do {
                   System.out.print("Enter the coordinates: ");
                   input = scanner.nextLine().trim();
                   if (processInput(input)) {
                       cellRow = Integer.parseInt(input.substring(0, input.indexOf(" ")));
                       cellColumn = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
                   } else {
                       System.out.println("You should enter numbers!");
                   }
               } while (!processInput(input));

               checkInput(cellRow, cellColumn);

        } while (!gameStatus());

    }

    public static boolean processInput(String input) {

        return input.matches("\\d\\s\\d");
    }

    public static boolean checkInput(int first, int second) {

        boolean checkRange = false;
            if (first >= 1 && first <= 3 && second >= 1 && second <= 3) {
                checkRange = true;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }

        return checkRange && checkPlacement(first, second);
    }

    private static boolean checkPlacement(int first, int second) {
        boolean check = false;
        if (!"_".equals(table[first - 1][second - 1])) {
            System.out.println("This cell is occupied! Choose another one!");
        } else {
            table[first - 1][second - 1] = switchLetter();
            printTable();
            check = true;
        }
        return check;
    }

    private static String switchLetter() {
        String letter;
        int amountX = 0;
        int amountO = 0;
        for (String[] strings : table) {
            for (String cell : strings) {
                if ("X".equals(cell)) {
                    amountX++;
                }
                if ("O".equals(cell)) {
                    amountO++;
                }
            }
        }
        if (amountX == amountO) {
            letter = "X";
        } else {
            letter = "O";
        }
        return letter;
    }
    private static boolean gameStatus() {
        boolean finish = false;
        if (gameDraw() || gameOver()) {
            finish = true;
        } else {
            System.out.println("Game not finished");
        }
        return finish;
    }

    private static boolean gameDraw() {
        boolean draw = false;
        int cellEmpty = 0;
        for (String[] strings : table) {
            for (String cell : strings) {
                if ("_".equals(cell)) {
                    cellEmpty++;
                }
            }
        }
        if (cellEmpty == 0) {
            System.out.println("Draw");
            draw = true;
        }
        return draw;
    }
    
    private static boolean gameOver() {
        boolean gameOver = false;
        // X win
        if ("X".equals(table[0][0]) && "X".equals(table[0][1]) && "X".equals(table[0][2]) || //horizontal line 0
                "X".equals(table[1][0]) && "X".equals(table[1][1]) && "X".equals(table[1][2]) || //horizontal line 1
                "X".equals(table[2][0]) && "X".equals(table[2][1]) && "X".equals(table[2][2]) || //horizontal line 2
                "X".equals(table[0][0]) && "X".equals(table[1][1]) && "X".equals(table[2][2]) || //diagonal from left-top
                "X".equals(table[0][2]) && "X".equals(table[1][1]) && "X".equals(table[2][0]) || //diagonal from right-top
                "X".equals(table[0][0]) && "X".equals(table[1][0]) && "X".equals(table[2][0]) || //vertical left
                "X".equals(table[0][1]) && "X".equals(table[1][1]) && "X".equals(table[2][1]) || //vertical centre
                "X".equals(table[0][2]) && "X".equals(table[1][2]) && "X".equals(table[2][2])) {
            gameOver = true;
            System.out.println("X wins");
        }
        // O win
        if ("O".equals(table[0][0]) && "O".equals(table[0][1]) && "O".equals(table[0][2]) || //horizontal line 0
                "O".equals(table[1][0]) && "O".equals(table[1][1]) && "O".equals(table[1][2]) || //horizontal line 1
                "O".equals(table[2][0]) && "O".equals(table[2][1]) && "O".equals(table[2][2]) || //horizontal line 2
                "O".equals(table[0][0]) && "O".equals(table[1][1]) && "O".equals(table[2][2]) || //diagonal from left-top
                "O".equals(table[0][2]) && "O".equals(table[1][1]) && "O".equals(table[2][0]) || //diagonal from right-top
                "O".equals(table[0][0]) && "O".equals(table[1][0]) && "O".equals(table[2][0]) || //vertical left
                "O".equals(table[0][1]) && "O".equals(table[1][1]) && "O".equals(table[2][1]) || //vertical centre
                "O".equals(table[0][2]) && "O".equals(table[1][2]) && "O".equals(table[2][2])) {
            gameOver = true;
            System.out.println("O wins");
        }
        return gameOver;
    }

}

