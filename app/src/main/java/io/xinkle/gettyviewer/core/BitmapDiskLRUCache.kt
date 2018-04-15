package io.xinkle.gettyviewer.core

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.File.separator
import android.os.Environment.MEDIA_MOUNTED
import android.os.Environment.isExternalStorageRemovable
import com.jakewharton.disklrucache.DiskLruCache
import java.io.File


class BitmapDiskLRUCache(mDiskLRUCache: DiskLruCache) {
    companion object {
        private const val DISK_CACHE_SIZE = 1024 * 1024 * 10L // 10MB
        private const val DISK_CACHE_SUBDIR = "thumbnails"

        @Volatile
        private var INSTANCE: BitmapDiskLRUCache? = null

        fun getInstance(context: Context): BitmapDiskLRUCache =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: BitmapDiskLRUCache.buildCache(context.applicationContext)
                            .also { INSTANCE = it }
                }

        private fun buildCache(applicationContext: Context): BitmapDiskLRUCache {
            val cacheDir = getDiskCacheDir(applicationContext, DISK_CACHE_SUBDIR)
            val diskCache = DiskLruCache.open(cacheDir, 0, 100, DISK_CACHE_SIZE)

            return BitmapDiskLRUCache(diskCache)
        }

        // Creates a unique subdirectory of the designated app cache directory. Tries to use external
        // but if not mounted, falls back on internal storage.
        private fun getDiskCacheDir(context: Context, uniqueName: String): File {
            // Check if media is mounted or storage is built-in, if so, try and use external cache dir
            // otherwise use internal cache dir
            val cachePath = if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
                    || !isExternalStorageRemovable())
                context.externalCacheDir.path
            else
                context.cacheDir.path

            return File(cachePath + File.separator + uniqueName)
        }
    }

    fun getBitmap(key: String) {

    }

    fun putBitmap(key: String, bitmap: Bitmap) {

    }
}