package com.abhilash.livedata.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.abhilash.livedata.ui.theme.database.Shedule.Companion.scheduleNo
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
        onError(IllegalArgumentException("Path and destination cannot be empty."))
        return
    }

    val resultList = mutableListOf<Pair<String, OriginalData>>()

    databaseRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val depoSnapshot = snapshot.child(path)
            depoSnapshot.children.forEach { busTypeSnapshot ->
                val busType = busTypeSnapshot.key as String
                busTypeSnapshot.children.forEach { scheduleNoSnapshot ->
                    val scheduleNo = scheduleNoSnapshot.key as String
                    scheduleNoSnapshot.children.forEach { tripsSnapshot ->
                        val trips = tripsSnapshot.getValue(OriginalData::class.java)
                        // Check if trips is not null and destinationPlace matches the search destination
                        if (trips != null && trips.destinationPlace == destination) {
                            // Add the result to the resultList with scheduleNo
                            resultList.add(Pair(scheduleNo, trips))
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

fun searchAndStorePath(path: String = "-", destination: String = "-"): List<Pair<String, OriginalData>>  {
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
            Text(errorMessage.value)
        }

        // Display the search results
        if (resultList.isNotEmpty()) {
            LazyColumn {
                items(resultList) { (scheduleNo, result) ->
                    Text("Bus Type: ${result.bustype}")
                    Text(text = "Time ${result.departureTime}")
                    Text(text = "From ${result.startPlace}")
                    Text("Via:  ${result.via}")
                    Text(text = "To ${result.destinationPlace}")
                    Text(text = "arrival at: ${result.arrivalTime}")
                    Text("Duty No: $scheduleNo")
                    // Display other fields as needed

                    Divider()
                }
            }
        } else {
            Text("No matching records found.")
        }
    }

    return resultList
}
@Composable
fun FindMyBusScreen(navController:NavController) {
    var depo by rememberSaveable { mutableStateOf("") }
    var destination by rememberSaveable { mutableStateOf("") }
    var path: SnapshotStateList<String>
    val errorMessage = remember { mutableStateOf("") }
    Column {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = depo,
            singleLine = true,
            shape = RoundedCornerShape(80),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = { depo = it },
            placeholder = {
                Text(
                    text = "Enter Depot Number",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = destination,
            singleLine = true,
            shape = RoundedCornerShape(80),
            //keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters
            ),
            onValueChange = { destination = it },
            placeholder = {
                Text(
                    text = "Destination",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        )
       path= searchAndStorePath(path=depo,destination=destination) as SnapshotStateList<String>
//        path.forEach{
//            Text(text = it)
//        }



    }
}












