package io.xinkle.gettyviewer.core

import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import io.xinkle.gettyviewer.R
import java.lang.ref.WeakReference

class ImageLoaderTask(mContext: Context, imageView: ImageView)
    : AsyncTask<String, Void, Bitmap>(){
    private val TAG = "ImageLoaderTask"
    private val mMemoryCache = BitmapMemoryLRUCache
    private val mDiskCache = BitmapDiskLRUCache.getInstance(mContext.applicationContext)
    private val mWeakImageView: WeakReference<ImageView> = WeakReference(imageView)

    var attached = true

    override fun onPreExecute() {
        mWeakImageView.get()!!.setImageResource(R.drawable.loading)
    }
    override fun doInBackground(vararg url: String): Bitmap? {
        return getImageFromURL(url[0],
                mWeakImageView.get()!!.width,
                mWeakImageView.get()!!.height)
    }

    override fun onPostExecute(resultBitmap: Bitmap) {
        if (attached) mWeakImageView.get()!!.setImageBitmap(resultBitmap)
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
                Log.d(TAG, "$urlString Loaded : ${bitmap.byteCount/1024}")
                bitmap
            }
        }
    }
}