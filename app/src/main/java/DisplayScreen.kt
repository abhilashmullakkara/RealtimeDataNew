import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.read.isValidText
import com.google.firebase.database.FirebaseDatabase

@Composable
fun DisplayScreen(navController: NavController) {
    val context = LocalContext.current
    var depoNo by rememberSaveable { mutableStateOf("") }
    var scheduleNo by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }
    var busType by rememberSaveable { mutableStateOf("") }
    var ti by rememberSaveable { mutableStateOf(0) }
    val dataBase = FirebaseDatabase.getInstance()
val scroll= rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scroll)) {
       
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
        }
        //READ fields from the user
        Row {
            OutlinedTextField(
                value = depoNo,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        depoNo = textFieldValue.text
                    }
                },
                modifier = Modifier.fillMaxWidth(0.23f),
                placeholder = {
                    Text(
                        text = "Depo NO:",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
            OutlinedTextField(
                value = scheduleNo,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        scheduleNo = textFieldValue.text
                    }
                },
                modifier = Modifier.fillMaxWidth(0.37f),
                placeholder = {
                    Text(
                        text = "Schedule NO:",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
            OutlinedTextField(
                value = busType,
                singleLine = true,
                shape = RoundedCornerShape(80),
                //keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters
                ),
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        busType = textFieldValue.text
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Type(FP,Ord,JNT):",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        val myRef = dataBase.getReference("$depoNo/$busType/$scheduleNo/")
        OutlinedButton(
            onClick = {
                val data = StringBuffer()
                //myRef.child("FP").child("4").child("1").get()
                myRef.get()
                    .addOnSuccessListener { dataSnapshot ->
                        //if (dataSnapshot.exists())
                        if (dataSnapshot != null) {
                            dataSnapshot.children.forEach { childSnapshot ->
                                // result=childSnapshot.child("destinationPlace").value.toString()
                                //data.append("Trip No" + childSnapshot.child("tripNo").value)
//                                data.append("\nDestination" + childSnapshot.child("destinationPlace").getValue())
//                                data.append("\nvia" + childSnapshot.child("via").value)
//                                data.append("\nStart Place" + childSnapshot.child("startPlace").value)
//
//                                ti+=1
//
                                //
                                data.append("\n ${ti + 1}\t  ${childSnapshot.child("startPlace").value}")
                                data.append("\t     via: ${childSnapshot.child("via").value}")
                                data.append("\t      ${childSnapshot.child("destinationPlace").value}")
                                data.append("\t      ${childSnapshot.child("kilometer").value}")
                                ti += 1
                            }


                            result = data.toString()
                            ti = 0
                        }
                        else
                        {
                            Toast.makeText(context, "Record not found", Toast.LENGTH_SHORT).show()
                        }

                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Record not found", Toast.LENGTH_SHORT).show()
                    }
            }
        ) {
            Text("Display")
        }
        Text(text = result)
        ti = 0
        }
    }

