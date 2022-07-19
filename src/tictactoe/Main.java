package tictactoe;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static final String[][] table = new String [3][3];

    private static final List<String> gameMode =List.of("user", "easy", "medium");

    public static Scanner scanner = new Scanner(System.in);

    private static boolean gameEnd;

    private static String playerX;

    private static String playerO;

    public static void main(String[] args) {
        isMenu();
    }


    private static void isMenu() {
        List<String> parametersOfGame;
        do {
            System.out.print("Input command: ");
            parametersOfGame = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
            if(areParametersRight(parametersOfGame) && "start".equals(parametersOfGame.get(0))) {
                startGame(parametersOfGame);
            } else if(!"exit".equals(parametersOfGame.get(0))) {
                System.out.println("Bad parameters!");
            }

        } while (!"exit".equals(parametersOfGame.get(0)));
    }

    private static boolean areParametersRight(List<String> parametersOfGame) {
        boolean correct = false;
        if (parametersOfGame.size() == 3) {
            if (gameMode.contains(parametersOfGame.get(1)) && gameMode.contains(parametersOfGame.get(2))) {
                correct = true;
            }
        }
        return correct;
    }


    private static void startGame(List<String> parametersOfGame) {
        //display empty table
        for (int i = 0; i < table.length; i++) {
            Arrays.fill(table[i], "_");
        }
        playerX = parametersOfGame.get(1);
        playerO = parametersOfGame.get(2);
        printTable();
        do {
            if (!getGameStatus()) {
                movePlayerX(playerX);
            }
            if(!getGameStatus()) {
                movePlayerO(playerO);
            }
        } while(!gameEnd);
    }
    public static void movePlayerX(String playerX) {
        switch (playerX) {
            case "user" : moveUser("X");
            break;
            case "easy" : moveEasyAI("X");
            break;
            case "medium" : moveMediumAI("X");
            break;
            default:
                break;
        }
    }

    public static void movePlayerO(String playerO) {
        switch (playerO) {
            case "user" : moveUser("O");
                break;
            case "easy" : moveEasyAI("O");
                break;
            case "medium" : moveMediumAI("O");
                break;
            default:
                break;
        }
    }

    public static void printTable() {
        System.out.println("---------");
        for (String[] strings : table) {
            System.out.print("|");
            for (String cell : strings) {
                System.out.print(" " + cell);
            }
            System.out.print(" ");
            System.out.println("|");
        }
        System.out.println("---------");

    }

    public static void moveUser(String letter) {
        String input;
        int cellRow = 0;
        int cellColumn = 0;
        do {
            System.out.print("Enter the coordinates: ");
            input = scanner.nextLine().trim();
            if (processInput(input)) {
                cellRow = Integer.parseInt(input.substring(0, input.indexOf(" ")));
                cellColumn = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            } else {
                System.out.println("You should enter numbers!");
            }
        } while (!checkPlacement(cellRow, cellColumn, letter));
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
            return checkRange;
    }

    private static boolean checkPlacement(int first, int second, String letter) {
        boolean check = false;
        if (checkInput(first, second)) {
            if (!"_".equals(table[first - 1][second - 1])) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                table[first - 1][second - 1] = letter;
                printTable();
                check = true;
            }
        }
        return check;
    }

    private static boolean getGameStatus() {
        boolean finish = false;
        if (gameDraw() || gameOver()) {
            finish = true;
            gameEnd = true;
        } else {
            gameEnd = false;
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

    private static void moveEasyAI(String letter) {
        Random random = new Random();
        boolean cellIsEmpty;
        do{
            // create random coordinate;
            int row = random.nextInt(3);
            int column = random.nextInt(3);
            // check cell with this coordinate;
            if ("_".equals(table[row][column])) {
                table[row][column] = letter;
                cellIsEmpty = true;
                System.out.println("Making move level \"easy\"");
                printTable();
            } else {
                cellIsEmpty = false;
            }
        } while (!cellIsEmpty);
    }

    private static void moveMediumAI(String letter) {
        Random random = new Random();

        if (doWinStep(letter) || doBlockStep(letter)) {
            boolean cellIsEmpty;
            do{
                // create random coordinate;
                int row = random.nextInt(3);
                int column = random.nextInt(3);
                // check cell with this coordinate;
                if ("_".equals(table[row][column])) {
                    table[row][column] = letter;
                    cellIsEmpty = true;
                    System.out.println("Making move level \"medium\"");
                    printTable();
                } else {
                    cellIsEmpty = false;
                }
            } while (!cellIsEmpty);
        }

    }

    private static boolean doWinStep(String letter) {
        boolean stepDone = false;
        String first = String.format("_%s%s", letter, letter);
        String second = String.format("%s_%s", letter, letter);
        String third = String.format("%s%s_", letter, letter);

        for (int line = 0; line < table.length; line++) {
            for (int column = 0; column < table[line].length; column++) {
                if ("_".equals(table[line][column])) {
                    if (Arrays.stream(table[line]).toString().equals(first) ||
                            Arrays.stream(table[line]).toString().equals(second) ||
                            Arrays.stream(table[line]).toString().equals(third)) {
                        table[line][column] = letter;
                        stepDone = true;
                    }
                    if (Objects.equals(table[0][column] + table[1][column] + table[2][column], first) ||
                            Objects.equals(table[0][column] + table[1][column] + table[2][column], second) ||
                            Objects.equals(table[0][column] + table[1][column] + table[2][column], third)) {
                        table[line][column] = letter;
                        stepDone = true;
                    }
                    if (column == line) {
                        if (Objects.equals(table[0][0] + table[1][1] + table[2][2], first) ||
                                Objects.equals(table[0][0] + table[1][1] + table[2][2], second) ||
                                Objects.equals(table[0][0] + table[1][1] + table[2][2], third)) {
                            table[line][column] = letter;
                            stepDone = true;
                        }
                    }
                    if (line == 0 && column == 2 || line == 2 && column == 0) {
                        if (Objects.equals(table[0][2] + table[1][1] + table[2][0], first) ||
                                Objects.equals(table[0][2] + table[1][1] + table[2][0], second) ||
                                Objects.equals(table[0][2] + table[1][1] + table[2][0], third)) {
                            table[line][column] = letter;
                            stepDone = true;
                        }

                    }
                }
            }
        }
        return stepDone;
    }

    private static boolean doBlockStep(String letter) {
        boolean stepDone = false;
        String oppositeLetter = "";
        switch (letter) {
            case "X" : oppositeLetter = "O";
                break;
            case "O" : oppositeLetter = "X";
                break;
            default:
                break;
        }

        String first = String.format("_%s%s", oppositeLetter, oppositeLetter);
        String second = String.format("%s_%s", oppositeLetter, oppositeLetter);
        String third = String.format("%s%s_", oppositeLetter, oppositeLetter);

        for (int line = 0; line < table.length; line++) {
            for (int column = 0; column < table[line].length; column++) {
                if ("_".equals(table[line][column])) {
                    if (Arrays.stream(table[line]).toString().equals(first) ||
                            Arrays.stream(table[line]).toString().equals(second) ||
                            Arrays.stream(table[line]).toString().equals(third)) {
                        table[line][column] = letter;
                        stepDone = true;
                    }
                    if (Objects.equals(table[0][column] + table[1][column] + table[2][column], first) ||
                            Objects.equals(table[0][column] + table[1][column] + table[2][column], second) ||
                            Objects.equals(table[0][column] + table[1][column] + table[2][column], third)) {
                        table[line][column] = letter;
                        stepDone = true;
                    }
                    if (column == line) {
                        if (Objects.equals(table[0][0] + table[1][1] + table[2][2], first) ||
                                Objects.equals(table[0][0] + table[1][1] + table[2][2], second) ||
                                Objects.equals(table[0][0] + table[1][1] + table[2][2], third)) {
                            table[line][column] = letter;
                            stepDone = true;
                        }
                    }
                    if (line == 0 && column == 2 || line == 2 && column == 0) {
                        if (Objects.equals(table[0][2] + table[1][1] + table[2][0], first) ||
                                Objects.equals(table[0][2] + table[1][1] + table[2][0], second) ||
                                Objects.equals(table[0][2] + table[1][1] + table[2][0], third)) {
                            table[line][column] = letter;
                            stepDone = true;
                        }

                    }
                }
            }
        }
        return stepDone;
    }

}

