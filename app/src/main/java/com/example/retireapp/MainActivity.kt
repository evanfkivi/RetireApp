package com.example.retireapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.retireapp.ui.theme.RetireAppTheme
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetireAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RetireLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RetireLayout(modifier: Modifier) {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0

    var annualReturnInput by remember { mutableStateOf("") }
    val annualReturn = annualReturnInput.toDoubleOrNull() ?: 0.0

    var currentAgeInput by remember {mutableStateOf("")}
    val currentAge = currentAgeInput.toDoubleOrNull() ?: 0.0

    var retireAgeInput by remember {mutableStateOf("")}
    val retireAge = retireAgeInput.toDoubleOrNull() ?: 0.0

    val tip = calculateNetWorth(
        amount, annualReturn, currentAge, retireAge
    )

    Column(
        modifier = modifier
        .statusBarsPadding()
        .padding(horizontal = 40.dp)
        .verticalScroll(rememberScrollState())
        .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EditNetWorth(
            value = amountInput,
            onValueChange = { amountInput = it },
            modifier = modifier
        )
        EditReturn(
            value = annualReturnInput,
            onValueChange = { annualReturnInput = it },
            modifier = modifier
        )
        EditCurrentAge(
            value = currentAgeInput,
            onValueChange = { currentAgeInput = it },
            modifier = modifier
        )
        EditRetireAge(
            value = retireAgeInput,
            onValueChange = { retireAgeInput = it },
            modifier = modifier
        )
        Text(
            text = "Ending Net Worth: $tip",
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun EditNetWorth(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text("Current Net Worth") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@Composable
fun EditReturn(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text("Annual Return") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@Composable
fun EditCurrentAge(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text("Current Age") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@Composable
fun EditRetireAge(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text("Retirement Age") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

private fun calculateNetWorth(
    amount: Double,
    annualReturn: Double,
    currentAge: Double,
    retireAge: Double
): String {
    var netWorth = amount
    var time = retireAge - currentAge

    while (time > 0) {
        netWorth = netWorth * (1 + annualReturn/100)
        time = time - 1
    }
    return NumberFormat.getCurrencyInstance().format(netWorth)
}
