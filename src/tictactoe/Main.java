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
        String input;
        String first = "";
        String second ="";
        do {
            System.out.print("Enter the coordinates: ");
            input = scanner.nextLine().trim();
            if (input.indexOf(" ") != -1) {
                first = input.substring(0, input.indexOf(" "));
                second = input.substring(input.indexOf(" ") + 1);
            } else {
                System.out.println("You should enter numbers!");
            }
        } while (!checkInput(first, second));

    }

    public static boolean checkInput(String first, String second) {

        int cellRow = 0;
        int cellColumn = 0;
        boolean firstCheck = false;
        boolean secondCheck = false;
        int[] coordinate = new int[2];
        if (!first.isEmpty() && first.matches("\\d+")) {
            cellRow = Integer.parseInt(first);
            if (cellRow >= 1 && cellRow <= 3 ) {
                firstCheck = true;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                firstCheck = false;
            }
        } else {
            System.out.println("You should enter numbers!");
            firstCheck = false;
        }

        if (!second.isEmpty() && second.matches("\\d+")) {
            cellColumn = Integer.parseInt(first);
            if (cellColumn >= 1 && cellColumn <= 3 ) {
                secondCheck = true;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                secondCheck = false;
            }
        } else {
            System.out.println("You should enter numbers!");
            secondCheck = true;
        }
        checkPlacement(cellRow, cellColumn);
        return firstCheck && secondCheck && checkPlacement(cellRow, cellColumn);
    }

    private static boolean checkPlacement(int cellRow, int cellColumn) {
        boolean check = false;
        if (table[cellRow][cellColumn] != "_") {
            System.out.println("This cell is occupied! Choose another one!");
        } else {
            check = true;
        }
        return check;
    }

}

