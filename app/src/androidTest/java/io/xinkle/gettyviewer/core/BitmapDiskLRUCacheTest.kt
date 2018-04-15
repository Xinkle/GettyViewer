package io.xinkle.gettyviewer.core

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.jakewharton.disklrucache.DiskLruCache
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BitmapDiskLRUCacheTest {
    lateinit var mContext: Context
    lateinit var diskCache: BitmapDiskLRUCache

    @Before
    fun setUp() {
        mContext = InstrumentationRegistry.getTargetContext()
        diskCache = BitmapDiskLRUCache.getInstance(mContext)
    }


    @Test
    fun getBitmap() {
    }

    @Test
    fun putBitmap() {
    }
}