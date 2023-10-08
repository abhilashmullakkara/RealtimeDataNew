package com.abhilash.livedata.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase

@Composable
fun DeleteScheduleScreen(navController:NavController){
        var scheduleNo by rememberSaveable { mutableStateOf("") }
       // var tripNo by rememberSaveable { mutableStateOf("") }
        var clicked by rememberSaveable { mutableStateOf(false) }
        var depoNo by rememberSaveable { mutableStateOf("") }
        var bType by rememberSaveable { mutableStateOf("") }
        val context= LocalContext.current
        Surface(color = Color(0xFF975ECD)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            )
            {
                IconButton(onClick = {
                    navController.popBackStack()
                })
                {
                    Icon(imageVector = Icons.Outlined.ArrowBack,tint= Color.White, contentDescription = "Arrow")
                }
                Text(
                    "Enter Schedule Information to Delete",
                    fontSize = 19.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.SemiBold,
                )
                //Read field to delete
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 3.dp,
                    contentColor = Color.Black,
                    backgroundColor =  Color(0xFFA37DC9)
                ) {
                    val scrollState = rememberScrollState()
                    Box(modifier = Modifier.verticalScroll(scrollState)) {
                        Column {

                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(value = depoNo,
                                singleLine = true,
                                shape = RoundedCornerShape(80),
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                onValueChange = { depoNo = it },
                                //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                                placeholder = {
                                    Text(
                                        text = "Enter Depo Number (eg:- KMR->34)",
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xFFC4A8E0),
                                    textColor= Color.Black)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(value = bType,
                                singleLine = true,
                                shape = RoundedCornerShape(80),
                                // keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Characters
                                ),
                                onValueChange = { bType = it },
                                //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                                placeholder = {
                                    Text(
                                        text = "Enter Type (eg:- FP )",
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xFFC4A8E0),
                                    textColor= Color.Black)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(value = scheduleNo,
                                singleLine = true,
                                shape = RoundedCornerShape(80),
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                onValueChange = { scheduleNo = it },
                                //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                                placeholder = {
                                    Text(
                                        text = "Enter Schedule No (eg:- 1)",
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xFFC4A8E0),
                                    textColor= Color.Black)
                            )

                            Spacer(modifier = Modifier.height(20.dp))
//                            OutlinedTextField(value = tripNo,
//                                singleLine = true,
//                                shape = RoundedCornerShape(80),
//                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                                onValueChange = { tripNo = it },
//                                //modifier=Modifier.padding(start = 20.dp,end=250.dp),
//                                placeholder = {
//                                    Text(
//                                        text = "Enter Trip Number (eg:- 1)",
//                                        color = Color.Black,
//                                        fontSize = 15.sp
//                                    )
//                                }
//                            )
                            OutlinedButton(onClick = {
                                clicked = true
                                val dataBase = FirebaseDatabase.getInstance()
                                val myRef = dataBase.getReference("$depoNo/$bType/")
                                if (scheduleNo.isNotBlank() && depoNo.isNotBlank() && bType.isNotBlank()) {


                                myRef.child(scheduleNo).removeValue().addOnSuccessListener {
                                    Toast.makeText(
                                        context,
                                        "Record Deleted or not exists",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    //depoNo=""
                                    bType = ""
                                    scheduleNo = " "
                                }.addOnFailureListener {
                                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                                else {
                                    Toast.makeText(
                                        context,
                                        "Input data first",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                               // navController.popBackStack()
                            },
                                colors = ButtonDefaults.buttonColors(Color(0xFFE01540))
                                ) {
                                Text(text = "DELETE", color = Color.White, fontSize =16.sp , fontWeight = FontWeight.SemiBold)

                            }



                        }
                    }


                }
                //End of card |

            }
        }
    }

