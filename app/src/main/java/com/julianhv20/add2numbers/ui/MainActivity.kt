package com.julianhv20.add2numbers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.julianhv20.add2numbers.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    fun operationAction(view: View) {
        if (view is Button && canAddOperation){
            tvWorkings.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun numberAction(view: View) {
        if (view is Button){

            if (view.text == "."){
                if (canAddDecimal) tvWorkings.append(view.text)
                canAddDecimal = false

            } else tvWorkings.append(view.text)

            canAddOperation = true
        }
    }

    fun allClearAction(view: View) {
        //Clear textview
        tvWorkings.text = ""
        tvResult.text = ""
    }

    fun backSpaceAction(view: View) {
        //Remove the last character

        val inputLength = tvWorkings.length()

        if (inputLength > 0){
            tvWorkings.text = tvWorkings.text.subSequence(0, inputLength - 1)
        }
    }


    fun equalsAction(view: View) {}
}