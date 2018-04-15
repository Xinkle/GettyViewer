package io.xinkle.gettyviewer.core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL


class ImageDownloader {
    fun getImageFromUrl(urlString: String, width: Int, height: Int): Bitmap? {
        val url = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.apply {
            readTimeout = 5000
            connectTimeout = 7000
            requestMethod = "GET"
            doInput = true
        }

        connection.connect()
        return BitmapFactory.decodeStream(connection.inputStream)
    }
}