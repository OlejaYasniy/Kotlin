package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageDownloaderApp()
        }
    }
}

@Composable
fun ImageDownloaderApp() {
    var url by remember { mutableStateOf("") }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isImageVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Поле для ввода URL
        TextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Введите ссылку на изображение") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка загрузки
        Button(
            onClick = {
                if (url.isNotEmpty()) {
                    // Вызов функции для загрузки изображения
                    downloadAndSaveImage(url) { bitmap ->
                        imageBitmap = bitmap
                        isImageVisible = bitmap != null
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Загрузить изображение")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Динамический список
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(1) { // Загружаем только одно изображение
                if (isImageVisible && imageBitmap != null) {
                    // Отображение изображения
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Gray)
                    ) {
                        Image(
                            bitmap = imageBitmap!!.asImageBitmap(),
                            contentDescription = "Downloaded Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
suspend fun downloadImage(url: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        val loader = ImageLoader(LocalContext.current)
        val request = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build()

        val result = (loader.execute(request) as? SuccessResult)?.drawable
        (result as? BitmapDrawable)?.bitmap
    }
}

@Composable
fun downloadAndSaveImage(url: String, onResult: (Bitmap?) -> Unit) {
    // Функция загрузки изображения с использованием корутин
    CoroutineScope(Dispatchers.Main).launch {
        val bitmap = downloadImage(url)
        onResult(bitmap)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageDownloaderApp()
}
