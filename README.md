# Tip Calculator App

A jetpack compose app that calculates custom tips on different cash amounts

![calc-tip](https://github.com/2Kelvin/tip-calclulator/assets/85868026/e2a6cd90-6617-4a02-8ed8-caa32f9b71a2)


## Future me Notes

- Convert a string to a double & using the elvis operator to return a default double value if conversion returns null
    ```kotlin
    val cashAmount = cashAmountInput.toDoubleOrNull() ?: 0.0
    ```
- make a Column layout (vertically) scrollable
    ```kotlin
    Column(modifier = Modifier.verticalScroll()) {}
    ```
  - A TextField (input field) allows a user to enter their data
     ```kotlin
    TextField(
        // its 'value' property accesses the data the user enters
        // 'onValueChange is a function that works with state to track change in the data the user enters
        // singleLine allows the data if really long not to span to a new line
        singleLine = true,
        // use an icon inside a TexField
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null ) },
        // text directing the user on what data to enter to the TextField
        label = { Text(text = "Enter your name") },
        // use keyboardOptions to customize the phone keyboard based on the data to be entered in the TextField
        keyboardOptions = KeyboardOptions.Default.copy(
                // configuring the phone keyboard to show only numbers
                keyboardType = KeyboardType.Number,
                // have a next phone keyboard button at the end of the keyboard (next action button)
                // done action button closes the phone keyboard
                imeAction = ImeAction.Next
                // have done phone keyboard button at the end of the keyboard (done action button)
                // done action button closes the phone keyboard
                imeAction = ImeAction.Done
        ),
    )
     ```
  - use the Switch composable to make a toggle icon switch
    ```kotlin
    Switch(checked = false, onCheckedChange = { /*lambda function here */ })
    ```
- `kotlin.math.ceil` rounds a number up
- `local testing` tests business logic's (kotlin) functions, classes, methods, variables...
- make a function / class available for local testing by annotating it with `@VisibleForTesting`
- `instrumental testing` tests ui code (jetpack compose & composable functions that translate to UI)
- both local and instrumental testing have their different folder paths where you can add respective testing files