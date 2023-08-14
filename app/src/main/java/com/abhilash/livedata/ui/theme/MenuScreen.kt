package com.abhilash.livedata.ui.theme

//
//
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.R

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun MenuScreen(navController: NavController) {

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF8f8fb3)) {
        val scroll = rememberScrollState()
        Column {
            TopAppBar(
                title = { Text("വേല  ", fontSize = 25.sp,color=Color.White,

                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    textAlign = TextAlign.Center)
                        /* Text or your app title */ },

                backgroundColor = Color(0xFF00897B), // Customize the app bar background color
               elevation = 4.dp // Set the elevation of the app bar
            )
            Box(  modifier= Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF00897B)),
                contentAlignment = Alignment.Center )
            {
                Text(" KSRTC  Duty  Diary  ", fontSize = 9.sp,color=Color.White)

            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(150.dp),// Adjust the height as needed
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
                .padding(top = 208.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                .height(240.dp), // Adjust the height as needed
            backgroundColor = Color.White,
           // shape = CircleShape,
            elevation = 5.dp
        ) {
            Column(modifier = Modifier.verticalScroll(scroll)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(240.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    //shape = CircleShape,
                    shape = RoundedCornerShape(16.dp),

                    elevation = 5.dp
                ) {
                    Column {
                        Text("SCHEDULE", fontSize = 20.sp,color=Color.Blue,fontWeight= FontWeight.Bold,
                            modifier= Modifier
                                .padding(start = 50.dp, end = 20.dp, top = 10.dp)
                                .fillMaxWidth())
                        Divider()
                   Row {
                       TextButton(
                           onClick = {
                               navController.navigate("ReadScreen")
                           },
                           //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                       ) {
                           Text("Add/Update ", color = Color.Blue, fontSize = 15.sp)
                       }
                       Icon(
                           imageVector = Icons.Default.AddCircle,
                           contentDescription = "Add",
                           modifier = Modifier
                               .size(20.dp)
                               .clickable {
                                   navController.navigate("ReadScreen")
                               }
                       )
                   }
                    Row {
                        TextButton(onClick = {
                            navController.navigate("DisplayScreen")
                        }) {
                            Text("View Schedule ", color = Color.Blue, fontSize = 20.sp)
                        }
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Add",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    navController.navigate("DisplayScreen")
                                }
                        )
                    }
                     Row {
                         TextButton(onClick = {
                             navController.navigate("DeleteTripScreen")
                         }) {
                             Text("Delete Trip", color = Color.Blue, fontSize = 20.sp)
                         }
                         Icon(
                             imageVector = Icons.Default.Delete,
                             contentDescription = "Add",
                             modifier = Modifier
                                 .size(20.dp)
                                 .clickable {
                                     navController.navigate("DeleteTripScreen")
                                 }
                         )
                     }
                        Row {
                            TextButton(onClick = {
                                navController.navigate("DeleteScheduleScreen")
                            }) {
                                Text("Delete Schedule", color = Color.Blue, fontSize = 18.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Add",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        navController.navigate("DeleteScheduleScreen")
                                    }
                            )

                        }


                }
            }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(180.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    //shape = CircleShape,
                    elevation = 5.dp
                ) {
                    Column {
                        Text("Details of Bus and Depot",
                            fontSize = 20.sp,color=Color.Blue,fontWeight= FontWeight.Bold,
                            modifier= Modifier
                                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                                .fillMaxWidth())
                        Divider()
                        Row {
                            TextButton(onClick = {
                                navController.navigate("DepoListScreen")
                            }) {
                                Text("Find my depo number", color = Color.Blue, fontSize = 20.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Add",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        navController.navigate("DepoListScreen")
                                    }
                            )
                        }
                        Row {
                            TextButton(onClick = {
                                navController.navigate("FindMyBusScreen")
                            }) {
                                Text("Find my Bus", color = Color.Blue, fontSize = 20.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        navController.navigate("FindMyBusScreen")
                                    }
                            )
                        }

                    }

                }
                //Room
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(300.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                   // shape = CircleShape,
                    elevation = 5.dp
                ) {
                    Column {
                        Text("DUTY DIARY", fontSize = 20.sp,color=Color.Red,fontWeight= FontWeight.Bold,
                            modifier= Modifier
                                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                                .fillMaxWidth())
                        Row {
                            TextButton(onClick = {
                                navController.navigate("AddDutyDiaryScreen")
                            }) {
                                Text("Add Duty ", color = Color.Blue, fontSize = 20.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        navController.navigate("AddDutyDiaryScreen")
                                    }
                            )
                        }
                        Row {
                            TextButton(onClick = {
                                navController.navigate("ViewDiaryScreen")
                            }) {
                                Text("View Diary ", color = Color.Blue, fontSize = 20.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "check",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        navController.navigate("ViewDiaryScreen")
                                    }
                            )
                        }
                        Row {
                            TextButton(onClick = {
                                navController.navigate("DeleteRecordScreen")
                            }) {
                                Text("Delete Record", color = Color.Blue, fontSize = 20.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "check",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        navController.navigate("DeleteRecordScreen")
                                    }
                            )
                        }
                        Row {
                            TextButton(onClick = {
                                navController.navigate("UpdateDutyDiaryScreen")
                            }) {
                                Text("Update duty Diary", color = Color.Blue, fontSize = 20.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "update",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        navController.navigate("UpdateDutyDiaryScreen")
                                    }
                            )
                        }
                        Row {
                            TextButton(onClick = {
                                navController.navigate("DeleteAllRecordScreen")
                            }) {
                                Text("DELETE ALL", color = Color.Blue, fontSize = 20.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "delete",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        navController.navigate("DeleteAllRecordScreen")
                                    }
                            )
                        }
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
                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(150.dp), // Adjust the height as neededmodifier = modifier.background(shape = RoundedCornerShape(cornerRadius))
                    backgroundColor = Color.White,
                    shape = CircleShape,
                    elevation = 5.dp
                ) {
                   // Card( modifier=Modifier.background(shape = RoundedCornerShape(100),color=Color.Red)) {
                    CircularCardExample()
                    }
                    }
                }
            }


        }

@Composable
fun CircularCardExample() {
    CircularCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(100.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        // Content inside the circular card
        // You can put any content you want here
    }
}

@Composable
fun CircularCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    elevation: Dp = 5.dp,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.background(shape = CircleShape, color = Color.Red),
        backgroundColor = backgroundColor,
        shape = CircleShape.copy(),
        elevation = elevation
    ) {
        content()
    }
}


