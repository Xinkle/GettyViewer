package io.xinkle.gettyviewer.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.xinkle.gettyviewer.R
import io.xinkle.gettyviewer.core.ImageLoaderTask
import io.xinkle.gettyviewer.model.GettyImage
import kotlinx.android.synthetic.main.item.view.*

class GettyImageHolder(private val context: Context, parent: ViewGroup?) :
        RecyclerView.ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false)) {

    fun bindView(item: GettyImage, position: Int) {
        ImageLoaderTask(context, itemView.getty_image).execute(item.url)
    }
}