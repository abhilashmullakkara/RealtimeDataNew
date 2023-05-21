package com.abhilash.livedata.ui.theme.read

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.google.firebase.database.FirebaseDatabase

@Composable
fun ReadScreen(navController: NavController) {
    var etm by rememberSaveable { mutableStateOf("") }
    var busType by rememberSaveable { mutableStateOf("") }
    var kilometer by rememberSaveable { mutableStateOf("") }
    var via by rememberSaveable { mutableStateOf("") }
    var destination by rememberSaveable { mutableStateOf("") }
    var depoNo by rememberSaveable { mutableStateOf("") }
    var scheduleNo by rememberSaveable { mutableStateOf("") }
    var tripNo by rememberSaveable { mutableStateOf("") }
    var departureTime by rememberSaveable { mutableStateOf("") }
    var stPlace by rememberSaveable { mutableStateOf("") }
    var arrivalTime by rememberSaveable { mutableStateOf("") }
    val context= LocalContext.current
    Surface(color = Color(0xFFC2D6F7)) {
      Column(
       modifier = Modifier.fillMaxWidth(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Top
        )
      {
       Text(
        "Enter Schedule Information",
        fontSize = 19.sp,
        color = Color.Red,
        fontWeight = FontWeight.SemiBold,
         )
          IconButton(onClick = {
          navController.popBackStack()
            })
          {
          Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
           }
            Divider(color = Color.White, thickness = 3.dp)
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                OutlinedTextField(
                    value = depoNo,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { newValue ->
                        val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                        if (isValidText(textFieldValue)) {
                            depoNo = textFieldValue.text
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.23f),
                    placeholder = {
                        Text(
                            text = "Depo NO:",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
                OutlinedTextField(
                    value = scheduleNo,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                    onValueChange = { newValue ->
                        val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                        if (isValidText(textFieldValue)) {
                            scheduleNo = textFieldValue.text
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.37f),
                    placeholder = {
                        Text(
                            text = "Schedule NO:",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
                OutlinedTextField(
                    value = busType,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    //keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters
                ),
                    onValueChange = { newValue ->
                        val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                        if (isValidText(textFieldValue)) {
                            busType = textFieldValue.text
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Type(FP,Ord,JNT):",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Enter each trip of a schedule and press INSERT button below(Scroll down). After completing the schedule , change schedule number you want to save further...(need not change depo number or schedule every time when entering trip)",
                textAlign = TextAlign.Start, modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 3.dp,
                contentColor = Color.Black,
                backgroundColor = Color.White
            ) {
                val scrollState = rememberScrollState()
                Box(modifier = Modifier.verticalScroll(scrollState)) {


                    Column {


                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = tripNo,
                            singleLine = true,
                            shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { tripNo = it },
                            //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                            placeholder = {
                                Text(
                                    text = "Enter Trip Number (eg:- 1)",
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = departureTime,
                            singleLine = true,
                            shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { departureTime = it },
                            placeholder = {
                                Text(
                                    text = "Enter Departure Time (eg:- 06.00)",
                                    color = Color.Black,
                                    fontSize = 14.sp
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = stPlace,
                            singleLine = true,
                            shape = RoundedCornerShape(80),
                            // keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Characters
                            ),
                            onValueChange = { stPlace = it },
                            //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                            placeholder = {
                                Text(
                                    text = "Enter Starting Place (eg:- KMR )",
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }
                        )
                        //
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = via,
                            singleLine = true,
                            shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Characters
                            ),
                            onValueChange = { via = it },
                            placeholder = {
                                Text(
                                    text = "Enter via (eg:- KTR )",
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = destination,
                            singleLine = true,
                            shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Characters
                            ),
                            onValueChange = { destination = it },
                            placeholder = {
                                Text(
                                    text = "Enter Destination Place (eg:- KTM )",
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = arrivalTime,
                            singleLine = true,
                            shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { arrivalTime = it },
                            placeholder = {
                                Text(
                                    text = "Enter Arrival Time (Eg:-18.00)",
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = kilometer,
                            singleLine = true,
                            shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { kilometer = it },
                            placeholder = {
                                Text(
                                    text = "Enter Trip Kilometer :",
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = etm,
                            singleLine = true,
                            shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { etm = it },
                            placeholder = {
                                Text(
                                    text = "Etm root No(optional)",
                                    color = Color.Black,
                                    fontSize = 14.sp
                                )
                            }
                        )

                        val originalDatabase = OriginalData(
                            startPlace = stPlace,
                            via = via,
                            destinationPlace = destination,
                            departureTime = departureTime,
                            arrivalTime = arrivalTime,
                            kilometer = kilometer,
                            bustype = busType,
                            etmNo = etm
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        TextButton(
                            onClick = {
                                if (depoNo.isNotBlank() && scheduleNo.isNotBlank() &&
                                    tripNo.isNotBlank() && stPlace.isNotBlank() &&
                                    departureTime.isNotBlank() &&
                                    destination.isNotBlank() &&
                                    arrivalTime.isNotBlank() &&
                                    kilometer.isNotBlank() &&
                                    busType.isNotBlank()
                                ) {
                                    val dataBase = FirebaseDatabase.getInstance()
                                    val myRef = dataBase.getReference(depoNo)
                                    myRef.child(busType).child(scheduleNo).child(tripNo)
                                        .setValue(originalDatabase).addOnSuccessListener {
                                        tripNo = ""
                                        stPlace = ""
                                        departureTime = ""
                                        via = ""
                                        destination = ""
                                        arrivalTime = ""
                                        kilometer = ""
                                        etm = ""
                                        Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT)
                                            .show()
                                    }.addOnFailureListener {
                                        Toast.makeText(context, it.toString(), Toast.LENGTH_LONG)
                                            .show()
                                    }

                                } else {
                                    Toast.makeText(context, "field empty", Toast.LENGTH_LONG).show()
                                }

                            },
                            modifier = Modifier
                                .fillMaxSize(0.5f)
                                .padding(start = 50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.LightGray,
                                contentColor = Color.Blue

                            )
                        ) {
                            Text(
                                text = "INSERT",
                                fontSize = 18.sp,
                                color = Color.Red
                            )


                        }
                    }
                }
            }
        }
    }
}



fun isValidText(text: TextFieldValue): Boolean {
    val allowedChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return text.text.all { allowedChars.contains(it) }
}
