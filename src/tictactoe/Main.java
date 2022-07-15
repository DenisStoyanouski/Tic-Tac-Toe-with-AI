package tictactoe;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        start();
    }

    private static String[][] table = new String [3][3];

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
        for (int i = 0; i < table.length; i++) {
            System.out.print("|");
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(" " + table[i][j]);
            }
            System.out.print(" ");
            System.out.println("|");
        }
        System.out.println("---------");

    }

    public static void choiceCell() {
        int cellRow = 0;
        int cellColumn = 0;
        boolean notInteger = true;

        do {
                System.out.print("Enter the coordinates: ");
                String input = scanner.nextLine().trim();
                if (processInput(input)) {
                    cellRow = Integer.parseInt(input.substring(0, input.indexOf(" ")));
                    cellColumn = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
                } else {
                    System.out.println("You should enter numbers!");
                }

        } while (!checkInput(cellRow, cellColumn));

    }

    public static boolean processInput(String input) {

        boolean b = input.matches("\\d\\s\\d");
        return b;
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
        if (table[first - 1][second - 1] != "_") {
            System.out.println("This cell is occupied! Choose another one!");
        } else {
            check = true;
        }
        return check;
    }

}

