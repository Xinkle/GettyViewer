package io.xinkle.gettyviewer.core

import android.graphics.BitmapFactory

fun BitmapFactory.Options.calculateTargetSize(targetWidth: Int, targetHeight: Int): BitmapFactory.Options {
    var inSampleSize = 1

    if (outHeight > targetHeight || outWidth > targetWidth) {
        val halfHeight = outHeight / 2
        val halfWidth = outWidth / 2

        while ((halfHeight / inSampleSize) >= targetHeight
                && (halfWidth / inSampleSize) >= targetWidth) {
            inSampleSize *= 2
        }
    }

    this.inSampleSize = inSampleSize

    return this
}