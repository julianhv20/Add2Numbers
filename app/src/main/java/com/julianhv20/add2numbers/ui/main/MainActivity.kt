package com.julianhv20.add2numbers.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.julianhv20.add2numbers.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.workings.observe(this) {work ->
            tvWorkings.text = work
        }

        mainViewModel.result.observe(this) {res ->
            tvResult.text = res
        }

    }

    fun operationAction(view: View) {
        mainViewModel.operationAction(view)
    }

    fun numberAction(view: View) {
        mainViewModel.numberAction(view)
    }

    fun allClearAction(view: View) {
        mainViewModel.allClearAction()
    }

    fun backSpaceAction(view: View) {
        mainViewModel.backSpaceAction(view)
    }


    fun equalsAction(view: View) {
        mainViewModel.equalsAction(view)
    }



}