package io.xinkle.gettyviewer.core

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BitmapDiskLRUCacheTest {
    private lateinit var mContext: Context
    private lateinit var diskCache: BitmapDiskLRUCache

    @Before
    fun setUp() {
        mContext = InstrumentationRegistry.getTargetContext()
        diskCache = BitmapDiskLRUCache.getInstance(mContext)
    }



    @Test
    fun put_bitmap_and_get_bitmap_successful() {
        // TODO: Must be replace local bitmap
        val urlString = "http://www.gettyimagesgallery.com/Images/Thumbnails/1340/134050.jpg"
        val bitmap = ImageDownloader.getImageFromUrl(urlString)

        diskCache.putBitmap(urlString, bitmap!!)

        val cachedBitmap = diskCache.getBitmap(urlString)

        assertTrue(cachedBitmap != null)
        Log.d("UnitTest", "Bitmap Height : ${cachedBitmap!!.height}, Width : ${cachedBitmap.width}")
    }

    @Test
    fun get_null_when_key_doesnt_available() {
        val nullBitmap = diskCache.getBitmap("nullkey")
        assertTrue(nullBitmap == null)
    }
}