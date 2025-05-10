package com.example.calculadora_cientifica

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView

    private var operand1: Double? = null
    private var pendingOperation: String = ""
    private var isNewOperation = true
    private var memoryValue: Double = 0.0
    private var angleMode: String = "DEG"
    private var isHypActive: Boolean = false

    private val decimalFormat = DecimalFormat().apply {
        maximumFractionDigits = 10
        isGroupingUsed = false
    }
    private val scientificFormat = DecimalFormat("0.#######E0")

    private lateinit var btnDegRad: Button
    private lateinit var btnHyp: Button
    private lateinit var btnShift: Button

    private lateinit var btnMC: Button
    private lateinit var btnMR: Button
    private lateinit var btnMMais: Button
    private lateinit var btnMMenos: Button
    private lateinit var btnMS: Button

    private lateinit var btnSquare: Button
    private lateinit var btnPowerY: Button
    private lateinit var btnSin: Button
    private lateinit var btnCos: Button
    private lateinit var btnTan: Button
    private lateinit var btnSqrt: Button
    private lateinit var btn10PowerX: Button
    private lateinit var btnLog: Button
    private lateinit var btnExp: Button
    private lateinit var btnMod: Button

    private lateinit var btnPi: Button
    private lateinit var btnCE: Button
    private lateinit var btnClear: Button
    private lateinit var btnBackspace: Button
    private lateinit var btnDividir: Button

    private lateinit var btnFactorial: Button
    private lateinit var btnToggleSign: Button

    private lateinit var btnOpenParenthesis: Button
    private lateinit var btnCloseParenthesis: Button

    private lateinit var btn0: Button; private lateinit var btn1: Button; private lateinit var btn2: Button
    private lateinit var btn3: Button; private lateinit var btn4: Button; private lateinit var btn5: Button
    private lateinit var btn6: Button; private lateinit var btn7: Button; private lateinit var btn8: Button
    private lateinit var btn9: Button; private lateinit var btnPonto: Button

    private lateinit var btnMais: Button; private lateinit var btnMenos: Button
    private lateinit var btnMultiplicar: Button; private lateinit var btnIgual: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        display = findViewById(R.id.display)

        initializeViewsAndListeners()
        resetCalculatorState()
    }

    private fun initializeViewsAndListeners() {
        btnDegRad = findViewById(R.id.btnDegRad)
        btnHyp = findViewById(R.id.btnHyp)
        btnShift = findViewById(R.id.btnShift)

        btnMC = findViewById(R.id.btnMC)
        btnMR = findViewById(R.id.btnMR)
        btnMMais = findViewById(R.id.btnMMais)
        btnMMenos = findViewById(R.id.btnMMenos)
        btnMS = findViewById(R.id.btnMS)

        btnSquare = findViewById(R.id.btnSquare)
        btnPowerY = findViewById(R.id.btnPowerY)
        btnSin = findViewById(R.id.btnSin)
        btnCos = findViewById(R.id.btnCos)
        btnTan = findViewById(R.id.btnTan)

        btnSqrt = findViewById(R.id.btnSqrt)
        btn10PowerX = findViewById(R.id.btn10PowerX)
        btnLog = findViewById(R.id.btnLog)
        btnExp = findViewById(R.id.btnExp)
        btnMod = findViewById(R.id.btnMod)

        btnPi = findViewById(R.id.btnPi)
        btnCE = findViewById(R.id.btnCE)
        btnClear = findViewById(R.id.btnClear)
        btnBackspace = findViewById(R.id.btnBackspace)
        btnDividir = findViewById(R.id.btnDividir)

        btnFactorial = findViewById(R.id.btnFactorial)
        btn7 = findViewById(R.id.btn7); btn8 = findViewById(R.id.btn8); btn9 = findViewById(R.id.btn9)
        btnMultiplicar = findViewById(R.id.btnMultiplicar)

        btnToggleSign = findViewById(R.id.btnToggleSign)
        btn4 = findViewById(R.id.btn4); btn5 = findViewById(R.id.btn5); btn6 = findViewById(R.id.btn6)
        btnMenos = findViewById(R.id.btnMenos)

        btnOpenParenthesis = findViewById(R.id.btnOpenParenthesis)
        btn1 = findViewById(R.id.btn1); btn2 = findViewById(R.id.btn2); btn3 = findViewById(R.id.btn3)
        btnMais = findViewById(R.id.btnMais)

        btnCloseParenthesis = findViewById(R.id.btnCloseParenthesis)
        btn0 = findViewById(R.id.btn0); btnPonto = findViewById(R.id.btnPonto)
        btnIgual = findViewById(R.id.btnIgual)

        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        numberButtons.forEach { button ->
            button.setOnClickListener { onNumberClick(button.text.toString()) }
        }
        btnPonto.setOnClickListener { onDecimalPointClick() }

        btnMais.setOnClickListener { onOperationClick("+") }
        btnMenos.setOnClickListener { onOperationClick("-") }
        btnMultiplicar.setOnClickListener { onOperationClick("×") }
        btnDividir.setOnClickListener { onOperationClick("÷") }
        btnIgual.setOnClickListener { onEqualsClick() }
        btnPowerY.setOnClickListener { onOperationClick("^") }
        btnMod.setOnClickListener { onOperationClick("%") }

        btnMC.setOnClickListener { memoryValue = 0.0 }
        btnMR.setOnClickListener {
            updateDisplay(formatDisplayNumber(memoryValue))
            isNewOperation = true
        }
        btnMS.setOnClickListener { memoryValue = getCurrentDisplayValue() }
        btnMMais.setOnClickListener { memoryValue += getCurrentDisplayValue() }
        btnMMenos.setOnClickListener { memoryValue -= getCurrentDisplayValue() }

        btnCE.setOnClickListener {
            updateDisplay("0")
            isNewOperation = true
        }
        btnClear.setOnClickListener { resetCalculatorState() }
        btnBackspace.setOnClickListener { onBackspaceClick() }
        btnToggleSign.setOnClickListener { onToggleSignClick() }
        btnDegRad.setOnClickListener { toggleAngleMode() }
        btnHyp.setOnClickListener { toggleHypMode() }

        btnSquare.setOnClickListener { performUnaryOperation { it * it } }
        btnSqrt.setOnClickListener { performUnaryOperation { sqrt(it) } }
        btnPi.setOnClickListener {
            updateDisplay(formatDisplayNumber(PI))
            isNewOperation = true
        }
        btnLog.setOnClickListener { performUnaryOperation { log10(it) } }
        btn10PowerX.setOnClickListener { performUnaryOperation { 10.0.pow(it) } }
        btnFactorial.setOnClickListener { onFactorialClick() }

        btnSin.setOnClickListener { performTrigonometricOperation(::sin, ::sinh) }
        btnCos.setOnClickListener { performTrigonometricOperation(::cos, ::cosh) }
        btnTan.setOnClickListener { performTrigonometricOperation(::tan, ::tanh) }

        btnOpenParenthesis.setOnClickListener { appendToDisplay("(") }
        btnCloseParenthesis.setOnClickListener { appendToDisplay(")") }

        btnExp.setOnClickListener {
            if (display.text.toString() != "Erro" && !display.text.toString().contains("E", ignoreCase = true)) {
                appendToDisplay("E")
            }
        }
        btnShift.setOnClickListener { }
    }

    private fun getCurrentDisplayValue(): Double {
        return display.text.toString().replace(',', '.').toDoubleOrNull() ?: 0.0
    }

    private fun formatDisplayNumber(number: Double): String {
        if (number.isNaN() || number.isInfinite()) return "Erro"

        val absNumber = abs(number)
        return if (absNumber > 999999999999.0 || (absNumber < 0.000000001 && absNumber != 0.0)) {
            scientificFormat.format(number)
        } else {
            if (number == floor(number) && absNumber < 9999999999999.0) { // Use um limite alto aqui também
                decimalFormat.format(number.toLong())
            } else {
                decimalFormat.format(number)
            }
        }
    }

    private fun updateDisplay(text: String) {
        display.text = text
    }

    private fun resetCalculatorState() {
        operand1 = null
        pendingOperation = ""
        updateDisplay("0")
        isNewOperation = true
    }

    private fun onNumberClick(numberStr: String) {
        if (display.text.toString() == "Erro") {
            resetCalculatorState()
        }
        val currentText = display.text.toString()
        if (isNewOperation) {
            updateDisplay(if (numberStr == ".") "0." else numberStr)
            isNewOperation = false
        } else {
            if (currentText == "0" && numberStr != ".") {
                updateDisplay(numberStr)
            } else if (currentText.length < 15) {
                updateDisplay(currentText + numberStr)
            }
        }
    }

    private fun appendToDisplay(str: String) {
        val currentText = display.text.toString()
        if (currentText == "Erro") {
            if (str == "(") {
                updateDisplay(str)
                isNewOperation = false
            }
            return
        }

        if (isNewOperation && currentText == "0" && (str == "(" || str == ")")) {
            updateDisplay(str)
            isNewOperation = false
        } else if (isNewOperation && (str == "(" || str == ")")) {
            updateDisplay(currentText + str)
            isNewOperation = false
        }
        else if (isNewOperation) {
            updateDisplay(str)
            isNewOperation = false
        } else {
            if (currentText.length < 20) {
                updateDisplay(currentText + str)
            }
        }
    }

    private fun onDecimalPointClick() {
        if (display.text.toString() == "Erro") resetCalculatorState()

        if (isNewOperation) {
            updateDisplay("0.")
            isNewOperation = false
        } else if (!display.text.toString().contains(".")) {
            if (display.text.length < 15) {
                updateDisplay(display.text.toString() + ".")
            }
        }
    }

    private fun onBackspaceClick() {
        val currentText = display.text.toString()
        if (currentText == "Erro" || (isNewOperation && operand1 != null) ) {
            updateDisplay("0")
            isNewOperation = true
            if(currentText == "Erro") resetCalculatorState()
            return
        }
        if (currentText.isNotEmpty() && currentText != "0") {
            val newText = currentText.substring(0, currentText.length - 1)
            if (newText.isEmpty() || newText == "-") {
                updateDisplay("0")
                isNewOperation = true
            } else {
                updateDisplay(newText)
            }
        } else if (currentText == "0"){
        }
    }

    private fun onToggleSignClick() {
        if (display.text.toString() == "Erro") return
        val valueToToggle = getCurrentDisplayValue()

        if (valueToToggle != 0.0 || display.text.toString() == "0" || display.text.toString() == "0.0") {
            val toggledValue = valueToToggle * -1
            updateDisplay(formatDisplayNumber(toggledValue))
            if (isNewOperation && operand1 != null) {
                operand1 = toggledValue
            }
        }
    }

    private fun toggleAngleMode() {
        angleMode = when (angleMode) {
            "DEG" -> "RAD"; "RAD" -> "GRAD"; else -> "DEG"
        }
        btnDegRad.text = angleMode
    }

    private fun toggleHypMode() {
        isHypActive = !isHypActive
        btnHyp.alpha = if (isHypActive) 1.0f else 0.6f
    }

    private fun onFactorialClick() {
        if (display.text.toString() == "Erro") return
        val value = getCurrentDisplayValue()
        if (value >= 0 && value == floor(value) && value <= 20) {
            var result: Long = 1
            try {
                if (value > 0) {
                    for (i in 1..value.toInt()) {
                        result = result.multiplyExactThrows(i.toLong())
                    }
                }
                updateDisplay(result.toString())
            } catch (e: ArithmeticException) {
                updateDisplay("Erro")
            }
            isNewOperation = true
        } else {
            updateDisplay("Erro")
        }
    }

    private fun onOperationClick(operationSymbol: String) {
        if (display.text.toString() == "Erro") return

        val currentValue = getCurrentDisplayValue()

        if (!isNewOperation && operand1 != null && pendingOperation.isNotEmpty()) {
            performCalculation(currentValue)
            operand1 = getCurrentDisplayValue()
        } else if (isNewOperation && operand1 != null && pendingOperation.isNotEmpty() && pendingOperation != operationSymbol) {
        }
        else {
            operand1 = currentValue
        }
        pendingOperation = operationSymbol
        isNewOperation = true
    }

    private fun onEqualsClick() {
        if (display.text.toString() == "Erro" || operand1 == null || pendingOperation.isEmpty()) {
            isNewOperation = true
            return
        }

        val currentValue = getCurrentDisplayValue()
        performCalculation(currentValue)
        isNewOperation = true
    }

    private fun performCalculation(operand2: Double) {
        if (operand1 == null || pendingOperation.isEmpty()) {
            updateDisplay(formatDisplayNumber(operand2))
            operand1 = operand2
            isNewOperation = true
            return
        }

        var result = 0.0
        val op1 = operand1!!

        try {
            result = when (pendingOperation) {
                "+" -> op1 + operand2
                "-" -> op1 - operand2
                "×" -> op1 * operand2
                "÷" -> {
                    if (operand2 == 0.0) throw ArithmeticException("Divisão por zero!")
                    op1 / operand2
                }
                "^" -> op1.pow(operand2)
                "%" -> {
                    if (operand2 == 0.0) throw ArithmeticException("Módulo por zero!")
                    op1.rem(operand2)
                }
                else -> operand2
            }
            updateDisplay(formatDisplayNumber(result))
            operand1 = result
        } catch (e: ArithmeticException) {
            updateDisplay("Erro: ${e.message}")
            operand1 = null
            pendingOperation = ""
        } catch (e: Exception) {
            updateDisplay("Erro Inesperado")
            operand1 = null
            pendingOperation = ""
        }
        isNewOperation = true
    }

    private fun performUnaryOperation(unaryOp: (Double) -> Double) {
        if (display.text.toString() == "Erro") return
        val value = getCurrentDisplayValue()
        try {
            val result = unaryOp(value)
            updateDisplay(formatDisplayNumber(result))
            operand1 = result
            pendingOperation = ""
            isNewOperation = true
        } catch (e: Exception) {
            updateDisplay("Erro")
        }
    }

    private fun performTrigonometricOperation(
        standardOp: (Double) -> Double,
        hyperbolicOp: (Double) -> Double
    ) {
        if (display.text.toString() == "Erro") return
        val value = getCurrentDisplayValue()
        try {
            val result: Double = if (isHypActive) {
                hyperbolicOp(value)
            } else {
                var angleInRadians = value
                if (angleMode == "DEG") {
                    angleInRadians = Math.toRadians(value)
                } else if (angleMode == "GRAD") {
                    angleInRadians = value * PI / 200.0
                }
                standardOp(angleInRadians)
            }
            updateDisplay(formatDisplayNumber(result))
            operand1 = result
            pendingOperation = ""
            isNewOperation = true
        } catch (e: Exception) {
            updateDisplay("Erro")
        }
    }
}

fun Long.multiplyExactThrows(other: Long): Long {
    val result = this * other
    if (this != 0L && other != 0L) {
        if (this == Long.MIN_VALUE && other == -1L || other == Long.MIN_VALUE && this == -1L) {
            throw ArithmeticException("Overflow no fatorial (Long.MIN_VALUE)")
        }
        if (result / other != this) {
            throw ArithmeticException("Overflow no fatorial")
        }
    }
    return result
}