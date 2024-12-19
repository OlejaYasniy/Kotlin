package com.example.myapplication

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp() {
        var url by remember { mutableStateOf(TextFieldValue()) }
        var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = url,
                onValueChange = { url = it },
                label = { Text("Введите URL изображения") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    scope.launch {
                        val bitmap = downloadImage(url.text)
                        if (bitmap != null) {
                            imageBitmap = bitmap
                            saveImageToInternalStorage(bitmap)
                        } else {
                            Toast.makeText(this@MainActivity, "Ошибка загрузки изображения", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Загрузить и сохранить изображение")
            }

            imageBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Загруженное изображение",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(10) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = 4.dp
                    ) {
                        Text(
                            text = "Элемент #${index + 1}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }

    suspend fun downloadImage(url: String): Bitmap? {
        val loader = ImageLoader(this@MainActivity)
        val request = ImageRequest.Builder(this@MainActivity).data(url).build()
        val result = (loader.execute(request) as? SuccessResult)?.drawable
        return (result as? BitmapDrawable)?.bitmap
    }

    private suspend fun saveImageToInternalStorage(bitmap: Bitmap) {
        val file = File(filesDir, "image.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
        }
        withContext(Dispatchers.Main) {
            Toast.makeText(this@MainActivity, "Изображение сохранено", Toast.LENGTH_SHORT).show()
        }
    }
}
