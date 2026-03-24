package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView

    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var operation = ""

    private var isNewInput = true
    private var display = ""

    private var lastInputWasOperation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)
    }

    fun onClick(view: View) {
        val value = (view as Button).text.toString()

        when (value) {
            "0","1","2","3","4","5","6","7","8","9" -> appendNumber(value)
            "+","-","*","/" -> setOperation(value)
            "=" -> calculate()
            "C" -> clear()
        }
    }

    private fun appendNumber(num: String) {
        display += num
        tvDisplay.text = display

        lastInputWasOperation = false
    }

    private fun setOperation(op: String) {
        if (display.isEmpty()) return

        if (lastInputWasOperation) {
            display = display.dropLast(3)
        }

        display += " $op "
        operation = op

        lastInputWasOperation = true
        isNewInput = true

        tvDisplay.text = display
    }

    private fun calculate() {
        val parts = display.trim().split(" ")

        if (parts.size < 3) return

        firstNumber = parts[0].toDouble()
        operation = parts[1]
        secondNumber = parts[2].toDouble()

        val result = when (operation) {
            "+" -> add(firstNumber, secondNumber)
            "-" -> subtract(firstNumber, secondNumber)
            "*" -> multiply(firstNumber, secondNumber)
            "/" -> divide(firstNumber, secondNumber)
            else -> return
        }

        if (result.isNaN()) {
            display = ""
            return
        }

        display = format(result)
        tvDisplay.text = display

        firstNumber = result
        lastInputWasOperation = false
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
        if (b == 0.0) {
            tvDisplay.text = "Error"
            display = ""
            return Double.NaN
        }
        return a / b
    }

    private fun clear() {
        display = ""
        firstNumber = 0.0
        secondNumber = 0.0
        operation = ""
        isNewInput = true
        lastInputWasOperation = false
        tvDisplay.text = ""
    }

    private fun format(value: Double): String {
        return if (value % 1 == 0.0) value.toInt().toString()
        else value.toString()
    }
}