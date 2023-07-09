package com.abhilash.livedata.ui.theme.userdatabase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ViewDiaryScreen(navController: NavController) {
    var flag by rememberSaveable { mutableStateOf(false) }
    var result by rememberSaveable { mutableStateOf("") }
    var result2 by rememberSaveable { mutableStateOf("") }
    val scroll = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Surface(color = Color.White) {


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            })
            {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
            }
            Text("For detailed view rotate the screen ", color = Color.Gray, fontSize = 14.sp)

            Row {
                val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                OutlinedButton(
                    onClick = {
                        flag = false
                        coroutineScope.launch {
                           // val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            val data = StringBuffer()
                            val employeeInfo =
                                EmployeeDB.getInstance(context).getEmployeeDao().display()
                            for (employe in employeeInfo) {

                                data.append("\n   " + employe.id + ")            " + employe.dutyNo + "          " +
                                        format.format(employe.performedOn)  + "          " + employe.dutyEarned)
                                // data.append(" " + employe.wayBillNo + " " + employe.employeeName + " " + employe.collection)
                            }

                            result = data.toString()
                        }
                    },
                    modifier = Modifier.padding(start = 25.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF456890))
                )
                {
                    Text("View /", color = Color.White, fontSize = 16.sp)
                }
                //detailed view
                //Spacer(modifier = Modifier.width(15.dp))
                OutlinedButton(

                    onClick = {
                        flag = true
                        coroutineScope.launch {
                            val data = StringBuffer()
                            val employeeInfo =
                                EmployeeDB.getInstance(context).getEmployeeDao().display()
                            for (employe in employeeInfo) {

                                data.append("\n   " + employe.id + ")            " + employe.dutyNo + "           " + format.format(employe.performedOn) + "           " + employe.dutyEarned)
                                data.append("            " + employe.wayBillNo + "          " + employe.employeeName + "           ₹" + employe.collection)
                                // data.append(" " + employe.employeeName + " " + employe.collection + " " + employe.wayBillNo )
                            }

                            result2 = data.toString()
                        }
                    },
                    // modifier = Modifier.padding(start = 25.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF456890))
                )
                {
                    Text("Detailed View ", color = Color.White, fontSize = 16.sp)
                }


            }

            Text("Record    DutyNo      Performed on    Duty earned     W/B no  CrewName    Collection")
            Column(modifier = Modifier.verticalScroll(scroll)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(600.dp), // Adjust the height as needed
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(0.4f),
                    elevation = 5.dp
                ) {
                    if (flag) {
                        Text(
                            text = result2,
                            color = Color.Red,
                            fontSize = 17.sp,
                            textAlign =TextAlign.Justify,
                            modifier = Modifier.fillMaxSize().padding(start = 15.dp)
                        )
                    } else {
                        //  Toast.makeText(context, "Record not found", Toast.LENGTH_SHORT).show()
                        Text(
                            text = result,
                            color = Color.Red,
                            fontSize = 17.sp,
                            textAlign =TextAlign.Justify,
                            modifier = Modifier.fillMaxSize().padding(start = 15.dp)
                        )
                    }


                }
            }
        }
    }
}