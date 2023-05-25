package com.abhilash.livedata.ui.theme.database

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun OpenDialer(phone:String) {
    val phoneNumber = remember {
        mutableStateOf(phone)
    }
    val ctx = LocalContext.current
            val u = Uri.parse("tel:" + phoneNumber.value)
            val i = Intent(Intent.ACTION_DIAL, u)
            try {
                ctx.startActivity(i)
            } catch (s: SecurityException) {
                Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
                    .show()
            }
        }
