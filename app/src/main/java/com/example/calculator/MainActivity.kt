package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    private var currentInput = ""
    private var firstNumber = 0.0
    private var operation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
    }

    fun onClick(view: View) {
        val button = view as Button
        val value = button.text.toString()

        when (value) {
            "0","1","2","3","4","5","6","7","8","9" -> appendNumber(value)
            "+","-","*","/" -> setOperation(value)
            "=" -> calculate()
            "C" -> clear()
        }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        tvResult.text = currentInput
    }

    private fun setOperation(op: String) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toDouble()
            operation = op
            currentInput = ""
        }
    }

    private fun calculate() {
        if (currentInput.isEmpty()) return

        val secondNumber = currentInput.toDouble()

        val result = when (operation) {
            "+" -> add(firstNumber, secondNumber)
            "-" -> subtract(firstNumber, secondNumber)
            "*" -> multiply(firstNumber, secondNumber)
            "/" -> divide(firstNumber, secondNumber)
            else -> return
        }

        tvResult.text = result.toString()
        currentInput = result.toString()
    }

    private fun add(a: Double, b: Double): Double {
        return a + b
    }

    private fun subtract(a: Double, b: Double): Double {
        return a - b
    }

    private fun multiply(a: Double, b: Double): Double {
        return a * b
    }

    private fun divide(a: Double, b: Double): Double {
        return a / b
    }
    
    private fun clear() {
        currentInput = ""
        firstNumber = 0.0
        operation = ""
        tvResult.text = ""
    }
}