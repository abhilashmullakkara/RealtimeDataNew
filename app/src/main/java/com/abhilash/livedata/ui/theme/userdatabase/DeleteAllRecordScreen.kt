package com.abhilash.livedata.ui.theme.userdatabase

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun DeleteAllRecordScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Surface(color = Color.White) {
        Column {


        IconButton(onClick = {
            navController.popBackStack()
        })
        {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
        }
        OutlinedButton(onClick = {

            coroutineScope.launch {
                EmployeeDB.getInstance(context).getEmployeeDao().deleteAll()
                Toast.makeText(context, "All records deleted", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "DELETE ALL")

        }
    }
}
}