import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Snake {

    static String[][] gameBoard = {
            {" # # # # # # # # # # # # # # # # # #"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {" # # # # # # # # # # # # # # # # # #"}
    };

    static List<CoordPair> user = new ArrayList<CoordPair>();

    static int size = 1;
    static char dir = ' ';

    static int currX = (gameBoard.length - 1) / 2;
    static int currY = (gameBoard[1].length - 1) / 2;

    static int prevX = (gameBoard.length - 1) / 2;
    static int prevY = (gameBoard[1].length - 1) / 2;

    static int candyX;
    static int candyY;

    public static void main (String[] args) {
        System.out.println("Welcome to my game of text-based Snake. By: Anand Krishnan");
        System.out.println();
        printGameBoard();

        user.add(new CoordPair(currX, currY));

        Scanner scan = new Scanner(System.in);
        System.out.println("If you want to quit, please type X. Otherwise, type anything else: ");
        String input = scan.nextLine();
        drawSnake();
        spawnCandy();

        while (!input.equalsIgnoreCase("X")) { //GAME LOOP
            printGameBoard();
            System.out.println("If you want to quit, please type X. Otherwise, move in a direction (W, A, S, D): ");
            input = scan.nextLine();

            prevX = user.get(user.size() - 1).getX();
            prevY = user.get(user.size() - 1).getY();

            if (input.equalsIgnoreCase("W")) {
                up();
            } else if (input.equalsIgnoreCase("A")) {
                left();
            } else if (input.equalsIgnoreCase("S")) {
                down();
            } else if (input.equalsIgnoreCase("D")) {
                right();
            } else {
                System.out.println("Please try again.");
            }

            if (user.get(0).getX() == candyY && user.get(0).getY() == candyX) { //snake has found the candy
                size++;
                user.add(0, new CoordPair(candyY, candyX));
                spawnCandy();
            }

            if (size == 1) {
                //removeSnake(prevX, prevY);
            }

            drawSnake();
            System.out.println(size);

        }

    }

    private static void drawSnake() {
        for (CoordPair elem : user) {
            gameBoard[elem.getX()][elem.getY()] = "o";
        }
    }

    private static void up() {
        for (CoordPair elem : user) {
            gameBoard[elem.getX()][elem.getY()]= " ";
            elem.setX(elem.getX() - 1);
        }
        dir = 'W';
    }

    private static void down() {
        for (CoordPair elem : user) {
            gameBoard[elem.getX()][elem.getY()]= " ";
            elem.setX(elem.getX() + 1);
        }
        dir = 'S';
    }

    private static void left() {
        for (CoordPair elem : user) {
            gameBoard[elem.getX()][elem.getY()]= " ";
            elem.setY(elem.getY() - 1);
        }
        dir = 'A';
    }

    private static void right() {
        for (CoordPair elem : user) {
            gameBoard[elem.getX()][elem.getY()]= " ";
            elem.setY(elem.getY() + 1);
        }
        dir = 'D';
    }

    private static void removeSnake(int x, int y) { //removes the last element of the array from the board
        gameBoard[x][y] = " ";
    }

    private static void printGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    private static void spawnCandy() {
        candyX = 0;
        candyY = 0;

        candyX = (int) (Math.random() * (gameBoard[1].length - 1)) + 1;
        candyY = (int) (Math.random() * (gameBoard.length - 1)) + 1;

        while (candyX == currX && candyY == currY) {
            candyX = (int) (Math.random() * (gameBoard[1].length - 1)) + 1;
            candyY = (int) (Math.random() * (gameBoard.length - 1)) + 1;
        }

        gameBoard[candyY][candyX] = "*";
    }
}