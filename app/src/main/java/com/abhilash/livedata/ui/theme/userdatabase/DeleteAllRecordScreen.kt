package com.abhilash.livedata.ui.theme.userdatabase

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun DeleteAllRecordScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var recordList by remember { mutableStateOf<List<Employee>>(emptyList()) }


    Surface(color = Color(0xF342699C)) {
        Column (modifier = Modifier.fillMaxSize())
        {


        IconButton(onClick = {
            navController.popBackStack()
        })
        {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
        }
            Text("      The whole records will be deleted!! ",
                fontSize = 17.sp, color = Color.Gray)
        OutlinedButton(onClick = {

            coroutineScope.launch {
                EmployeeDB.getInstance(context).getEmployeeDao().deleteAll()
                EmployeeDB.getInstance(context).getEmployeeDao().resetSequence()
                recordList = emptyList()
                Toast.makeText(context, "All records deleted", Toast.LENGTH_SHORT).show()
            }

        }, colors = ButtonDefaults.textButtonColors(Color(0xFFD50000))) {
            Text(text = "DELETE ALL", color = Color.White)

        }
    }
}
}



