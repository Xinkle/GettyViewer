package io.xinkle.gettyviewer.core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


object ImageDownloader {
    fun getImageFromUrl(urlString: String, width: Int = -1, height: Int = -1): Bitmap? {
        val url = URL(urlString)

        return when {
            width >= 0 && height >= 0 -> {
                val bitmapOption = BitmapFactory.Options()
                bitmapOption.inJustDecodeBounds = true

                BitmapFactory.decodeStream(makeInputStreamFromURL(url), null, bitmapOption)

                bitmapOption.calculateTargetSize(width, height)
                bitmapOption.inJustDecodeBounds = false

                BitmapFactory.decodeStream(makeInputStreamFromURL(url), null, bitmapOption)
            }
            else -> BitmapFactory.decodeStream(makeInputStreamFromURL(url))
        }
    }

    private fun makeInputStreamFromURL(url: URL): InputStream {
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.apply {
            readTimeout = 5000
            connectTimeout = 7000
            requestMethod = "GET"
            doInput = true
            connect()
        }

        return connection.inputStream
    }
}