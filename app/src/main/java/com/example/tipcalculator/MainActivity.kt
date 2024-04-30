package com.example.tipcalculator

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipLayout()
                }
            }
        }
    }
}

@Composable
fun TipLayout() {
    // cash amount state variable
    var cashAmountInput by remember { mutableStateOf("") }
    // tip amount state variable
    var tipAmountInput by remember { mutableStateOf("") }

    // converting tipAmount from string to double
    val tipPercent = tipAmountInput.toDoubleOrNull() ?: 0.0
    // converting the user amount entered from a string to a double
    val cashAmount = cashAmountInput.toDoubleOrNull() ?: 0.0

    // calculating the tip amount, using the amount & custom tip percent if provided
    val tip = calculateTip(cashAmount, tipPercent)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        // cash amount input field
        TheInputField(
            value = cashAmountInput,
            onValueChange = { cashAmountInput = it },
            label = R.string.bill_amount,
            keyboardOptions = KeyboardOptions.Default.copy(
                // configuring the phone keyboard to show only numbers
                keyboardType = KeyboardType.Number,
                // have a next phone keyboard button at the end of the keyboard (next action button)
                // done action button closes the phone keyboard
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        // tip input field
        TheInputField(
            value = tipAmountInput,
            onValueChange = { tipAmountInput = it },
            label = R.string.how_was_the_service,
            keyboardOptions = KeyboardOptions.Default.copy(
                // configuring the phone keyboard to show only numbers
                keyboardType = KeyboardType.Number,
                // have done phone keyboard button at the end of the keyboard (done action button)
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun TheInputField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int, // @StringRes annotates that this is a string resource reference
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    // user text input field
    TextField(
        // the variable passed to mutableStateOf is wrapped in a state object
        // which then make's it observable/ trackable
        // to access the value of the variable in the state, use stateVariableName.value
        // but by using remember, the state object value property's getter & setter functions are delegated to remember class's getter & setter functions
        // this let's you read & set the state object value directly without referring to stateObj.value property
        value = value,
        // as the user types in the textField, so doest the value change
        // triggering a recomposition initiated by this lambda function passed to onValueChange
        // it assigns the new value to the state object value property
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(text = stringResource(id = label)) },
        // make the input scrollable horizontally instead of spanning to anew line
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */
private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        TipLayout()
    }
}