package com.abhilash.livedata.ui.theme.utility

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.CreateDocument
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun FareTableScreen(navController: NavController) {
    var flag by remember { mutableStateOf(0) }
    var flag2 by remember { mutableStateOf(0) }
    Surface(color = Color.White) {
        val scroll= rememberScrollState()
        Column{
            Surface(color = Color(0xFF484F7B)) {
                IconButton(onClick = {
                    navController.popBackStack()
                }, modifier = Modifier.fillMaxWidth())
                {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow",
                        tint = Color.White
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(600.dp), // Adjust the height as needed
                backgroundColor = Color.White,
                //shape=,
                elevation = 5.dp
            )
            {
    Column(modifier = Modifier.verticalScroll(scroll)) {
      Row {
        TextButton(onClick = { flag = 1 }) {
        Text("TVM-KTR-KTM_FP ", color = Color.Blue, fontSize = 20.sp) }
          TextButton(onClick = { flag2 = 2 })
          { Text("DOWNLOAD ", color = Color.Blue, fontSize = 20.sp) }
                } }
           if (flag == 1) {
             flag2 = 0
             Text("Fare Table Checking Stage")
              DisplayPdfFromGoogleCloud(pdfUrl = "https://drive.google.com/file/d/1EA9ZTK3dPQ4IeXAnx8j_yLle1UkHMKb2/view?usp=drive_link")
                }
                if (flag2 == 2) {
                    flag = 0
                    DownloadScreen()
                }
            }
        }


        }
    }

@Composable
fun DisplayPdfFromGoogleCloud(pdfUrl: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
            }
        },
        update = { webView ->
            webView.loadUrl(pdfUrl)
        }
    )
}

@Composable
fun DownloadScreen() {
    var downloadUrl by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(CreateDocument("jpg/png"))
    { uri -> if (uri != null) {
      coroutineScope.launch {
       downloadFile(context, downloadUrl, uri) }
        }
    }
    Column {
        TopAppBar(
            title = { Text("Download File", fontSize = 20.sp, color = Color.White) },
            backgroundColor = Color(0xFFB28042)
        )
     Surface(color = Color.White) {
         Column {
         var flag by remember { mutableStateOf(false) }
         downloadUrl =
             "https://drive.google.com/uc?export=download&id=1EA9ZTK3dPQ4IeXAnx8j_yLle1UkHMKb2"
         Button(
             onClick = {
                 flag = true
                 if (downloadUrl.isNotEmpty()) {
                     launcher.launch("document.jpg")
                 }
             },
             modifier = Modifier.padding(16.dp)
         ) {
             Text("Download", color = Color.White)
         }
         if (flag) Text("Download started...")
     }
        }
    }
}

private suspend fun downloadFile(context: Context, url: String, uri: Uri) {

    withContext(Dispatchers.IO) {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setAllowedOverRoaming(false)
            .setTitle("Downloading File")
            .setDescription("Downloading...")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"document.jpg")


        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = downloadManager.enqueue(request)

        var downloading = true
        while (downloading) {
            val query = DownloadManager.Query().setFilterById(downloadId)
            val cursor: Cursor = downloadManager.query(query)

            if (cursor.moveToFirst()) {

                when (cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))) {
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        val fileUri = cursor.getString(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI))
                        downloading = false
                        cursor.close()

                        if (fileUri != null) {
                           // openDownloadedPdf(context, Uri.parse(fileUri))
                        } else {
                            // Handle the case where fileUri is null even for a successful download
                            Log.e("Download", "File URI is null for a successful download.")
                        }
                    }
                    DownloadManager.STATUS_FAILED -> {
                        downloading = false
                        cursor.close()
                        // Handle the download failure here (e.g., show an error message)
                        Log.e("Download", "File download failed.")
                    }
                    else -> {
                        // Handle other statuses if needed
                    }
                }
            } else {
                // Handle the case where the cursor is empty
                downloading = false
                cursor.close()
                Log.e("Download", "Cursor is empty.")
            }
        }
    }
}




private fun openDownloadedPdf(context: Context, contentUri: Uri) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(contentUri, "application/pdf")
    intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Optional, add this if needed
    context.startActivity(intent)
}




private fun getRealPathFromURI(context: Context, uri: Uri): String {
    var result = ""
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val columnIdx = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            result = it.getString(columnIdx)
        }
    }
    return result
}
