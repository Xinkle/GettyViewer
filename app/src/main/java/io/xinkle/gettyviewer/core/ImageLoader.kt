package io.xinkle.gettyviewer.core

import android.content.Context
import android.graphics.Bitmap

class ImageLoader(mContext: Context) {
    private val mMemoryCache = BitmapMemoryLRUCache
    private val mDiskCache = BitmapDiskLRUCache.getInstance(mContext.applicationContext)

    fun getImageFromURL(urlString: String, width: Int, height: Int): Bitmap? {
        return mMemoryCache.getBitmap(urlString) ?:
        mDiskCache.getBitmap(urlString) ?:
        getAndCacheImageFromUrl(urlString, width, height)
    }

    private fun getAndCacheImageFromUrl(urlString: String, width: Int, height: Int): Bitmap?{
        val bitmap = ImageDownloader.getImageFromUrl(urlString, width, height)

        return when(bitmap) {
            null -> null
            else -> {
                mMemoryCache.putBitmap(urlString, bitmap)
                mDiskCache.putBitmap(urlString, bitmap)
                bitmap
            }
        }
    }
}