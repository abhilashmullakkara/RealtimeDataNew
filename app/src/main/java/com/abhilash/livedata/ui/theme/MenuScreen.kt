package com.abhilash.livedata.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
@Composable
fun MenuScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFFFFFFF)) {
        Column {
            TextButton(onClick = {
                navController.navigate("ReadScreen")
            },
                //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text("Add/Update Schedule")
            }
            TextButton(onClick = {
                navController.navigate("DisplayScreen")
            }) {
                Text("View")
                
            }
            TextButton(onClick = {
                navController.navigate("DeleteTripScreen")
            }) {
                Text("Delete Trip")

            }
            TextButton(onClick = {
                navController.navigate("DeleteScheduleScreen")
            }) {
                Text("Delete Schedule")
            }
            TextButton(onClick = {
                navController.navigate("DepoListScreen")
            }) {
                Text("Find my depo number")
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun MenuScreenPreview(){
    //MenuScreen()
}