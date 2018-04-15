package io.xinkle.gettyviewer.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.Environment.isExternalStorageRemovable
import com.jakewharton.disklrucache.DiskLruCache
import java.io.File


class BitmapDiskLRUCache(private val mDiskLRUCache: DiskLruCache) {
    companion object {
        private const val DISK_CACHE_SIZE = 1024 * 1024 * 30L // 30MB
        private const val DISK_CACHE_SUBDIR = "images"

        @Volatile
        private var INSTANCE: BitmapDiskLRUCache? = null

        fun getInstance(context: Context): BitmapDiskLRUCache =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: BitmapDiskLRUCache.buildCache(context.applicationContext)
                            .also { INSTANCE = it }
                }

        private fun buildCache(applicationContext: Context): BitmapDiskLRUCache {
            val cacheDir = getDiskCacheDir(applicationContext, DISK_CACHE_SUBDIR)
            val diskCache = DiskLruCache.open(cacheDir, 1, 1, DISK_CACHE_SIZE)

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

    /**
     * Return bitmap when key is available in Disk LRU Cache
     * if key doesn't exist, return null
     */
    fun getBitmap(key: String): Bitmap? {
        return BitmapFactory.decodeStream(mDiskLRUCache.get(
                key.replace(Regex("[^a-z0-9_-]{1,120}"), ""))?.getInputStream(0))
    }

    /**
     * Put bitmap to Disk LRU Cache
     */
    fun putBitmap(key: String, bitmap: Bitmap) {
        val editor = mDiskLRUCache.edit(key.replace(Regex("[^a-z0-9_-]{1,120}"), ""))
        val outStream = editor.newOutputStream(0)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        editor.commit()
    }
}