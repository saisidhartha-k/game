package com.sudoku.game.controller;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sudoku.game.entity.SudokuBoard;
import com.sudoku.game.model.SudokuGenerator;
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
        return "generated"; 
    }

//     @GetMapping("/input/board")
//     public String showBoard()
//     {
//         return "generated";
//     }

//     public void saveSudokuBoard(int row, int column, int value) {
//     SudokuBoard sudokuBoard = new SudokuBoard();
//     sudokuBoard.setRow(row);
//     sudokuBoard.setColumn(column);
//     sudokuBoard.setValue(value);
//     sudokuBoardRepository.save(sudokuBoard);
// }
}
