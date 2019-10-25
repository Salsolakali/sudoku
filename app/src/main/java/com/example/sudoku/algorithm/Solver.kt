package com.example.sudoku.algorithm

import com.example.sudoku.algorithm.Board.Companion.EMPTY_CELL
import com.example.sudoku.algorithm.Board.Companion.GRID_SIZE

internal class Solver
/**
 * Constructor
 * @param input a 9x9 array representing a sudoku, empty cells are 0
 */
    (input: Array<IntArray>) {

    /**
     * Returns the board object, useful for pretty-printing of the sudoku
     */
    val board: Board = Board(input)

    /**
     * Method to solve the sudoku "in-place" (without creating/copying with a new array)
     * @return true if the sudoku is successfully solved
     */
    fun solve(): Boolean {
        return solve(0, 0)
    }

    /**
     * Backtracking recursive algorithm to solve sudoku
     */
    private fun solve(row: Int, col: Int): Boolean {
        var row = row
        var col = col
        if (row == GRID_SIZE) {
            row = 0
            if (++col == GRID_SIZE) {
                return true
            }
        }
        if (board.getCell(row, col) != EMPTY_CELL) {
            return solve(row + 1, col)
        }
        // For all possible values
        for (`val` in 1..GRID_SIZE) {
            if (isMoveOK(row, col, `val`)) {
                board.setCell(row, col, `val`)
                if (solve(row + 1, col)) {
                    return true
                }
            }
        }
        // Reset the cell to EMPTY to do recursive backtrack and try again
        board.setCell(row, col, EMPTY_CELL)
        return false
    }


    private fun isMoveOK(row: Int, col: Int, `val`: Int): Boolean {
        return !(arrayContains(board.getRow(row), `val`)
                || arrayContains(board.getColumn(col), `val`)
                || arrayContains(board.getRegion(row, col), `val`))
    }

    private fun arrayContains(array: IntArray, `val`: Int): Boolean {
        for (arrayVal in array) {
            if (arrayVal == `val`) {
                // return true and stop the iteration
                return true
            }
        }
        return false
    }
}