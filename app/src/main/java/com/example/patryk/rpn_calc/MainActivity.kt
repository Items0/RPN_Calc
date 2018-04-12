package com.example.patryk.rpn_calc

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Stack

class MainActivity : AppCompatActivity() {
    var stack = Stack<Float>()
    var stackCopy = Stack<Float>()
    val REQUEST_CODE = 10000
    var numberOfDigits = 1
    var buttonsColor = "Original"
    var textViewColor = "Original"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addValues(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (stack.count() > 1)
        {
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b + a)
            updateValues()
        }
    }

    fun subtractValues(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (stack.count() > 1)
        {
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b - a)
            updateValues()
        }
    }

    fun multiplyValues(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (stack.count() > 1)
        {
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b * a)
            updateValues()
        }
    }

    fun divideValues(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (stack.count() > 1)
        {
            val a = stack.pop()
            val b = stack.pop()
            if (a != 0.toFloat()) {
                stack.push(b / a)
                updateValues()
            }
            else {
                statusText.text = "Error"
            }
        }
    }

    fun sqrtValue(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (stack.count() > 0)
        {
            var a = stack.pop()
            if (a >= 0)
            {
                a = Math.sqrt(a.toDouble()).toFloat()
                stack.push(a)
                updateValues()
            }
            else {
                statusText.text = "Error"
            }
        }
    }

    fun powValues(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (stack.count() > 1) {
            val x= stack.pop()
            val y = stack.pop()
            stack.push(Math.pow(x.toDouble(), y.toDouble()).toFloat())
            updateValues()
        }
    }

    fun insertValue(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        val b = v as Button
        val sb = StringBuilder()
        sb.append(statusText.text).append(b.text)
        statusText.text = sb.toString()
    }

    fun enterValue(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (statusText.text != "") {
            val myValue = (statusText.text).toString().toFloat()
            stack.push(myValue)
        }
        else {
            if (stack.count() > 0) {
                val myValue = stack.get(stack.count() - 1)
                stack.push(myValue)
            }
        }
        statusText.text = ""
        updateValues()
    }

    fun dropValue(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (stack.count() > 0) {
            stack.pop()
        }
        updateValues()
    }

    fun allClear(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        stack.clear()
        statusText.text = ""
        updateValues()
    }

    fun undoStep(v: View) {
        stack= stackCopy.clone() as Stack<Float>
        updateValues()
    }

    fun swapValues(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (stack.count() > 1) {
            val a = stack.pop()
            val b = stack.pop()
            stack.push(a)
            stack.push(b)
            updateValues()
        }
    }

    fun changeSign(v: View) {
        stackCopy = stack.clone() as Stack<Float>
        if (stack.count() > 0) {
            val a = stack.pop()
            stack.push(-a)
            updateValues()
        }
    }
    fun updateValues()
    {
        var myFormat = "%." + numberOfDigits.toString() + "f"
        if (stack.getOrNull(stack.count() - 1) != null) {
            statusText2.text = "1: " + myFormat.format(stack.getOrNull(stack.count() - 1))
        }
        else {
            statusText2.text = "1: "
        }

        if (stack.getOrNull(stack.count() - 2) != null) {
            statusText3.text = "2: " + myFormat.format(stack.getOrNull(stack.count() - 2))
        }
        else {
            statusText3.text = "2: "
        }

        if (stack.getOrNull(stack.count() - 3) != null) {
            statusText4.text = "3: " + myFormat.format(stack.getOrNull(stack.count() - 3))
        }
        else {
            statusText4.text = "3: "
        }

        if (stack.getOrNull(stack.count() - 4) != null) {
            statusText5.text = "4: " + myFormat.format(stack.getOrNull(stack.count() - 4))
        }
        else {
            statusText5.text = "4: "
        }
        //statusText2.text = "1: " + (myFormat.format(stack.getOrNull(stack.count() - 1))?.toString() ?: "")
        //statusText3.text = "2: " + (myFormat.format(stack.getOrNull(stack.count() - 2).?.toString() ?: "")
        //statusText4.text = "3: " + myFormat.format(stack.getOrNull(stack.count() - 3)?.toString() ?: "")
        //statusText5.text = "4: " + myFormat.format(stack.getOrNull(stack.count() - 4)?.toString() ?: "")
    }

    fun updateButtonsColor(myString : String) {
        buttonsColor = myString
        var myColor : Int = 0
        // "White", "Blue", "Green", "Red", "Yellow"
        when(myString) {
            "Original" -> myColor = android.graphics.Color.parseColor("#D8D8D8")
            "White" -> myColor = android.graphics.Color.WHITE
            "Blue" -> myColor = android.graphics.Color.BLUE
            "Green" -> myColor = android.graphics.Color.GREEN
            "Red" -> myColor = android.graphics.Color.RED
            "Yellow" -> myColor = android.graphics.Color.YELLOW
        }
        button0.setBackgroundColor(myColor)
        button1.setBackgroundColor(myColor)
        button2.setBackgroundColor(myColor)
        button3.setBackgroundColor(myColor)
        button4.setBackgroundColor(myColor)
        button5.setBackgroundColor(myColor)
        button6.setBackgroundColor(myColor)
        button7.setBackgroundColor(myColor)
        button8.setBackgroundColor(myColor)
        button9.setBackgroundColor(myColor)
        buttonAdd.setBackgroundColor(myColor)
        buttonSubtract.setBackgroundColor(myColor)
        buttonMultiply.setBackgroundColor(myColor)
        buttonDivide.setBackgroundColor(myColor)
        buttonDot.setBackgroundColor(myColor)
        buttonSettings.setBackgroundColor(myColor)
        buttonSqrt.setBackgroundColor(myColor)
        buttonPow.setBackgroundColor(myColor)
        buttonPlusMinus.setBackgroundColor(myColor)
        buttonAC.setBackgroundColor(myColor)
        buttonSwap.setBackgroundColor(myColor)
        buttonDrop.setBackgroundColor(myColor)
        buttonUndo.setBackgroundColor(myColor)
        buttonEnter.setBackgroundColor(myColor)
    }

    fun updateTextViewColor(myString: String) {
        textViewColor = myString
        var myColor  = 0
        when(myString) {
            "Original" -> myColor = android.graphics.Color.parseColor("#FAFAFA")
            "White" -> myColor = android.graphics.Color.WHITE
            "Blue" -> myColor = android.graphics.Color.BLUE
            "Green" -> myColor = android.graphics.Color.GREEN
            "Red" -> myColor = android.graphics.Color.RED
            "Yellow" -> myColor = android.graphics.Color.YELLOW
        }
        statusText.setBackgroundColor(myColor)
        statusText2.setBackgroundColor(myColor)
        statusText3.setBackgroundColor(myColor)
        statusText4.setBackgroundColor(myColor)
        statusText5.setBackgroundColor(myColor)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable("myStack", stack)
        outState?.putSerializable("myStackCopy", stackCopy)
        outState?.putString("myStatusText", statusText.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        stack = savedInstanceState?.getSerializable("myStack") as Stack<Float>
        stackCopy = savedInstanceState?.getSerializable("myStackCopy") as Stack<Float>
        statusText.text = savedInstanceState?.getString("myStatusText")
        updateValues()
    }
    fun settingsActivity(v: View) {
        val i = Intent(this, secondActivity::class.java)
        i.putExtra("numberOfDigits", numberOfDigits.toString())
        i.putExtra("buttonsColor", buttonsColor)
        i.putExtra("textViewColor", textViewColor)
        startActivityForResult(i, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ((REQUEST_CODE == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
            if (data != null) {
                if (data.hasExtra("numberOfDigits"))
                {
                    numberOfDigits = data.extras.getString("numberOfDigits").toInt()
                    updateValues()
                }
                if (data.hasExtra("buttonsColor"))
                {
                   buttonsColor = data.extras.getString("buttonsColor")
                    updateButtonsColor(buttonsColor)
                }
                if (data.hasExtra("textViewColor"))
                {
                   textViewColor = data.extras.getString("textViewColor")
                    updateTextViewColor(textViewColor)
                }
            }
        }
    }
}

