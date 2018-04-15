package io.xinkle.gettyviewer

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.xinkle.gettyviewer.core.ImageDownloader

class GridShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gridshow)

        object: AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg p0: Unit?): Unit {
                val imgDownloader = ImageDownloader()
                val bitmap = imgDownloader.getImageFromUrl("http://www.gettyimagesgallery.com/Images/Thumbnails/1340/134050.jpg",
                        1000,1000)

                Log.d("GETTY_VIEWER","Bitmap Height : ${bitmap!!.height}, Width : ${bitmap.width}")
            }

        }.execute()
    }
}
