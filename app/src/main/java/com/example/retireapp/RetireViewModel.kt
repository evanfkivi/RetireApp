package com.example.retireapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RetireViewModel : ViewModel() {
    var amountInput by mutableStateOf("")
    fun updateAmountInput(input: String) {
        amountInput = input
    }

    var annualReturnInput by mutableStateOf("")
    fun updateAnnualReturnInput(input: String) {
        annualReturnInput = input
    }

    var currentAgeInput by mutableStateOf("")
    fun updateCurrentAgeInput(input: String) {
        currentAgeInput = input
    }

    var retireAgeInput by mutableStateOf("")
    fun updateRetireAgeInput(input: String) {
        retireAgeInput = input
    }

    var addlConInput by mutableStateOf("")
    fun updateAddlConInput(input: String) {
        addlConInput = input
    }

    var inflChecked by mutableStateOf(false)
    fun updateInflChecked(input: Boolean) {
        inflChecked = input
    }

}