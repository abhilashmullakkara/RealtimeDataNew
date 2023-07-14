package com.abhilash.livedata.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
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
    var firstGeneration: SnapshotStateList<String>
    var secondGeneration = SnapshotStateList<String>()
    var thirdGeneration = remember { mutableStateListOf<String>() }
    val memberCounts = remember { mutableStateMapOf<String, Int>() }


//    LaunchedEffect(Unit) {
//        // Retrieve the first generation names from Firebase
//        val database = FirebaseDatabase.getInstance()
//        val reference = database.getReference("")
//
//        reference.addChildEventListener(object : ChildEventListener {
//            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//                val childName = snapshot.key
//                childName?.let { name ->
//                    firstGeneration.add(name)
//                    memberCounts[name] = 0 // Initialize member count to 0
//                }
//            }
//
//            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
//            override fun onChildRemoved(snapshot: DataSnapshot) {}
//            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
//            override fun onCancelled(error: DatabaseError) {}
//        })
//    }

//    LaunchedEffect(firstGeneration) {
//        firstGeneration.forEach { firstGenName ->
//            val database = FirebaseDatabase.getInstance()
//            val reference = database.getReference(firstGenName)
//
//            reference.addChildEventListener(object : ChildEventListener {
//                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//                    val childName = snapshot.key
//                    childName?.let { name ->
//                        secondGeneration.add(name)
//                        memberCounts[firstGenName] = memberCounts[firstGenName]?.plus(1) ?: 1
//                    }
//                }
//
//                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
//                override fun onChildRemoved(snapshot: DataSnapshot) {}
//                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
//                override fun onCancelled(error: DatabaseError) {}
//            })
//        }
//    }
//
//    LaunchedEffect(secondGeneration) {
//        secondGeneration.forEach { secondGenName ->
//            val firstGenName = secondGenName.substringBefore('/')
//            val database = FirebaseDatabase.getInstance()
//            val reference = database.getReference(secondGenName)
//
//            reference.addChildEventListener(object : ChildEventListener {
//                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//                    val childName = snapshot.key
//                    childName?.let { name ->
//                        thirdGeneration.add(name)
//                    }
//                }
//
//                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
//                override fun onChildRemoved(snapshot: DataSnapshot) {}
//                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
//                override fun onCancelled(error: DatabaseError) {}
//            })
//        }
//    }
firstGeneration= findRoot(ref = "") as SnapshotStateList<String>
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
            Text("First Generation:")
            var name1=""
            firstGeneration.forEach { name ->
                Text(name)
                name1=name
                secondGeneration=findRoot(ref = name) as SnapshotStateList<String>
                //Text("Member Count: ${memberCounts[name]}")
            }
            secondGeneration.forEach {

                if (it.isNotBlank() ){
                    Text(text = it)
                    thirdGeneration=findRoot(ref = "$name1/+$it") as SnapshotStateList<String>
                }
                
            }
            thirdGeneration.forEach { 
                if (it.isNotBlank()){
                    Text(text = it)
                }
            }
        }
    }
}


@Composable
fun findRoot(ref:String):List<String> {
    val firstGeneration = remember { mutableStateListOf<String>() }
    LaunchedEffect(Unit) {
        // Retrieve the first generation names from Firebase
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference(ref)

        reference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val childName = snapshot.key
                childName?.let { name ->
                    firstGeneration.add(name)
                    //memberCounts[name] = 0 // Initialize member count to 0
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }
    return firstGeneration
}

