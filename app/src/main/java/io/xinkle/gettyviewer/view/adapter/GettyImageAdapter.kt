package io.xinkle.gettyviewer.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.xinkle.gettyviewer.model.GettyImage

class GettyImageAdapter(private val context: Context) :
        RecyclerView.Adapter<GettyImageHolder>(),
        GettyImageAdapterContract.View,
        GettyImageAdapterContract.Model {

    private val itemList: MutableList<GettyImage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GettyImageHolder {
        return GettyImageHolder(context, parent)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: GettyImageHolder?, position: Int) {
        holder?.bindView(itemList[position], position)
    }

    override fun onViewDetachedFromWindow(holder: GettyImageHolder?) {
        holder?.detachedView()
    }

    override fun reload() {
        notifyDataSetChanged()
    }

    override fun add(item: GettyImage) {
        itemList.add(item)
    }

    override fun clear() {
        itemList.clear()
    }




}