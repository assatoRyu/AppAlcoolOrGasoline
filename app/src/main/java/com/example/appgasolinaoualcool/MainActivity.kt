package com.example.appgasolinaoualcool

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasoline: TextInputLayout
    private lateinit var editGasoline: TextInputEditText

    private lateinit var btnCalculate: Button
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInterfaceComponents()
        btnCalculate.setOnClickListener {
            calcBetterPrice()
        }
    }

    private fun calcBetterPrice() {
        val priceAlcool = editAlcool.text.toString()
        val priceGasoline = editGasoline.text.toString()

        val validateInputPrices = validatePrices(priceAlcool, priceGasoline)

        if (validateInputPrices) {
            val textBetterGasoline = "A melhor opção para abastecimento é: Gasolina"
            val spanStringGasoline = SpannableString(textBetterGasoline)
            val colorGasoline = getColor(R.color.betterGasoline)

            val startIndexGasoline = textBetterGasoline.indexOf("Gasolina")

            spanStringGasoline.setSpan(
                ForegroundColorSpan(colorGasoline),
                startIndexGasoline,
                startIndexGasoline + "Gasolina".length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE

            )

            val textBetterAlcool = "A melhor opção para abastecimento é: Álcool"
            val spanStringAlcool = SpannableString(textBetterAlcool)
            val colorAlcool = getColor(R.color.betterAlcool)

            val startIndexAlcool = textBetterAlcool.indexOf("Álcool")

            spanStringAlcool.setSpan(
                ForegroundColorSpan(colorAlcool),
                startIndexAlcool,
                startIndexAlcool + "Álcool".length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE

            )



            if ( (priceAlcool.toDouble() / priceGasoline.toDouble()) >= 0.7 ) {
                textResult.setText(spanStringGasoline)
            } else
                textResult.setText(spanStringAlcool)
        }
    }

    private fun validatePrices(pAlcool: String, pGasoline: String): Boolean {

        textInputAlcool.error = null
        textInputGasoline.error = null

        if( pAlcool.isEmpty() ) {
            textInputAlcool.error = "Digite o preço do álcool."
            return false
        } else if ( pGasoline.isEmpty() ) {
            textInputGasoline.error = "Digite o preço da gasolina."
            return false
        }

        return true
    }

    private fun initInterfaceComponents() {
        textInputAlcool = findViewById(R.id.text_input_alcool)
        editAlcool = findViewById(R.id.edit_alcool)

        textInputGasoline = findViewById(R.id.text_input_gasoline)
        editGasoline = findViewById(R.id.edit_gasoline)

        btnCalculate = findViewById(R.id.btn_calculate)

        textResult = findViewById(R.id.text_result)
    }
}