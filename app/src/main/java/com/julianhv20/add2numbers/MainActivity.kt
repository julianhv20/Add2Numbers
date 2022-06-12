package com.julianhv20.add2numbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.julianhv20.add2numbers.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {

            //Check empty values

            if (binding.etNumber1.text.isEmpty() || binding.etNumber2.text.isEmpty()) {
                binding.tvResult.text = "Please enter a value"
            } else {
                //Convert to int
                val number1 = binding.etNumber1.text.toString().toInt()
                val number2 = binding.etNumber2.text.toString().toInt()

                //Add numbers
                val result = number1 + number2

                //Display result
                binding.tvResult.text = result.toString()

            }


        }

        //Clear inputs

        binding.btnReset.setOnClickListener {
            binding.etNumber1.text.clear()
            binding.etNumber2.text.clear()
            binding.tvResult.text = "0"
        }

    }
}