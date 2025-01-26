import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        fillSpacesOnBoard();
    }

    public void fillSpacesOnBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void dispBoard() {
        System.out.println("_____________");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("_____________");

        }
    }

    public static void placeMark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = mark;
        } else {
            System.out.println("Invalid Input");
        }
    }

    public static boolean checkIfColWin() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;

            }
        }
        return false;
    }

    public static boolean checkIfRowWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfDiagWin() {
        if (board[1][1] != ' ') {
            if ((board[1][1] == board[2][2] && board[1][1] == board[0][0]) ||
                    (board[1][1] == board[2][0] && board[1][1] == board[0][2])) {
                return true;
            }
        }
        return false;

    }

    public static boolean checkIfDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        TicTacToe t = new TicTacToe();
        HumanPlayer p1 = new HumanPlayer("Alex", 'X');
        MachinePlayer p2 = new MachinePlayer("Bob", 'O');
        Player cp;
        cp = p1;
        while (true) {
            System.out.println(cp.name + "'s Turn");
            cp.makeMove();
            TicTacToe.dispBoard();
            if (TicTacToe.checkIfColWin() || TicTacToe.checkIfDiagWin() || TicTacToe.checkIfRowWin()) {
                System.out.println(cp.name + " Has Won");
                break;
            } else if (TicTacToe.checkIfDraw()) {
                System.out.println("Drawn");
                break;
            } else {
                if (cp == p1) {
                    cp = p2;
                } else {
                    cp = p1;
                }
            }
        }
    }
}

abstract class Player {
    String name;
    char mark;

    Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    abstract void makeMove();

    public boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }

}

class HumanPlayer extends Player {
    // String name;
    // char mark;

    HumanPlayer(String name, char mark) {
        super(name, mark);
        // this.name = name;
        // this.mark = mark;
    }

    public void makeMove() {
        Scanner scanner = new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("Enter the ROW and COL");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while (!isValidMove(row, col));
        TicTacToe.placeMark(row, col, mark);
    }

    // public boolean isValidMove(int row, int col) {
    // if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
    // if (TicTacToe.board[row][col] == ' ') {
    // return true;
    // }
    // }
    // return false;
    // }
}

class MachinePlayer extends Player {
    // String name;
    // char mark;

    MachinePlayer(String name, char mark) {
        super(name, mark);
        // this.name = name;
        // this.mark = mark;
    }

    public void makeMove() {
        Scanner scanner = new Scanner(System.in);
        int row;
        int col;
        do {
            // System.out.println("Enter the ROW and COL");
            Random r = new Random();

            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (!isValidMove(row, col));
        TicTacToe.placeMark(row, col, mark);
    }

    // public boolean isValidMove(int row, int col) {
    // if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
    // if (TicTacToe.board[row][col] == ' ') {
    // return true;
    // }
    // }
    // return false;
    // }
}