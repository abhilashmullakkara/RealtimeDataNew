package com.abhilash.livedata.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

@Composable
fun FindMyBusScreen(navController: NavController) {
    val scroll = rememberScrollState()
    val children1 = remember { mutableStateListOf<String>() }
    var tolalDepo by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        // Retrieve the children names from Firebase
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("")

        reference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val childName = snapshot.key
                childName?.let { name ->
                    children1.add(name)
                    tolalDepo++
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        }
        )
        onChildrenNamesLoaded(childrenNames)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(10.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
        }

        Column {
            Text("Children Names:")
            children1.forEach { name ->
                Text(name)
            }
        }
for ( i in 0 until tolalDepo)
    for (j in 0 until i)
        if (children1.isNotEmpty()) {
            val secondGeneration: List<String> = childrenListScreen(children1[i])
            Column {
                Text("Children Names:")
                secondGeneration.forEach { name ->
                    Text(name)
                }
            }

            if (secondGeneration.isNotEmpty()) {
                val childPath = "${children1[i]}/${secondGeneration[j]}".replace('/', '_')
                val thirdGeneration: List<String> = childrenListScreen(childPath)
                Column {
                    Text("Children Names:")
                    thirdGeneration.forEach { name ->
                        Text(name)
                    }
                }
            }
        }
    }
}

@Composable
fun childrenListScreen(child: String, onChildrenNamesLoaded: (List<String>) -> Unit) {
    val childrenNames = remember { mutableStateListOf<String>() }

    // Retrieve the children names from Firebase
    LaunchedEffect(Unit) {
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference(child)

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
        onChildrenNamesLoaded(childrenNames)

    }


    // Display the children names in a Compose UI
//    Column {
//        Text("Children Names:")
//        childrenNames.forEach { name ->
//            Text(name)
//        }
    //}
    //return childrenNames
}

