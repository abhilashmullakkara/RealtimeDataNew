package com.abhilash.livedata

import DisplayScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abhilash.livedata.ui.theme.LiveDataTheme
import com.abhilash.livedata.ui.theme.MenuScreen
import com.abhilash.livedata.ui.theme.MyApp
import com.abhilash.livedata.ui.theme.read.ReadScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //val readData=
                       // ReadScreen()
                   // Authi()
                   // DisplayScreen()
                    MyApp()
                }
            }
        }
    }
}

//@Composable
//fun Authi(schedule:Shedule){
//
//    val originalDatabase=OriginalData(
//        startPlace=schedule.startPlace,
//        via=schedule.via,
//        destinationPlace=schedule.destinationPlace,
//        departureTime=schedule.departureTime,
//        arrivalTime=schedule.arrivalTime,
//        kilometer=schedule.kilometer
//    )
//    val database = FirebaseDatabase.getInstance()
//    val myRef = database.getReference(schedule.depoNumber)
//    myRef.child(schedule.depoNumber).child(schedule.scheduleNo).child(schedule.tripNumber).setValue(originalDatabase)
//
//}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LiveDataTheme {
        //ReadScreen()
    }
}