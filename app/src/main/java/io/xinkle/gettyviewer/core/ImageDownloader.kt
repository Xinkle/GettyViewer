package io.xinkle.gettyviewer.core

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import java.io.File.separator
import android.os.Environment.MEDIA_MOUNTED




class ImageDownloader private constructor(val memoryCache: BitmapMemoryLRUCache,
                                          val diskCache: BitmapDiskLRUCache){

}