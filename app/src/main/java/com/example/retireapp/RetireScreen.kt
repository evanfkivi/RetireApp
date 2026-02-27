package com.example.retireapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RetireScreen(
    modifier: Modifier,
    viewModel: RetireViewModel = viewModel(),
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EditNetWorth(
            value = viewModel.amountInput,
            onValueChange = { viewModel.updateAmountInput(it) },
            modifier = modifier
        )
        EditAddlCon(
            value = viewModel.addlConInput,
            onValueChange = { viewModel.updateAddlConInput(it) },
            modifier = modifier
            )
        InflationCheckbox(
            value = viewModel.inflChecked,
            onValueChange = { viewModel.updateInflChecked(it) },
            modifier = modifier
            )
        EditReturn(
            value = viewModel.annualReturnInput,
            onValueChange = { viewModel.updateAnnualReturnInput(it) },
            modifier = modifier
        )
        EditCurrentAge(
            value = viewModel.currentAgeInput,
            onValueChange = { viewModel.updateCurrentAgeInput(it) },
            modifier = modifier
        )
        EditRetireAge(
            value = viewModel.retireAgeInput,
            onValueChange = { viewModel.updateRetireAgeInput(it) },
            modifier = modifier
        )
        Text(
            text = "Ending Net Worth: ${viewModel.netWorth}",
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
        label = { Text("Current Net Worth ($)") },
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
        label = { Text("Annual Return (%)") },
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
        label = { Text("Current Age (years)") },
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
        label = { Text("Retirement Age (years)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@Composable
fun EditAddlCon(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text("Additional Annual Contribution ($)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@Composable
fun InflationCheckbox(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            "Adjust contribution for inflation?"
        )
        Checkbox(
            checked = value,
            onCheckedChange = onValueChange
        )
    }
}

