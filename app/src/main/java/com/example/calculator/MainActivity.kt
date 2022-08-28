package com.example.calculator



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Numbers
        btnZero.setOnClickListener { saveExpressions("0", true) }
        btnOne.setOnClickListener { saveExpressions("1", true) }
        btnTwo.setOnClickListener { saveExpressions("2", true) }
        btnThree.setOnClickListener { saveExpressions("3", true) }
        btnFour.setOnClickListener { saveExpressions("4", true) }
        btnFive.setOnClickListener { saveExpressions("5", true) }
        btnSix.setOnClickListener { saveExpressions("6", true) }
        btnSeven.setOnClickListener { saveExpressions("7", true) }
        btnEight.setOnClickListener { saveExpressions("8", true) }
        btnNine.setOnClickListener { saveExpressions("9", true) }

        // Operators
        operatorPoint.setOnClickListener { saveExpressions(".",false) }
        operatorDivide.setOnClickListener { saveExpressions("/", false) }
        operatorMultiply.setOnClickListener { saveExpressions("*", false) }
        operatorAdd.setOnClickListener { saveExpressions("+", false) }
        operatorSubstract.setOnClickListener { saveExpressions("-", false) }

        // Actions

        // Del all
        actionClear.setOnClickListener {
            outText.text = ""
            inText.text = ""
        }
        // del part
        actionDel.setOnClickListener {
            val string = inText.text.toString()
            if (string.isNotEmpty()) {
                inText.text = string.substring(0,string.length-1)
            }
            outText.text = ""
        }
        actionEqual.setOnClickListener {
            try {
                val out = ExpressionBuilder(inText.text.toString()).build()
                val result = out.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble())
                    outText.text = longResult.toString()
                else
                    outText.text = result.toString()
            }
            catch (e: Exception) {}
        }
    }





    fun saveExpressions (string: String, canClear : Boolean) {
        if (outText.text.isNotEmpty()) {
            inText.text = ""
        }
        if (canClear) {
            outText.text = ""
            inText.append(string)
        } else {
            inText.append(outText.text)
            inText.append(string)
            outText.text = ""
        }
    }
}
