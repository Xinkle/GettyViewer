package io.xinkle.gettyviewer.view.adapter

import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import io.xinkle.gettyviewer.R
import io.xinkle.gettyviewer.core.ImageLoaderTask
import io.xinkle.gettyviewer.model.GettyImage
import kotlinx.android.synthetic.main.item.view.*

class GettyImageHolder(private val context: Context, parent: ViewGroup?) :
        RecyclerView.ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false)) {
    companion object {
        private const val TAG = "GettyImageHolder"
    }

    private var task: ImageLoaderTask? = null

    fun bindView(item: GettyImage, position: Int) {
        when (task?.status) {
            AsyncTask.Status.PENDING, AsyncTask.Status.RUNNING -> {
                task!!.cancel(true)
            }
            else -> {}
        }
        task = ImageLoaderTask(context, itemView.getty_image)
        task!!.execute(item.url)
    }

    fun detachedView() {
//        if(task.status != AsyncTask.Status.FINISHED) {
//            task.attched = false
//            Log.d(TAG, "View detached Item : ${}")
//        }
//        Log.d(TAG, "View detached")
    }
}