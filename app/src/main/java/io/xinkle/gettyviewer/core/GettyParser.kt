package io.xinkle.gettyviewer.core

import org.jsoup.Jsoup

object GettyParser {
    private const val PATH_TO_PARSE = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx"

    /**
     * Get all of photo src from getty image web page
     */
    fun parse(): List<String> {
        val rawData = Jsoup.connect(PATH_TO_PARSE)
                .timeout(5000)
                .get()

        val imgClasses = rawData.select("img[class=picture]")

        val imageUrls = mutableListOf<String>()

        for (img in imgClasses) {
            imageUrls.add(img.attr("abs:src"))
        }

        return imageUrls
    }
}