package com.sudoku.game.repository;

import org.springframework.stereotype.Repository;
import com.sudoku.game.entity.SudokuBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface SudokuBoardRepository extends JpaRepository<SudokuBoard, Integer> {

    
}
