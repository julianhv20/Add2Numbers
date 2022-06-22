package com.julianhv20.add2numbers.ui.main

import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var canAddOperation = false
    private var canAddDecimal = true

    private val _result : MutableLiveData<String> = MutableLiveData()
    val result : LiveData<String> = _result

    private val _workings : MutableLiveData<String> = MutableLiveData("")
    val workings : LiveData<String> = _workings

    fun operationAction(view: View) {
        if (view is Button && canAddOperation){
            _workings.value += view.text.toString()
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun numberAction(view: View) {
        if (view is Button){

            if (view.text == "."){
                if (canAddDecimal) _workings.value += view.text.toString()
                canAddDecimal = false

            } else _workings.value += view.text.toString()

            canAddOperation = true
        }
    }

    fun allClearAction() {
        //Clear textview
        _workings.value = ""
        _result.value = ""
    }

    fun backSpaceAction(view: View) {
        //Remove the last character

        val input = _workings.value


        if (input != null) {

            if (input.isNotEmpty()){
                val inputLength = input.length
                _workings.value = input.subSequence(0, inputLength - 1).toString()
            }
        }
    }

    fun equalsAction(view: View) {
        _result.value = calculateResults()
    }

    private fun calculateResults(): String? {

        val digitsOperators = digitsOperators()

        if (digitsOperators.isEmpty()) return ""

        val timesDivision = timesDivisionCalculate(digitsOperators)
        if (timesDivision.isEmpty()) return ""

        val result = addSubtractCalculate(timesDivision)

        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float {

        var result = passedList[0] as Float

        for (i in passedList.indices){
            if (passedList[i] is Char && i != passedList.lastIndex){
                val operator = passedList[i]
                val nextDigit = passedList[i+1] as Float

                if (operator == '+') result += nextDigit
                if (operator == '-') result -= nextDigit
            }
        }


        return result

    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any> {
        var newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for (i in passedList.indices){
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex){
                val operator = passedList[i]
                val prevDigit = passedList[i-1] as Float
                val nextDigit = passedList[i+1] as Float

                when (operator) {
                    'x' -> {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }

                    '/' -> {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i +1
                    }
                    else -> {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if (i > restartIndex){
                newList.add(passedList[i])
            }

        }

        return newList
    }

    private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {

        var list = passedList

        while (list.contains('x') || list.contains('/')){
            list = calcTimesDiv(list)
        }

        return list

    }

    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""

        val workings = _workings.value

        if (workings != null) {
            for (character in workings){
                if (character.isDigit() || character == '.'){
                    currentDigit += character
                } else {
                    list.add(currentDigit.toFloat())
                    currentDigit = ""
                    list.add(character)
                }
            }
        }

        if (currentDigit !== ""){
            list.add(currentDigit.toFloat())
        }


        return list
    }


}