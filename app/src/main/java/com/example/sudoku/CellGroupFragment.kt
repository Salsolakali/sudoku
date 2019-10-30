package com.example.sudoku


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface


class CellGroupFragment : Fragment() {
    private var groupId: Int = 0
    private var mListener: OnFragmentInteractionListener? = null
    lateinit var view1: View
    var nums: IntArray? = null

    fun newInstance(ints: IntArray): CellGroupFragment {
        val args = Bundle()
        args.putIntArray("nums", ints)
        val f = CellGroupFragment()
        f.arguments = args
        return f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            nums = arguments!!.getIntArray("nums")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view1 = inflater.inflate(R.layout.fragment_cell_group, container, false)

        //Set textview click listeners
        val textViews = intArrayOf(
            R.id.textView1,
            R.id.textView2,
            R.id.textView3,
            R.id.textView4,
            R.id.textView5,
            R.id.textView6,
            R.id.textView7,
            R.id.textView8,
            R.id.textView9
        )
        for (textView1 in textViews) {
            val textView = view!!.findViewById<TextView>(textView1)
            textView.setOnClickListener { view ->
                mListener!!.onFragmentInteraction(
                    groupId,
                    Integer.parseInt(view.tag.toString()),
                    view
                )
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for(i in 0..8){
            setValue(i, nums?.get(i))
        }
    }

    fun fill(nums: IntArray){}

    fun setValue(position: Int, value: Int?) {
        val textViews = intArrayOf(
            R.id.textView1,
            R.id.textView2,
            R.id.textView3,
            R.id.textView4,
            R.id.textView5,
            R.id.textView6,
            R.id.textView7,
            R.id.textView8,
            R.id.textView9
        )
        val currentView = view!!.findViewById<TextView>(textViews[position])
        currentView.text = value.toString()
        currentView.setTextColor(Color.BLACK)
        currentView.setTypeface(null, Typeface.BOLD)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(groupId: Int, cellId: Int, view: View)
    }
}// Required empty public constructor
