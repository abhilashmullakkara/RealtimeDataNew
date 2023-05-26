package com.abhilash.livedata.ui.theme

import DisplayScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhilash.livedata.ui.theme.read.ReadScreen
import com.abhilash.livedata.ui.theme.userdatabase.AddDutyDiaryScreen

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun MyApp(){
   ScreenManager()
}


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ScreenManager(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination ="MenuScreen"  ){
        composable("MenuScreen"){
            MenuScreen(navController)
        }
        composable("DisplayScreen"){
            DisplayScreen(navController)
        }
        composable("ReadScreen"){
            ReadScreen(navController)
        }
        composable("DeleteTripScreen"){
            DeleteTripScreen(navController)
        }
        composable("DeleteScheduleScreen"){
            DeleteScheduleScreen(navController)
        }
        composable("DepoListScreen"){
            DepoListScreen(navController)
        }
        composable("AddDutyDiaryScreen"){
            AddDutyDiaryScreen(navController)
        }
    }
}
