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
        EditTextField(
            value = viewModel.amountInput,
            onValueChange = { viewModel.updateAmountInput(it) },
            text = "Current Net Worth ($)",
            modifier = modifier
        )
        EditTextField(
            value = viewModel.addlConInput,
            onValueChange = { viewModel.updateAddlConInput(it) },
            text = "Additional Annual Contribution ($)",
            modifier = modifier
            )
        InflationCheckbox(
            value = viewModel.inflChecked,
            onValueChange = { viewModel.updateInflChecked(it) },
            modifier = modifier
            )
        EditTextField(
            value = viewModel.annualReturnInput,
            onValueChange = { viewModel.updateAnnualReturnInput(it) },
            text = "Annual Return (%)",
            modifier = modifier
        )
        EditTextField(
            value = viewModel.currentAgeInput,
            onValueChange = { viewModel.updateCurrentAgeInput(it) },
            text = "Current Age (years)",
            modifier = modifier
        )
        EditTextField(
            value = viewModel.retireAgeInput,
            onValueChange = { viewModel.updateRetireAgeInput(it) },
            text = "Retirement Age (years)",
            modifier = modifier
        )
        Text(
            text = "Ending Net Worth: ${viewModel.netWorth}",
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun EditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(text) },
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

