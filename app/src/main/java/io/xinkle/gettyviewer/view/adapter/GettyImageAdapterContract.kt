package io.xinkle.gettyviewer.view.adapter

import io.xinkle.gettyviewer.model.GettyImage

interface GettyImageAdapterContract {
    interface View {
        fun reload()
    }

    interface Model {
        fun add(item : GettyImage)
        fun clear()
    }
}