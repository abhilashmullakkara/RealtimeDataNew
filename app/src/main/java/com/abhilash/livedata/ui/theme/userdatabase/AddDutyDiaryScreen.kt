package com.abhilash.livedata.ui.theme.userdatabase

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import java.util.Calendar
import java.util.Date

@Composable
fun AddDutyDiaryScreen(navController: NavController){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = {
            navController.popBackStack()
        })
        {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
        }
//        var isLoading by remember {
//            mutableStateOf(true) }
//        LaunchedEffect(isLoading) {
//            if (isLoading) {
//                withContext(Dispatchers.Main) {
//                    delay(1500)
//                    isLoading = false
//                }
//            }
//        }
//Row{
//    Spacer(modifier = Modifier.width(150.dp))
//    CircularLoadingIndicator(isLoading)
//}

        RoomData()

       
    }

    }



@SuppressLint("SuspiciousIndentation")
@Composable
fun MyCalendar():String {
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
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear1, mMonth1, mDay1
    )
        Button(onClick = {
            mDatePickerDialog.show()
        },modifier = Modifier.padding(start=10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58)) ) {
            Text(text = "Select Date", color = Color.White)
        }
        Text(text = "Selected Date: ${mDate.value}", fontSize = 17.sp,modifier = Modifier.padding(start=10.dp)
        )
    return mDate.value
}


@Composable
fun CircularLoadingIndicator(isLoading: Boolean) {
    if (isLoading) {
        CircularProgressIndicator()
    }
    }
