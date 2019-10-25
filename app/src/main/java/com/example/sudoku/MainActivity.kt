package com.example.sudoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sudoku.algorithm.Board
import com.example.sudoku.algorithm.Solver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var sudoku = arrayOf(
            intArrayOf(8,6,0,0,2,0,0,0,0),
            intArrayOf(0,0,0,7,0,0,0,5,9),
            intArrayOf(0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,6,0,8,0,0),
            intArrayOf(0,4,0,0,0,0,0,0,0),
            intArrayOf(0,0,5,3,0,0,0,0,7),
            intArrayOf(0,0,0,0,0,0,0,0,0),
            intArrayOf(0,2,0,0,0,0,6,0,0),
            intArrayOf( 0,0,7,5,0,9,0,0,0))
        Solver(sudoku).solve()
        Log.e("Alberto",Board(sudoku).toString())
    }
}
