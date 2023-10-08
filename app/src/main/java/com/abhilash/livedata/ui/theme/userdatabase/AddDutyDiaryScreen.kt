package com.abhilash.livedata.ui.theme.userdatabase

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddDutyDiaryScreen(navController: NavController){
    Surface(color = Color(0xFF073415)) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(onClick = {
                navController.popBackStack()
            })
            {
                Icon(imageVector = Icons.Outlined.ArrowBack,tint=Color.White, contentDescription = "Arrow")
            }
           // Spacer(modifier = Modifier.height(5.dp))
            RoomData()
        }

    }
       }


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@Composable
fun MyCalendar(): Date {
    //val format = SimpleDateFormat.getDateInstance()
    val date:Date = Date() // Replace this with your actual Date object
    //val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val mContext = LocalContext.current
    val mYear1: Int
    val mMonth1: Int
    val mDay1: Int
    val mCalendar = Calendar.getInstance()
    mYear1 = mCalendar.get(Calendar.YEAR)
    mMonth1 = mCalendar.get(Calendar.MONTH)
    mDay1 = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("") }
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear1, mMonth1, mDay1
    )
    Button(
        onClick = {
            mDatePickerDialog.show()
        }, modifier = Modifier.padding(start = 10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
    ) {
        Text(text = "Select Date", color = Color.White)
    }
    Text(
        text = "Selected Date: ${mDate.value}",
        fontSize = 17.sp,
        modifier = Modifier.padding(start = 10.dp)
    )
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    println(dateFormat.format(Date())) //data can be inserted in this format function


    val format = SimpleDateFormat("dd/MM/yyyy")
   // val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH)
    val dateStr = mDate.value
    return if (dateStr.isEmpty()) {
        val formattedDateStr = format.format(Date())
       format.parse(formattedDateStr) ?: Date()
    }
    else
    {
        try {
            format.parse(dateStr)
        } catch (e: ParseException) {
            val formattedDateStr = format.format(Date())
            format.parse(formattedDateStr) ?: Date()
        }
    }
}


@Composable
fun CircularLoadingIndicator(isLoading: Boolean) {
    if (isLoading) {
        CircularProgressIndicator()
    }
    }
