package com.sudoku.game.model;

public class UserSudokuSolver {

    private static final int EMPTY_CELL = 0;
    private static final int SIZE = 9;

    public static boolean solve(int[][] puzzle) {
        return solveSudoku(puzzle, 0, 0);
    }

    public static boolean isValidSudoku(int[][] puzzle) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int num = puzzle[row][col];
                if (num != EMPTY_CELL && !isValidPlacement(puzzle, row, col, num)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean solveSudoku(int[][] puzzle, int row, int col) {
     if (row == SIZE - 1 && col == SIZE) {
            return true; 
        }

        if (col == SIZE) {
            row++;
            col = 0;
        }

        if (puzzle[row][col] != EMPTY_CELL) {
            return solveSudoku(puzzle, row, col + 1);
        }

        for (int num = 1; num <= SIZE; num++) {
            if (isValidPlacement(puzzle, row, col, num)) {
                puzzle[row][col] = num;
                if (solveSudoku(puzzle, row, col + 1)) {
                    return true;
                }
                puzzle[row][col] = EMPTY_CELL; 
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] puzzle, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (puzzle[row][i] == num || puzzle[i][col] == num ||
                puzzle[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }
}