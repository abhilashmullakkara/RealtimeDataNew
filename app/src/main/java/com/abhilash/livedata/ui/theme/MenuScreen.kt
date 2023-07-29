package com.abhilash.livedata.ui.theme

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.abhilash.livedata.R

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun MenuScreen(navController: NavController) {

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF8f8fb3)) {
        val scroll = rememberScrollState()
        Column {

            Card(
                modifier = Modifier
                    //.fillMaxSize()
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(100.dp),// Adjust the height as needed
                backgroundColor = Color.White,
                elevation = 5.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ksrtc),
                    contentDescription = "Image with Text",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=150.dp,start=10.dp,end=10.dp, bottom = 10.dp)
                .height(250.dp), // Adjust the height as needed
            backgroundColor = Color.White,
           // shape = CircleShape,
            elevation = 5.dp
        ) {
            Column(modifier = Modifier.verticalScroll(scroll)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(50.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    //shape = CircleShape,
                    shape = RoundedCornerShape(16.dp),

                    elevation = 5.dp
                ) {
                    TextButton(
                        onClick = {
                            navController.navigate("ReadScreen")
                        },
                        //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text("Add/Update Schedule", color = Color.Blue, fontSize = 15.sp)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape = CircleShape,
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
                    shape = CircleShape,
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
                    shape = CircleShape,
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
                    shape = CircleShape,
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
                    shape = CircleShape,
                    elevation = 5.dp
                ) {
                    TextButton(onClick = {
                        navController.navigate("FindMyBusScreen")
                    }) {
                        Text("Find my Bus", color = Color.Blue, fontSize = 20.sp)
                    }
                }

                //Room
                Text("              Duty Diary", color = Color.White, fontSize = 25.sp)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape = CircleShape,
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
                    shape = CircleShape,
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
                    shape = CircleShape,
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
                    shape = CircleShape,
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
                    shape = CircleShape,
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
                    shape = CircleShape,
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
                    shape = CircleShape,
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


@RequiresApi(Build.VERSION_CODES.M)
@Preview(showBackground = true)
@Composable
fun MenuScreenPreview(navController: NavHostController = NavHostController(ContextAmbient.current)){
    MenuScreen(navController )
}

class ContextAmbient {
    companion object {
        val current: Context
            get() {
                TODO()
            }
    }

}
