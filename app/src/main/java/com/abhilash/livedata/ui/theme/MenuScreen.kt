package com.abhilash.livedata.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun MenuScreen(navController: NavController) {

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xff90918a)) {
        val scroll = rememberScrollState()
        Column {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .height(150.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    elevation = 5.dp
                ) {

                }

            Column(modifier = Modifier.verticalScroll(scroll)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape = CircleShape,
                    elevation = 5.dp
                ) {

                    TextButton(
                        onClick = {
                            navController.navigate("ReadScreen")
                        },
                        //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text("Add/Update Schedule", color = Color.Blue, fontSize = 20.sp)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {

                    TextButton(onClick = {
                        navController.navigate("DisplayScreen")
                    }) {
                        Text("View Schedule ", color = Color.Blue, fontSize = 20.sp)

                    }

                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                        navController.navigate("DeleteTripScreen")
                    }) {
                        Text("Delete Trip", color = Color.Blue, fontSize = 20.sp)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                        navController.navigate("DeleteScheduleScreen")
                    }) {
                        Text("Delete Schedule", color = Color.Blue, fontSize = 20.sp)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                        navController.navigate("DepoListScreen")
                    }) {
                        Text("Find my depo number", color = Color.Blue, fontSize = 20.sp)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                       navController.navigate("FindMyBusScreen")
                    }) {
                        Text("Find my Bus", color = Color.Blue, fontSize = 20.sp)
                    }
                }

                //Room
                Text("              Duty Diary", color = Color.White, fontSize =25.sp )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                        navController.navigate("AddDutyDiaryScreen")
                    }) {
                        Text("Add Duty ", color = Color.Blue, fontSize = 20.sp)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                         navController.navigate("ViewDiaryScreen")
                    }) {
                        Text("View Diary ", color = Color.Blue, fontSize = 20.sp)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                       navController.navigate("DeleteRecordScreen")
                    }) {
                        Text("Delete Record", color = Color.Blue, fontSize = 20.sp)
                    }
                }
                //Update Duty Diary
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                        navController.navigate("UpdateDutyDiaryScreen")
                    }) {
                        Text("Update duty Diary", color = Color.Blue, fontSize = 20.sp)
                    }
                }
                //
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color(0xFFF00B04),
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                        navController.navigate("DeleteAllRecordScreen")
                    }) {
                        Text("DELETE ALL", color = Color.White, fontSize = 20.sp)
                    }
                }
                //Utility functions
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                        navController.navigate("CurrencyCountScreen")
                    }) {
                        Text("Currency Count ", color = Color.Blue, fontSize = 20.sp)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape= CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                        navController.navigate("FareTableScreen")
                    }) {
                        Text("Fare Tables ", color = Color.Blue, fontSize = 20.sp)
                    }
                }

            }


        }
    }
        }


@Preview(showBackground = true)
@Composable
fun MenuScreenPreview(){
    //MenuScreen()
}
