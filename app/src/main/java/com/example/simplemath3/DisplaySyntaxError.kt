package com.example.simplemath3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_display_syntax_error.*

class DisplaySyntaxError : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_syntax_error)

        // Get the Intent that started this activity and extract the string
        val errorMessage = intent.getStringExtra("ERROR_MESSAGE")

        // Capture the layout's TextView and set the string as its text
        errorText.apply {
            text = errorMessage
        }
    }
}