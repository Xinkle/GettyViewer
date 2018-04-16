package io.xinkle.gettyviewer.view.presenter

import android.os.AsyncTask
import android.util.Log
import io.xinkle.gettyviewer.core.GettyParser
import io.xinkle.gettyviewer.model.GettyImage
import io.xinkle.gettyviewer.view.adapter.GettyImageAdapterContract

class ImagePresenter : ImageContract.Presenter{
    companion object {
        private const val TAG = "ImagePresenter"
    }

    override var view: ImageContract.View? = null
    override var adapterModel: GettyImageAdapterContract.Model? = null
    override var adapterView: GettyImageAdapterContract.View? = null

    override fun getImage() {
        ParserTask(view!!, adapterModel!!, adapterView!!).execute()
    }

    class ParserTask(val view: ImageContract.View,
                     private val adapterModel: GettyImageAdapterContract.Model,
                     private val adapterView: GettyImageAdapterContract.View) :
            AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            val imageUrls = GettyParser.parse()

            for (url in imageUrls) {
                adapterModel.add(GettyImage(url))
            }

            Log.d(TAG, "Parsed URL Complete : ${imageUrls.size}")
            return null
        }

        override fun onPostExecute(result: Void?) {
            adapterView.reload()
            view.onLoadSuccess()
        }

    }
}