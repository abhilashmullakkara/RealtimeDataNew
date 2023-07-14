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

    LaunchedEffect(Unit) {
        // Retrieve the children names from Firebase
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("")

        reference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val childName = snapshot.key
                childName?.let { name ->
                    children1.add(name)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
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
            for (i in 0 until children1.size) {
                val name = children1[i]
                Text(name)
                for (j in 0 until i) {
                    val childPath = "$children1[i]/"
                    val secondGeneration: List<String> = childrenListScreen(childPath)
                    Column {
                        Text("Children Names:")
                        secondGeneration.forEach { thirdName ->
                            Text(thirdName)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun childrenListScreen(child: String): List<String> {
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
    }

    return childrenNames
}


