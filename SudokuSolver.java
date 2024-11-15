import java.util.Scanner;

public class SudokuSolver {
    private static final int SIZE = 9; // Size of the Sudoku grid

    public static void main(String[] args) {
        int[][] board = new int[SIZE][SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle (use 0 for empty cells):");

        // Prompt the user to enter each row of the Sudoku puzzle
        for (int row = 0; row < SIZE; row++) {
            System.out.println("Enter values for row " + (row + 1) + " (9 numbers separated by spaces):");
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = scanner.nextInt();
            }
        }

        System.out.println("\nOriginal Sudoku Puzzle:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku Puzzle:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
        scanner.close();
    }

    // Solves the Sudoku puzzle using backtracking
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) { // Find an empty cell
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Checks if placing a number at the given row and column is valid
    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Prints the Sudoku board
    private static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col] == 0 ? "." : board[row][col]);
            }
            System.out.println();
        }
    }
}
