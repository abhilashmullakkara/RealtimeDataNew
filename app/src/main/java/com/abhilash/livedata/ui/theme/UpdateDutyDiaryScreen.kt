package com.abhilash.livedata.ui.theme

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.read.isValidText
import com.abhilash.livedata.ui.theme.userdatabase.Employee
import com.abhilash.livedata.ui.theme.userdatabase.EmployeeDB
import com.abhilash.livedata.ui.theme.userdatabase.MyCalendar
import kotlinx.coroutines.launch
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UpdateDutyDiaryScreen(navController: NavController){
    Column {
        IconButton(onClick = {
            navController.popBackStack()
        })
        {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
        }
        UpdateDiary()


    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UpdateDiary() {
    Surface(color = Color(0xFFBDBABA)) {


        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var recNo by rememberSaveable { mutableStateOf("") }
        var scheduleNo by rememberSaveable { mutableStateOf("") }
        var dutyEearnt by rememberSaveable { mutableStateOf("") }
        var permedDate by rememberSaveable { mutableStateOf(Date()) }
        var todayCollection by rememberSaveable { mutableStateOf("") }
        var wBillNo by rememberSaveable { mutableStateOf("") }
        var crewName by rememberSaveable { mutableStateOf("") }
        Column {

            Text(
                "Enter Record NO:", fontSize = 19.sp,
                color = Color(0xFF3949AB),
                fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp)
            )
            OutlinedTextField (
                value = recNo,
                singleLine = true,
                //shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    recNo=it
                },
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = "Record No:",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                },
                    )
            Text(text = "After entering Record number," +
                    " press view button to load record for updation",
                color = Color.Gray)
            //view corresponding record
            OutlinedButton(onClick = {
                if (recNo.isBlank())
                { recNo="0"
                  Toast.makeText(context,"Record Not found",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    coroutineScope.launch {
                        try {
                            val employeeInfo = EmployeeDB.getInstance(context).getEmployeeDao().view(recNo = recNo.toInt())
                            scheduleNo=employeeInfo.dutyNo
                            dutyEearnt=employeeInfo.dutyEarned
                            crewName=employeeInfo.employeeName
                            todayCollection=employeeInfo.collection
                            wBillNo=employeeInfo.wayBillNo
                            permedDate=employeeInfo.performedOn
                        } catch (e: Exception) {
                            Toast.makeText(context,"Record Not found",Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            })
            {
                Text(text = "VIEW RECORD ")
            }

            OutlinedTextField(
                value = scheduleNo,
                singleLine = true,
                //shape = RoundedCornerShape(80),
                // keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        scheduleNo = textFieldValue.text
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = "Schedule NO:",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = dutyEearnt,
                singleLine = true,
               // shape = RoundedCornerShape(80),
                //keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                onValueChange = {
                    dutyEearnt = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = "No of duty earned:",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            // Text("Select Date ")
            permedDate = MyCalendar()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Optional Data (below)",
                fontSize = 17.sp,
                color = Color.Red,
                modifier = Modifier.padding(start = 10.dp)
            )
            Divider(thickness = 3.dp)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = todayCollection,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    todayCollection = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = "Collection:",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }

            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = wBillNo,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        wBillNo = textFieldValue.text
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = "Way Bill No",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
//
            OutlinedTextField(
                value = crewName,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                onValueChange = {
                    crewName = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = "Name of the crew",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
            OutlinedButton(onClick = {
                if (scheduleNo.isNotBlank() && dutyEearnt.isNotBlank()) {
                    coroutineScope.launch {
                        if (todayCollection.isBlank()) todayCollection = "--.--"
                        val employee = Employee(
                            scheduleNo,
                            permedDate,
                            dutyEearnt,
                            todayCollection,
                            crewName,
                            wBillNo,
                            id=recNo.toInt()
                            )
                        EmployeeDB.getInstance(context).getEmployeeDao().update(employee)

                        Toast.makeText(context, "Record inserted successfully", Toast.LENGTH_SHORT)
                            .show()
                        scheduleNo = ""
                        dutyEearnt = ""
                        todayCollection = ""
                        crewName = ""
                        wBillNo = ""


                    }


                } else {
                    Toast.makeText(context, "Input Record first", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("UPDATE")
            }

        }
    }
}

