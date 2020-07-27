package com.example.simplemath3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.havardp.calculator.interpreter.Interpreter
import com.havardp.calculator.interpreter.OrdinaryResult
import com.havardp.calculator.interpreter.QuadraticResult
import com.havardp.calculator.interpreter.Result
import com.havardp.calculator.lexer.Lexer
import com.havardp.calculator.parser.Parser
import com.havardp.exception.InvalidSyntaxException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** call changeActivity with the input string */
    fun evaluateOnClick(view: View) {
        val input = input.text.toString()
        changeActivity(input)
    }

    fun exampleOnClick(view: View){
        val equation = view.tag.toString()
        changeActivity(equation)
    }

    /** Change to the appropriate display result activity */
    private fun changeActivity(str: String){
        try{
            val result = getResult(str)

            if(result is OrdinaryResult){
                val intent = Intent(this, DisplayOrdinaryResult::class.java).apply {
                    putExtra("result", result)
                }
                startActivity(intent)
            }

            else if(result is QuadraticResult){
                val intent = Intent(this, DisplayQuadraticResult::class.java).apply {
                    putExtra("result", result)
                }
                startActivity(intent)
            }
        } catch (e: InvalidSyntaxException){
            val intent = Intent(this, DisplaySyntaxError::class.java).apply {
                putExtra("ERROR_MESSAGE", e.message)
            }
            startActivity(intent)
        }
    }

    /** Calls the calculator and returns the result object (or a syntax error) */
    private fun getResult(str: String): Result {
        val lexer = Lexer(str)
        val parser = Parser(lexer)
        val interpreter = Interpreter(parser)
        return interpreter.interpret()
    }
}