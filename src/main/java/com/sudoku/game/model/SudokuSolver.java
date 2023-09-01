// package com.sudoku.game.model;

// public class SudokuSolver {

//     private static final int EMPTY_CELL = 0;

//     private static final int SIZE = 9;

//     public static boolean solve(int[][] puzzle) {
//         return solveSudoku(puzzle, 0, 0);
//     }

//     private static boolean solveSudoku(int[][] puzzle, int row, int col) {
//         if (row == SIZE - 1 && col == SIZE) {
//             return true; // All cells filled
//         }

//         if (col == SIZE) {
//             row++;
//             col = 0;
//         }

//         if (puzzle[row][col] != EMPTY_CELL) {
//             return solveSudoku(puzzle, row, col + 1);
//         }

//         for (int num = 1; num <= SIZE; num++) {
//             if (isValidPlacement(puzzle, row, col, num)) {
//                 puzzle[row][col] = num;
//                 if (solveSudoku(puzzle, row, col + 1)) {
//                     return true;
//                 }
//                 puzzle[row][col] = EMPTY_CELL; // Backtrack
//             }
//         }
//         return false;
//     }

//     private static boolean isValidPlacement(int[][] puzzle, int row, int col, int num) {
//         // Check row, column, and subgrid constraints
//         for (int i = 0; i < SIZE; i++) {
//             if (puzzle[row][i] == num || puzzle[i][col] == num ||
//                 puzzle[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }
