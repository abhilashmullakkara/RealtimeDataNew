package com.abhilash.livedata.ui.theme.utility

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CurrencyCountScreen(navController: NavController){
    Surface(color = Color.White) {
        var grandTotal by rememberSaveable { mutableStateOf(0) }
        var reqiredAmont by rememberSaveable { mutableStateOf(0) }
        Column {
            Surface(color = Color(0xFF484F7B)) {


                IconButton(onClick = {
                    navController.popBackStack()
                }, modifier = Modifier.fillMaxWidth())
                {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Arrow",
                        tint = Color.White
                    )
                }
                Text(
                    "Currency Count",
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            Text("You can enter corresponding number of currency", color = Color.Gray)
            val scroll = rememberScrollState()
            Column(modifier = Modifier.verticalScroll(scroll)) {


            Spacer(modifier = Modifier.height(10.dp))
            grandTotal = currencyRead(currencyType = 10)
            Spacer(modifier = Modifier.height(10.dp))
            grandTotal += currencyRead(currencyType = 20)
            Spacer(modifier = Modifier.height(10.dp))
            grandTotal += currencyRead(currencyType = 50)
            Spacer(modifier = Modifier.height(10.dp))
            grandTotal += currencyRead(currencyType = 100)
            Spacer(modifier = Modifier.height(10.dp))
            grandTotal += currencyRead(currencyType = 200)
            Spacer(modifier = Modifier.height(10.dp))
            grandTotal += currencyRead(currencyType = 500)


            //Total
            Text(
                "Total: $grandTotal",
                fontSize = 19.sp,
                color = Color.Red, modifier = Modifier.padding(start = 50.dp)
            )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Enter required Amount:")
                Spacer(modifier = Modifier.height(10.dp))
                reqiredAmont=requiredAmt(rAmount = grandTotal)
                if (reqiredAmont==0){
                    Text(text = "You need nothing more:$reqiredAmont")
                }
                else if(reqiredAmont<0)
                {
                    Text(text = "Amount of ₹:${reqiredAmont * -1} require more")

                }
                else {
                    Text("Excess Amount of ₹: $reqiredAmont")
                }


        }

        }
    }
}
@Composable
fun currencyRead(currencyType:Int): Int {
    var currency by rememberSaveable { mutableStateOf("") }
    Row(modifier = Modifier.padding(start=15.dp)) {

        Text("$currencyType x")
        OutlinedTextField (
            value = currency,
            singleLine = true,
            //shape = RoundedCornerShape(80),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = { it ->
                val filteredInput = it.filter { it.isDigit() || it == '.' }
                // Make sure there's no more than one dot in the input
                currency = filteredInput.replace("..", ".")
            },
            modifier = Modifier
                .fillMaxWidth(0.44f)
                .padding(start = 10.dp),
            placeholder = {
                Text(
                    text = "Enter number",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            },
        )

    }

    return if (currency.isNotBlank() && currency != ".") {
        try {
            // Parse the input currency string to a Double value
            val currencyValue = currency.toDouble()
            // Perform the multiplication and return the result as an integer
            (currencyValue * currencyType).toInt()
        } catch (e: NumberFormatException) {
            // Handle invalid input (e.g., non-numeric characters, multiple dots)
            0
        }
    } else {
        0
    }

}
@Composable
fun requiredAmt(rAmount:Int):Int{
    var reqAmount by rememberSaveable { mutableStateOf("") }
    OutlinedTextField (
        value = reqAmount,
        singleLine = true,
        //shape = RoundedCornerShape(80),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        onValueChange = { it ->
            val filteredInput = it.filter { it.isDigit() || it == '.' }
            // Make sure there's no more than one dot in the input
            reqAmount = filteredInput.replace("..", ".")
        },
        modifier = Modifier
            .fillMaxWidth(0.44f)
            .padding(start = 10.dp),
        placeholder = {
            Text(
                text = "Enter required Amount:",
                color = Color.Black,
                fontSize = 14.sp
            )
        },
    )
    return if (reqAmount.isNotBlank() && reqAmount != ".") {
        try {
            // Parse the input currency string to a Double value
            val currencyValue = reqAmount.toDouble()
            // Perform the multiplication and return the result as an integer
            currencyValue .toInt()
            (currencyValue - rAmount).toInt()
        } catch (e: NumberFormatException) {
            // Handle invalid input (e.g., non-numeric characters, multiple dots)
            0
        }
    } else {
        0
    }

}