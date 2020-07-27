package com.example.simplemath3

import android.os.Bundle
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import com.havardp.calculator.interpreter.OrdinaryResult
import kotlinx.android.synthetic.main.activity_display_solve_equation.*


class DisplayOrdinaryResult : AppCompatActivity() {
    private var expandableAdapter: ExpandableAdapter? = null
    private lateinit var expList: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_solve_equation)

        // Get the Intent that started this activity and extract the string
        val result = intent.getSerializableExtra("result") as OrdinaryResult
        // Capture the layout's TextView and set the string as its text

        val inputView = inputLatex.apply {
            text = result.input
        }

        val solutionView = solutionLatex.apply {
            text = result.result
        }

        // code for adapter
        var childList = ArrayList<ArrayList<String>>()
        var test = ArrayList<String>()
        test.add("test1")
        test.add("test")
        test.add("test2")
        childList.add(test)
        var parents = arrayOf("parent")

        expList = exp_list
        expandableAdapter = ExpandableAdapter(this, childList, parents)
        expList.setAdapter(expandableAdapter)
    }

}