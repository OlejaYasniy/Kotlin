package com.example.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import java.io.File
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java).also {
            it.onActivity { activity ->
                mainActivity = activity
            }
        }
    }

    @Test
    fun testDownloadImage() = runTest {
        val url = "https://funik.ru/wp-content/uploads/2018/10/17478da42271207e1d86.jpg"
        val bitmap = mainActivity.downloadImage(url)
        assertNotNull(bitmap)
    }

    @Test
    fun testSaveImageToInternalStorage() = runTest {
        val url = "https://funik.ru/wp-content/uploads/2018/10/17478da42271207e1d86.jpg"
        val bitmap = mainActivity.downloadImage(url)
        assertNotNull(bitmap)
        bitmap?.let {
            mainActivity.saveImageToInternalStorage(it)

            val savedFile = File(mainActivity.filesDir, "image.png")
            assertTrue(savedFile.exists())
        }
    }


}
