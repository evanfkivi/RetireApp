package com.example.retireapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.NumberFormat

class RetireViewModel : ViewModel() {
    var netWorth by mutableStateOf("")
        private set
    private fun updateNetWorth() {
        netWorth = calculateNetWorth(
            amountInput.toDoubleOrNull() ?: 0.0,
            annualReturnInput.toDoubleOrNull() ?: 0.0,
            currentAgeInput.toDoubleOrNull() ?: 0.0,
            retireAgeInput.toDoubleOrNull() ?: 0.0,
            addlConInput.toDoubleOrNull() ?: 0.0,
            inflChecked
        )
    }

    var amountInput by mutableStateOf("")
        private set
    fun updateAmountInput(input: String) {
        amountInput = input
        updateNetWorth()
    }

    var annualReturnInput by mutableStateOf("")
        private set
    fun updateAnnualReturnInput(input: String) {
        annualReturnInput = input
        updateNetWorth()
    }

    var currentAgeInput by mutableStateOf("")
        private set
    fun updateCurrentAgeInput(input: String) {
        currentAgeInput = input
        updateNetWorth()
    }

    var retireAgeInput by mutableStateOf("")
        private set
    fun updateRetireAgeInput(input: String) {
        retireAgeInput = input
        updateNetWorth()
    }

    var addlConInput by mutableStateOf("")
        private set
    fun updateAddlConInput(input: String) {
        addlConInput = input
        updateNetWorth()
    }

    var inflChecked by mutableStateOf(false)
        private set
    fun updateInflChecked(input: Boolean) {
        inflChecked = input
        updateNetWorth()
    }
}

private fun calculateNetWorth(
    amount: Double,
    annualReturn: Double,
    currentAge: Double,
    retireAge: Double,
    addlCon: Double,
    inflChecked: Boolean,
): String {
    var netWorth = amount
    var time = retireAge - currentAge
    var addlConInfl = addlCon
    val inflation = 0.03

    if (inflChecked) {
        while (time > 0) {
            netWorth = netWorth * (1 + annualReturn/100) + addlConInfl
            addlConInfl = addlConInfl * (1 + inflation)
            time = time - 1
        }
    } else {
        while (time > 0) {
            netWorth = netWorth * (1 + annualReturn/100) + addlCon
            time = time - 1
        }
    }

    return NumberFormat.getCurrencyInstance().format(netWorth)
}
