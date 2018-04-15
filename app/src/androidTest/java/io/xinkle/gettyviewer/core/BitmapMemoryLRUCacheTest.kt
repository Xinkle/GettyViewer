package io.xinkle.gettyviewer.core

import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BitmapMemoryLRUCacheTest {
    lateinit var memoryCache: BitmapMemoryLRUCache

    @Before
    fun setUp() {
        memoryCache = BitmapMemoryLRUCache
    }

    @Test
    fun put_bitmap_and_get_bitmap_successful() {
        // TODO: Must be replace local bitmap
        val urlString = "http://www.gettyimagesgallery.com/Images/Thumbnails/1340/134050.jpg"
        val bitmap = ImageDownloader.getImageFromUrl(urlString)

        memoryCache.putBitmap(urlString, bitmap!!)

        val cachedBitmap = memoryCache.getBitmap(urlString)

        assertTrue(bitmap.height == cachedBitmap!!.height)
    }

    @Test
    fun get_null_when_key_doesnt_available() {
        val nullBitmap = memoryCache.getBitmap("nullkey")
        assertTrue(nullBitmap == null)
    }
}