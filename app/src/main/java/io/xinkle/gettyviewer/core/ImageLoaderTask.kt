package io.xinkle.gettyviewer.core

import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask
import android.widget.ImageView
import java.lang.ref.WeakReference

class ImageLoaderTask(mContext: Context, imageView: ImageView)
    : AsyncTask<String, Void, Void>(){
    private val mMemoryCache = BitmapMemoryLRUCache
    private val mDiskCache = BitmapDiskLRUCache.getInstance(mContext.applicationContext)
    private val mWeakImageView: WeakReference<ImageView> = WeakReference(imageView)
    override fun doInBackground(vararg url: String): Void? {
        val bitmap = getImageFromURL(url[0],
                mWeakImageView.get()!!.width,
                mWeakImageView.get()!!.height)

        mWeakImageView.get()!!.setImageBitmap(bitmap)

        return null
    }


    private fun getImageFromURL(urlString: String, width: Int, height: Int): Bitmap? {
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