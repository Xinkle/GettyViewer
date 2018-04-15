package io.xinkle.gettyviewer.core

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageDownloaderTest {

    @Test
    fun getImageFromUrl() {
        Thread {
            val imgDownloader = ImageDownloader()
            val bitmap = imgDownloader.getImageFromUrl("http://www.gettyimagesgallery.com/Images/Thumbnails/1340/134050.jpg",
                    1000,1000)

            assertTrue(bitmap != null)
            println("Bitmap Height : ${bitmap!!.height}, Width : ${bitmap.width}")
        }.run()

    }
}