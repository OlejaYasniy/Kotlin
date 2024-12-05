package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var urlInput: EditText
    private lateinit var button: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlInput = findViewById(R.id.urlInput)
        button = findViewById(R.id.button)
        imageView = findViewById(R.id.imageView)

        button.setOnClickListener {
            val url = urlInput.text.toString()
            if (url.isNotEmpty()) {
                downloadAndSaveImage(url)
            }
        }
    }

    private fun downloadAndSaveImage(url: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = downloadImage(url)
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
                imageView.visibility = ImageView.VISIBLE
                saveImageToInternalStorage(bitmap)
            } else {
                Toast.makeText(this@MainActivity, "Ошибка загрузки изображения", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun downloadImage(url: String): Bitmap? = withContext(Dispatchers.IO) {
        val loader = ImageLoader(this@MainActivity)
        val request = ImageRequest.Builder(this@MainActivity).data(url).build()
        val result = (loader.execute(request) as? SuccessResult)?.drawable
        (result as BitmapDrawable).bitmap
    }

    suspend fun saveImageToInternalStorage(bitmap: Bitmap) = withContext(Dispatchers.IO) {

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
