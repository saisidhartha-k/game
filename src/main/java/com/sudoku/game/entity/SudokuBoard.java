package com.sudoku.game.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SudokuBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

 //   @Column("sudokuColumn")
    private int sudokuRow;
    private int sudokuColumn;
    private int value;

}

