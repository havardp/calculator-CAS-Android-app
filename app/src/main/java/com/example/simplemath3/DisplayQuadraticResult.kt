package com.example.simplemath3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import com.havardp.calculator.interpreter.OrdinaryResult
import com.havardp.calculator.interpreter.QuadraticResult
import kotlinx.android.synthetic.main.activity_display_quadratic_result.*
import kotlinx.android.synthetic.main.activity_display_solve_equation.*
import kotlinx.android.synthetic.main.activity_display_solve_equation.inputLatex
import kotlinx.android.synthetic.main.activity_display_solve_equation.solutionLatex
import java.util.*

class DisplayQuadraticResult : AppCompatActivity() {
    private var expandableAdapter: ExpandableQuadraticAdapter? = null
    private var test: ExpandableAdapter? = null
    private lateinit var expList: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_quadratic_result)

        // Get the Intent that started this activity and extract the result object we sent
        val result = intent.getSerializableExtra("result") as QuadraticResult

        // Capture the layout's TextView and set the string as its text
        inputLatex.apply {
            text = "\\(${result.input}\\)"
        }

        solutionLatex.apply {
            text = "\\(${result.quadraticFormula}\\)"
        }

        root1_latex.apply {
            text = "\\(${result.root1.result}\\)"
        }

        root2_latex.apply {
            text = "\\(${result.root2.result}\\)"
        }

        if(result.root1.result.toIntOrNull() != null && result.root2.result.toIntOrNull() != null){
            if(result.root1.result.toInt() > 0 && result.root2.result.toInt() > 0)
                altsolution.text = "\\( (x-${result.root1.result})(x-${result.root2.result})=0 \\)"
            else if(result.root1.result.toInt() < 0 && result.root2.result.toInt() > 0)
                altsolution.text = "\\( (x+${result.root1.result.toInt().unaryMinus()})(x-${result.root2.result})=0 \\)"
            else if(result.root1.result.toInt() > 0 && result.root2.result.toInt() < 0)
                altsolution.text = "\\( (x-${result.root1.result})(x+${result.root2.result.toInt().unaryMinus()})=0 \\)"
            else
                altsolution.text = "\\( (x+${result.root1.result.toInt().unaryMinus()})(x+${result.root2.result.toInt().unaryMinus()})=0 \\)"
        }else{
            altform.visibility = View.GONE
        }


        val solveSteps1 = ArrayList<String>(result.root1.solveSteps)
        val explanationSteps1 = ArrayList<String>(result.root1.explanationSteps)
        val solveSteps2 = ArrayList<String>(result.root2.solveSteps)
        val explanationSteps2 = ArrayList<String>(result.root2.explanationSteps)

        val solveSteps = arrayListOf(solveSteps1, solveSteps2)
        val explanationSteps = arrayListOf(explanationSteps1, explanationSteps2)

        expList = exp_list1
        expandableAdapter = ExpandableQuadraticAdapter(this, solveSteps, explanationSteps)
        expList.setAdapter(expandableAdapter)
    }
}