package io.xinkle.gettyviewer.view.presenter

import io.xinkle.gettyviewer.view.adapter.GettyImageAdapterContract

interface ImageContract {
    interface View {
        fun onLoadSuccess()
    }

    interface Presenter {
        var view: View?

        var adapterModel: GettyImageAdapterContract.Model?
        var adapterView: GettyImageAdapterContract.View?

        fun getImage()
    }
}