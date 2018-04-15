package io.xinkle.gettyviewer.core

import org.jsoup.Jsoup
import android.graphics.Bitmap

object GettyParser {
    private const val PATH_TO_PARSE = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx"

    fun parse(): List<String> {
        val rawData = Jsoup.connect(PATH_TO_PARSE)
                .timeout(5000)
                .get()

        val imgs = rawData.select("img[class=picture]")

        val imageUrls = mutableListOf<String>()

        for (img in imgs) {
            imageUrls.add(img.attr("abs:src"))
        }

        return imageUrls
    }
}