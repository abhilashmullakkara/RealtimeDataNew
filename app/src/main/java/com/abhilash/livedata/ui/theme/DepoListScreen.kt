package com.abhilash.livedata.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.database.DepoData
import com.abhilash.livedata.ui.theme.database.depoList

@Composable
fun DepoListScreen(navController: NavController) {
    Surface(color = Color.White) {


        Column {
            IconButton(onClick = {
                navController.popBackStack()
            })
            {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
            }

            LazyColumn {
                items(depoList) { depo ->
//                    Text("Depo No:${depo.depoId}", textAlign = TextAlign.Center, modifier = Modifier.padding(start = 60.dp))
//                    Text(text = depo.depoName,textAlign = TextAlign.Center, modifier = Modifier.padding(start =60.dp ) )
//                    Text(text = depo.phone,textAlign = TextAlign.Center, modifier = Modifier.padding(start =60.dp ) )
//                    Text(text = depo.email,textAlign = TextAlign.Center, modifier = Modifier.padding(start = 60.dp))
//                    Divider()
                DepoItem(depoData = depo)
                    Divider()
                }
            }
        }


    }
}
@Composable
fun DepoItem(depoData: DepoData){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)) {
        Card(modifier = Modifier
            .padding(end = 8.dp, start = 15.dp)
            .size(40.dp)
            .clip(CircleShape),
            backgroundColor = Color(0XFF896DA3)
        )
        {
        Text(text=depoData.depoName[0].toString(),
         textAlign = TextAlign.Center, modifier = Modifier.padding(8.dp))
        }
        Column(modifier = Modifier.weight(2.0f) ) {
            Text(depoData.depoName,
            fontSize = 18.sp,
             fontWeight = FontWeight.Bold
                )
            Text("Depo NO:${depoData.depoId}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Text(depoData.phone,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(depoData.email,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

        }
        Column {
            IconButton(onClick = { },
                modifier = Modifier
                    .size(80.dp)
                    .padding(16.dp)
            ) {
                Icon(imageVector = Icons.Outlined.Star, contentDescription = "star")
            }

        }

    }
}