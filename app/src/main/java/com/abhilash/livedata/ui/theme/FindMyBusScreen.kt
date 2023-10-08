package com.abhilash.livedata.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun fetchDatabaseValues(
    databaseRef: DatabaseReference,
    path: String,
    destination: String,
    onSuccess: (List<Pair<String, OriginalData>>) -> Unit,
    onError: (Exception) -> Unit
) {
    if (path.isEmpty() || destination.isEmpty()) {
        onError(IllegalArgumentException("DepoNo and destination cannot be empty."))
        return
    }
    val resultList = mutableListOf<Pair<String, OriginalData>>()
    databaseRef.addValueEventListener(object : ValueEventListener {
        //addValueEventListener
        override fun onDataChange(snapshot: DataSnapshot) {
            val depoSnapshot = snapshot.child(path)
            resultList.clear()
            depoSnapshot.children.forEach { busTypeSnapshot ->
              //  val busType = busTypeSnapshot.key as String
                busTypeSnapshot.children.forEach { scheduleNoSnapshot ->
                    val scheduleNo = scheduleNoSnapshot.key as String
                    scheduleNoSnapshot.children.forEach { tripsSnapshot ->
                        val trips = tripsSnapshot.getValue(OriginalData::class.java)
                        // Update the Compose UI with the last data
                        // Check if trips is not null and destinationPlace matches the search destination
                        if (trips != null) {
                            Log.d("FetchDatabaseValues", "Destination: ${trips.destinationPlace}, Via: ${trips.via}")
                            if ((trips.destinationPlace == destination) || (trips.via == destination)) {
                                // Add the result to the resultList with scheduleNo
                                resultList.add(Pair(scheduleNo, trips))
                                Log.d("FetchDatabaseValues", "Added result for scheduleNo: $scheduleNo")
                            }
                        }

                    }
                }
            }
            onSuccess(resultList)
        }
        override fun onCancelled(error: DatabaseError) {
            onError(error.toException())
        }
    })
}
@Composable
fun searchAndStorePath(path: String = "", destination: String = "")//: List<Pair<String, OriginalData>>
{
    Surface(color = Color(0xF3698DBB)) {
        val resultList = remember { mutableStateListOf<Pair<String, OriginalData>>() }
        val errorMessage = remember { mutableStateOf("") }
        val databaseRef = FirebaseDatabase.getInstance().reference.child("")
        fetchDatabaseValues(databaseRef, path, destination, { results ->
            resultList.clear()
            resultList.addAll(results)
            errorMessage.value = "" // Clear any previous error message
        }, { error ->
            // Handle error here
            errorMessage.value = "Error: ${error.message}"
        })
        Column {
            if (errorMessage.value.isNotEmpty()) {
                Text(errorMessage.value,color=Color.Red)
            }
            // Display the search results
            if (resultList.isNotEmpty()) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Type")
                    Text("Departure")
                    Text(text = "From")
                    Text("Via")
                    Text("Destination")
                    Text("Arrival")
                    Text("DutyNo")

                }
                Divider(color= Color(0xF3C5CBD2), thickness = 2.dp)
                LazyColumn(modifier = Modifier.padding(start = 20.dp)) {
                    items(resultList) { (scheduleNo, result) ->

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp), horizontalArrangement = Arrangement.SpaceBetween){
                            Text(" ${result.bustype}")
                            Text(
                                text = result.departureTime,
                                color = Color.Red,
                                fontSize = 18.sp
                            )
                            Text(text = " ${result.startPlace}")
                            Text(" ${result.via}")
                            Text(
                                text = result.destinationPlace,
                                color = Color.Red,
                                fontSize = 18.sp
                            )
                            Text(text = " ${result.arrivalTime}")
                            Text(" $scheduleNo")

                        }

                        // Display other fields as needed
                        Divider(color= Color(0xF3E4E6E8), thickness = 1.dp)
                    }
                }
            } else {
                Text("No matching records found.")
            }
        }
        //return resultList
    }
}
@Composable
fun FindMyBusScreen(navController:NavController) {
    Surface(color = Color(0xF342699C)) {
        var depo by rememberSaveable { mutableStateOf("") }
        var destination by rememberSaveable { mutableStateOf("") }
        var flag by rememberSaveable { mutableStateOf(false) }
        Column {
            IconButton(onClick = { navController.popBackStack() }) {
                Surface (color = Color(0xF380B0ED), modifier = Modifier.fillMaxWidth()){
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
            }
            }
           // Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Enter depo number for limiting search within a depo",
                color = Color.LightGray, fontSize = 16.sp, fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(value = depo,
                        singleLine = true,
                        // shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { depo = it.replace("\\s+".toRegex(), "") },
                        placeholder = {
                            Text(
                                text = "Depot Number",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(value = destination,
                        singleLine = true,
                        // shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters
                        ),
                        onValueChange = { destination = it.replace("\\s+".toRegex(), "") },
                        placeholder = {
                            Text(
                                text = "Destination ",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    )
                }

            }

            Text(
                "Press Display button and scroll down to view",
                color = Color.Gray,
                fontSize = 17.sp
            )
            TextButton(
                onClick = {
                    flag = true
                }, modifier = Modifier.padding(start = 50.dp),
//                elevation = ButtonDefaults.elevation(
//                    defaultElevation = 10.dp,
//                    pressedElevation = 5.dp,
//                    disabledElevation = 0.dp,
//                    hoveredElevation = 5.dp,
//                    focusedElevation = 10.dp
//                ),
                colors= ButtonDefaults.textButtonColors( Color(0xFFAFC6E9))
            ) {
                Surface(color = Color(0xFFAFC6E9)) {
                    Text("DISPLAY")
                }

            }
             if (flag) {
                 Text(text = "Wait...")
                 searchAndStorePath(path = depo, destination = destination)

             }

        }
    }
}
@Composable
fun ChildrenListScreen(child:String) :List<String>{
    val childrenNames = remember { mutableStateListOf<String>() }
    LaunchedEffect(Unit) {
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("/$child")
        reference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val childName = snapshot.key
                childName?.let { name ->
                    childrenNames.add(name)
                }
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }
    return childrenNames
}
