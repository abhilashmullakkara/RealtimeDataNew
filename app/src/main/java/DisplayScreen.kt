import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.read.isValidText
import com.abhilash.livedata.ui.theme.userdatabase.CircularLoadingIndicator
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@Composable
fun  DisplayScreen(navController: NavController){

    val context = LocalContext.current
    var depoNo by rememberSaveable { mutableStateOf("") }
    var scheduleNo by rememberSaveable { mutableStateOf("") }
    var busType by rememberSaveable { mutableStateOf("") }
    var ti by rememberSaveable { mutableStateOf(0) }
    var flag by rememberSaveable { mutableStateOf(0) }
    val dataBase = FirebaseDatabase.getInstance()
    val scroll= rememberScrollState()
    var result by rememberSaveable { mutableStateOf("") }
    var etmresult by rememberSaveable { mutableStateOf("") }
    var kilomts by rememberSaveable { mutableStateOf("") }
Surface(color = Color(0xFF071715)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(10.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Arrow",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = depoNo,
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        depoNo = textFieldValue.text
                    }
                },
                label = { Text("Depo NO") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = scheduleNo,
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        scheduleNo = textFieldValue.text
                    }
                },
                label = { Text("Schedule NO") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = busType,
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        busType = textFieldValue.text
                    }
                },
                label = { Text("Type(FP,Ord,JNT)") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(

                onClick = {
                    if (depoNo.isNotBlank() && busType.isNotBlank() && scheduleNo.isNotBlank()){
                        val myRef = dataBase.getReference("$depoNo/$busType/$scheduleNo/")
                        val data = StringBuffer()
                        val etmKmr=StringBuffer()
                        val kilo=StringBuffer()
                        myRef.get()
                            .addOnSuccessListener { dataSnapshot ->
//                                data.append("\nNo  Time   From   Via   To   Arr:Time    Kmrs\n")
//                                data.append("  ......................................\n")
                                if (dataSnapshot != null) {
                                    dataSnapshot.children.forEach { childSnapshot ->

                                        data.append("\n"+ (ti+1).toString())
                                        data.append("   "+childSnapshot.child("departureTime").value)
                                        data.append("    "+childSnapshot.child("startPlace").value)
                                        data.append("    "+childSnapshot.child("via").value)
                                        data.append("    "+childSnapshot.child("destinationPlace").value)
                                        data.append("     "+childSnapshot.child("arrivalTime").value)
                                        data.append("     "+childSnapshot.child("kilometer").value)

                                        kilo.append(","+childSnapshot.child("kilometer").value)
                                        etmKmr.append(""+childSnapshot.child("etmNo").value+",")
                                        ti += 1
                                        flag=1
                                    }
                                    ti = 0
                                    result=data.toString()
                                    etmresult=etmKmr.toString()
                                    kilomts=kilo.toString()
                                } else { Toast.makeText(context, "Record not found", Toast.LENGTH_SHORT).show() }
                            }
                            .addOnFailureListener { Toast.makeText(context, "Record not found", Toast.LENGTH_SHORT).show() }
                    }
                    else
                    {
                        Toast.makeText(context, "Input data first", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Display")
            }
            Text(text = "Check Internet Connection", color = Color.LightGray)
        }
        if(flag==1){
            var isLoading by rememberSaveable{
                mutableStateOf(true) }

            LaunchedEffect(isLoading) {
                if (isLoading) {
                    withContext(Dispatchers.Main) {
                        delay(1400)
                        isLoading = false
                    }
                }
            }

            CircularLoadingIndicator(isLoading)
            Text(" \nNo   Time  From   Via    To    Arr.Time   Kmrs\n",color= Color.White)
            Divider(color = Color.Red, thickness = 4.dp)
            Text(result, color = Color.White)
            val number=kilomts.split(",")
            val sum = number.sumOf { it.trim().toDoubleOrNull() ?: 0.00 }
            Text("\nTotal Kilometers: $sum", color = Color.White)

            Divider(color = Color.Red)
            Text("\nETM Root Numbers: $etmresult", color = Color.White)
            Divider(color = Color.Red, thickness = 2.dp)
            Divider(color = Color.Green, thickness = 3.dp)
        }
        else
        {
            flag=0
          //MyScreen()
        }
    }
}

    }
