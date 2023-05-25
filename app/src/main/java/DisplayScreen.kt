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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.abhilash.livedata.ui.theme.read.isValidText
import com.google.firebase.database.FirebaseDatabase


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
    val myList= remember{mutableStateListOf<OriginalData>()}
    var result by rememberSaveable { mutableStateOf("") }

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
                contentDescription = "Arrow"
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
                        myRef.get()
                            .addOnSuccessListener { dataSnapshot ->
                                if (dataSnapshot != null) {
                                    dataSnapshot.children.forEach { childSnapshot ->
//                                        myList.add(
//                                            OriginalData(
//                                                bustype ="",
//                                                etmNo="",
//                                                departureTime = childSnapshot.child("departureTime").value.toString(),
//                                                startPlace = childSnapshot.child("startPlace").value.toString(),
//                                                via = childSnapshot.child("via").value as String,
//                                                destinationPlace = childSnapshot.child("destinationPlace").value as String,
//                                                arrivalTime = childSnapshot.child("arrivalTime").value as String,
//                                                kilometer=childSnapshot.child("kilometer").value as String
//                                            )
//                                        )
                                        data.append(childSnapshot.child("departureTime").value)
                                        ti += 1
                                        flag=1
                                    }
                                    ti = 0
                                } else {
                                    Toast.makeText(context, "Record not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "Record not found", Toast.LENGTH_SHORT).show()
                            }
                        result=data.toString()
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
            }
        var indx =0
        if(flag==1){
//            for  (item in myList) {
//                indx+=1
//                Text(
//                    text = "\n$indx  : ${item.departureTime} : ${item.startPlace}  " +
//                            "Via: ${item.via}  " +
//                            "  ${item.destinationPlace} " +
//                            " " +
//                            ": ${item.arrivalTime}   ${item.kilometer}",
//                    textAlign = TextAlign.Start
//                )
              Text(result)
                Divider()
            }
            

        else
        {
            flag=0
        }
    }
    }
//@Composable
//fun DisplayClassArrayElements(array: List<OriginalData>) {
//    LazyColumn {
//        items(array) { item ->
//            Text(
//                text = "Start Place: ${item.startPlace}\n" +
//                        "Via: ${item.via}\n" +
//                        "Destination Place: ${item.destinationPlace}\n" +
//                        "Departure Time: ${item.departureTime}\n" +
//                        "Arrival Time: ${item.arrivalTime}\n",
//                textAlign = TextAlign.Start
//            )
//        }
//    }
//}
