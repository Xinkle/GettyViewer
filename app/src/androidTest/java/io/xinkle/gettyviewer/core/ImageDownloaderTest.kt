package io.xinkle.gettyviewer.core

import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageDownloaderTest {

    @Test
    fun get_image_from_url_successful() {
        val imgDownloader = ImageDownloader()
        val bitmap = imgDownloader.getImageFromUrl("http://www.gettyimagesgallery.com/Images/Thumbnails/1340/134050.jpg")

        assertTrue(bitmap != null)
        Log.d("UnitTest", "Bitmap Height : ${bitmap!!.height}, Width : ${bitmap.width}")
    }
}