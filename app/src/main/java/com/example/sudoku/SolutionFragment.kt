package com.example.sudoku


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class SolutionFragment : Fragment() {
    lateinit var sudokuFinal: Array<IntArray?>
    lateinit var cellGroupFragments : Array<CellGroupFragment>
    lateinit var layoutCellGroupFragment: IntArray
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_solution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (i in 0..8){
            sudokuFinal[i] = arguments?.getIntArray(i.toString())
        }

        initFragments()

        /*layoutCellGroupFragment = intArrayOf(
            R.id.cellGroupFragment,
            R.id.cellGroupFragment2,
            R.id.cellGroupFragment3,
            R.id.cellGroupFragment4,
            R.id.cellGroupFragment5,
            R.id.cellGroupFragment6,
            R.id.cellGroupFragment7,
            R.id.cellGroupFragment8,
            R.id.cellGroupFragment9
        )*/

        //fillSudoku(sudokuFinal)


    }

    private fun initFragments() {
        for(i in 0..8){
            cellGroupFragments[i] = sudokuFinal[i]?.let { CellGroupFragment(it) }!!
        }
    }

    /*private fun fillSudoku(sudokuFinal: Array<IntArray?>) {

    }*/


}
