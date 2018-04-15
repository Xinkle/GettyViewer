package io.xinkle.gettyviewer.core

import android.graphics.Bitmap
import android.util.LruCache

object BitmapMemoryLRUCache {
    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt() // Kilobytes
    private val cacheSize = maxMemory / 8 // Use 1/8

    private val mMemoryCache = object: LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String?, bitmap: Bitmap?): Int {
            return bitmap!!.byteCount / 1024 // Kilobytes
        }
    }

    fun putBitmap(key: String, bitmap: Bitmap) {
        val filteredKey = key.replace(Regex("[^a-z0-9_-]{1,120}"), "")
        if (getBitmap(filteredKey) == null) {
            mMemoryCache.put(filteredKey, bitmap)
        }
    }

    fun getBitmap(key: String): Bitmap? {
        val filteredKey = key.replace(Regex("[^a-z0-9_-]{1,120}"), "")
        return mMemoryCache.get(filteredKey)
    }

}