package com.sudoku.game.controller;


import java.util.List;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sudoku.game.entity.SudokuBoard;
import com.sudoku.game.model.SudokuGenerator;
import com.sudoku.game.model.SudokuSolver;
import com.sudoku.game.model.UserSudokuSolver;
import com.sudoku.game.repository.SudokuBoardRepository;

@Controller
public class SudokuController {

    @Autowired
    private SudokuBoardRepository SudokuBoardRepository;

    @GetMapping("/input")
    public String showInputForm(Model model) {
        SudokuGenerator generator = new SudokuGenerator();
        model.addAttribute("generator", generator);

        return "input"; 
    }

    @PostMapping("/input")
    public String generatePuzzle(SudokuGenerator generator, Model model) {
        int[][] puzzle = generator.generatePuzzle();
        model.addAttribute("puzzle", puzzle);

        for(int row = 0; row < puzzle.length; row++)
        {
            for(int column = 0; column < puzzle[0].length; column++)
            {
                SudokuBoard sudokuBoard = new SudokuBoard();
                sudokuBoard.setSudokuRow(row);
                sudokuBoard.setSudokuColumn(column);
                sudokuBoard.setValue(puzzle[row][column]);     
                SudokuBoardRepository.save(sudokuBoard);
           
            }
        }
        return "redirect:/sudoku-board"; 
    }

    @GetMapping("/sudoku-board")
    public String showSudokuBoard(Model model)
     {
        List<SudokuBoard> sudokuBoardList = SudokuBoardRepository.findAll();
        int[][] board = new int[9][9];

    for (SudokuBoard sudokuBoard : sudokuBoardList)
    {
        int row = sudokuBoard.getSudokuRow();
        int column = sudokuBoard.getSudokuColumn();
        int value = sudokuBoard.getValue();
        board[row][column] = value;
    }

    model.addAttribute("board", board);
    return "sudoku-board";
}

   @GetMapping("/solve")
    public String solvePuzzle(Model model) {
        List<SudokuBoard> sudokuBoardList = SudokuBoardRepository.findAll();
        int[][] board = new int[9][9];

        for (SudokuBoard sudokuBoard : sudokuBoardList) {
            int row = sudokuBoard.getSudokuRow();
            int column = sudokuBoard.getSudokuColumn();
            int value = sudokuBoard.getValue();
            board[row][column] = value;
        }

        boolean solved = SudokuSolver.solve(board);

        if (solved) {
            model.addAttribute("solvedPuzzle", board);
        } else {
            model.addAttribute("errorMessage", "Could not solve the puzzle.");
        }

        return "solved";
    }


    // @PostMapping("/user_solver")
    // public String solvePuzzle(SudokuGenerator generator, Model model) {
    //     int[][] userInput = generator.generatePuzzle();

    //     if (!UserSudokuSolver.isValidSudoku(userInput)) {
    //         model.addAttribute("errorMessage", "Invalid Sudoku puzzle. Please check your input.");
    //         return "input";
    //     }

    //     boolean solved = SudokuSolver.solve(userInput);

    //     if (solved) {
    //         model.addAttribute("solvedPuzzle", userInput);
    //     } else {
    //         model.addAttribute("errorMessage", "Could not solve the puzzle.");
    //     }

    //     return "Usersolved";
    // }

    // @PostMapping("/validate")
    // public String validatePuzzle(SudokuGenerator generator, Model model) {
    //     int[][] userInput = generator.getUserInput();

    //     // Use SudokuSolver to check if the user input is a valid Sudoku puzzle
    //     SudokuSolver solver = new SudokuSolver(userInput);
    //     boolean isValid = solver.isValidSudoku();

    //     model.addAttribute("isValid", isValid);

    //     return "validation-result";
    // }


}
