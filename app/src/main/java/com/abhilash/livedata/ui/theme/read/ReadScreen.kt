package com.abhilash.livedata.ui.theme.read


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.R
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.google.firebase.database.FirebaseDatabase

@Composable
fun ReadScreen(navController: NavController) {
    var busType by rememberSaveable { mutableStateOf("") }
    var depoNo by rememberSaveable { mutableStateOf("") }
    var scheduleNo by rememberSaveable { mutableStateOf("") }
    Surface(color = Color(0xFF586477), contentColor = Color.Black) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                })
                {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
                }
                Text(
                    "Enter Schedule Information...   ",
                    fontSize = 19.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            //Rest of heading
            Divider(color = Color.White, thickness = 3.dp)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.weight(.25f)) {
                    OutlinedTextField(
                        value = depoNo,
                        singleLine = true,
                        colors=TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Black),
                        // shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { newValue ->
                            val textFieldValue =
                                TextFieldValue(newValue, TextRange(newValue.length))
                            if (isValidText(textFieldValue)) {
                                depoNo = textFieldValue.text
                            }
                        },
                        //modifier = Modifier.fillMaxWidth(0.23f),
                        placeholder = {
                            Text(
                                text = "Enter Depo NO:",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
                Box(modifier = Modifier.weight(0.25f)) {
                    OutlinedTextField(
                        value = scheduleNo,
                        singleLine = true,
                        colors=TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Black),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                        onValueChange = { newValue ->
                            val textFieldValue =
                                TextFieldValue(newValue, TextRange(newValue.length))
                            if (isValidText(textFieldValue)) {
                                scheduleNo = textFieldValue.text
                            }
                        },
                        placeholder = {
                            Text(
                                text = "Enter Schedule NO:",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
                Box(modifier = Modifier.weight(0.25f)) {
                    OutlinedTextField(
                        value = busType,
                        singleLine = true,
                        colors=TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Black),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters
                        ),
                        onValueChange = { newValue ->
                            val textFieldValue =
                                TextFieldValue(newValue, TextRange(newValue.length))
                            if (isValidText(textFieldValue)) {
                                busType = textFieldValue.text
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "Enter (FP,Ord,JNT)",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }
            //Enter Schedule Information
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Enter each trip of a schedule and press INSERT button below(Scroll down). After completing the schedule , change schedule number you want to save further...(need not change depo number or schedule every time when entering trip)",
                textAlign = TextAlign.Start, modifier = Modifier.padding(10.dp),
                color = Color.White
            )
            Surface(color = Color(0xFF586477), contentColor = Color.Black) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 3.dp,
                contentColor = Color.Black,
                backgroundColor = Color.White
            ) {

                Spacer(modifier = Modifier.height(10.dp))
                val scrollState = rememberScrollState()
                Surface(color = Color(0xFF568058), contentColor = Color.White) {
                Box(modifier = Modifier.verticalScroll(scrollState)) {

                    Column {
                            Spacer(modifier = Modifier.height(7.dp))
                        Text(
                            "TripNo  Departure Time From  Via  To ArrivalTime Kilometer ETM_Root_No",
                            color = Color.White
                        )
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.yellow_arrow),
                                contentDescription = "Image with Text",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )

                            Text(
                                text = "Tap",
                                color = Color.Blue,
                                style = MaterialTheme.typography.overline,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        RepeatRead(
                            depoNumber = depoNo,
                            bustype = busType,
                            scheduleno = scheduleNo
                        )
                    }
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

@Composable
fun RepeatRead(depoNumber:String="",bustype:String="",scheduleno:String="") {
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
    var flag by remember { mutableStateOf(false) }
    val context = LocalContext.current
    depoNo = depoNumber
    busType = bustype
    scheduleNo = scheduleno
    Surface(color = Color(0xFF529155), contentColor = Color.White) {
    Column {


        if (flag) {
            flag = false
        } else
            UploadedScheduleList(depoNumber, bustype, scheduleno)
        val scroll = rememberScrollState()
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.horizontalScroll(scroll),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = tripNo,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { tripNo = it.replace("\\s+".toRegex(), "") },
                placeholder = {
                    Text(
                        text = "Trip ",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier
                    .width(85.dp)
                    .height(80.dp)
            )
            OutlinedTextField(
                value = departureTime,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    departureTime = it.replace("\\s+".toRegex(), "")
                },
                placeholder = {
                    Text(
                        text = "Departure Time:",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp)
            )
            OutlinedTextField(
                value = stPlace,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters
                ),
                onValueChange = { stPlace = it.replace("\\s+".toRegex(), "") },
                placeholder = {
                    Text(
                        text = "Enter Starting Place (eg:- KMR )",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp)
            )
            OutlinedTextField(
                value = via,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters
                ),
                onValueChange = { via = it.replace("\\s+".toRegex(), "") },
                placeholder = {
                    Text(
                        text = "Enter via (eg:- KTR )",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp)
            )
            OutlinedTextField(
                value = destination,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters
                ),
                onValueChange = {
                    destination = it.replace("\\s+".toRegex(), "")
                },
                placeholder = {
                    Text(
                        text = "Enter Destination Place (eg:- KTM )",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier
                    .width(125.dp)
                    .height(80.dp)
            )
            OutlinedTextField(
                value = arrivalTime,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    arrivalTime = it.replace("\\s+".toRegex(), "")
                },
                placeholder = {
                    Text(
                        text = "Enter Arrival Time (Eg:-18.00)",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier
                    .width(130.dp)
                    .height(80.dp)
            )
            OutlinedTextField(
                value = kilometer,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    kilometer = it.replace("\\s+".toRegex(), "")
                },
                placeholder = {
                    Text(
                        text = "Enter Trip Kilometer :",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier
                    .width(100.dp)
                    .height(80.dp)
            )
            OutlinedTextField(
                value = etm,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { etm = it.replace("\\s+".toRegex(), "") },
                placeholder = {
                    Text(
                        text = "Etm root No(optional)",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier
                    .width(100.dp)
                    .height(80.dp)
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
            TextButton(
                onClick = {
                    flag = true
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
                    backgroundColor = Color(0xff2e4f24),
                    contentColor = Color.Blue
                )
            ) {
                Surface(color = Color(0xff2e4f24)) {
                    Text(
                        text = "UPLOAD",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

            }

        }

    }
    }
}
@Composable
fun UploadedScheduleList(depoNumber: String, bustype: String, scheduleno: String) {
    val dataBase = FirebaseDatabase.getInstance()
    var result by rememberSaveable { mutableStateOf("") }

    if (depoNumber.isNotBlank() && bustype.isNotBlank() && scheduleno.isNotBlank()) {
        val myRef = dataBase.getReference("$depoNumber/$bustype/$scheduleno/")
        val data = StringBuffer()
        var i=0
        myRef.get()
            .addOnSuccessListener { dataSnapshot ->
                dataSnapshot?.children?.forEach { childSnapshot ->
                    i++
                    data.append("\n  $i " + childSnapshot.child("departureTime").value)
                    data.append("    " + childSnapshot.child("startPlace").value)
                    data.append("    " + childSnapshot.child("via").value)
                    data.append("    " + childSnapshot.child("destinationPlace").value)
                    data.append("     "+childSnapshot.child("arrivalTime").value)
                }
                result=data.toString()
            }
    }
     Text(result)
}






