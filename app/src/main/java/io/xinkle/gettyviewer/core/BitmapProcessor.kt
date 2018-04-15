package io.xinkle.gettyviewer.core

import android.graphics.BitmapFactory

fun BitmapFactory.Options.calculateTargetSize(targetWidth: Int, targetHeight: Int): BitmapFactory.Options {
    var sampleSize = 1

    if (outHeight > targetHeight || outWidth > targetWidth) {
        val halfHeight = outHeight / 2
        val halfWidth = outWidth / 2

        while ((halfHeight / sampleSize) >= targetHeight
                && (halfWidth / sampleSize) >= targetWidth) {
            sampleSize *= 2
        }
    }

    this.inSampleSize = sampleSize

    return this
}