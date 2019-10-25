package com.example.sudoku.algorithm

import java.util.*

internal class Board
/**
 * Default Constructor
 * @param init a dimension 2 array of integers, empty cells are represented by 0
 */
    (private val board: Array<IntArray>)// A check on the size of the input data could be done here, to throw an InvalidInputException
// Also, it is possible to deep copy the input array to ensure isolation/security.
{
    private var line: CharArray? = null
    /**
     * Returns the specified row
     * @param row row number (starting from 0)
     */
    fun getRow(row: Int): IntArray {
        return board[row]
    }

    /**
     * Returns the specified column
     * @param col column number (starting from 0)
     */
    fun getColumn(col: Int): IntArray {
        val columnView = IntArray(GRID_SIZE)
        for (a in 0 until GRID_SIZE) {
            columnView[a] = board[a][col]
        }
        return columnView
    }

    /**
     * Return the numbers from an area of the board (classically a 3x3 square)
     * @param row row number
     * @param col column number
     * @return the area that enclose the specified row/col values
     */
    fun getRegion(row: Int, col: Int): IntArray {
        val regionView = IntArray(GRID_SIZE)
        val rowBase = row - row % REGION_SIZE
        val colBase = col - col % REGION_SIZE
        var counter = 0
        // REGION_SIZE (3) Rows/Columns from rowBase/ColumnBase
        for (r in rowBase until REGION_SIZE + rowBase) {
            for (c in colBase until REGION_SIZE + colBase) {
                regionView[counter++] = board[r][c]
            }
        }
        return regionView
    }

    /**
     * Getter
     */
    fun getCell(row: Int, col: Int): Int {
        return board[row][col]
    }

    /**
     * Setter
     */
    fun setCell(row: Int, col: Int, `val`: Int) {
        board[row][col] = `val`
    }

    /**
     * Return a string containing the sudoku with region separators
     */
    override fun toString(): String {
        // Exact size of the generated string for the buffer (values + spacers)
        val size = (GRID_SIZE * 2 + 1 + (REGION_SIZE + 1) * 2) * (GRID_SIZE + REGION_SIZE + 1)
        val verticalSpace = " |"
        // A StringBuilder is absolutely needed here
        // use of String concatenation (+) would have really bad performance
        val buffer = StringBuilder(size)
        // Row/Column traversal
        for (a in 0 until GRID_SIZE) {
            val row = board[a]
            if (a % REGION_SIZE == 0) {
                appendLine(buffer)
            }
            for (b in 0 until GRID_SIZE) {
                val value = row[b]
                if (b % REGION_SIZE == 0) {
                    buffer.append(verticalSpace)
                }
                appendValue(buffer, value)
            }
            buffer.append(verticalSpace)
            buffer.append('\n')
        }
        appendLine(buffer)
        return buffer.toString()
    }

    /**
     * Appends the value, or a _ if empty
     */
    private fun appendValue(buffer: StringBuilder, value: Int) {
        buffer.append(' ')
        if (value != EMPTY_CELL) {
            buffer.append(value)
        } else {
            buffer.append('_')
        }
    }

    /**
     * Append a line (separator between region)
     */
    private fun appendLine(buffer: StringBuilder) {
        // Only create the line once
        // Thread safe because the Sudoku class create one new Board for every toString() method call
        if (line == null) {
            line = CharArray(GRID_SIZE * 2 + (REGION_SIZE + 1) * 2)
            Arrays.fill(line, '-')
            //first char as space
            line!![0] = ' '
        }
        buffer.append(line)
        buffer.append('\n')
    }

    companion object {
        // Constants with package visibility
        val EMPTY_CELL = 0
        val GRID_SIZE = 9
        val REGION_SIZE = 3
        val ERROR_MSG_SIZE = "Please provide a " + GRID_SIZE + "x" + GRID_SIZE + " array for Input"
    }
}