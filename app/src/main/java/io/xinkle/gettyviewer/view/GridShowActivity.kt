package io.xinkle.gettyviewer.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.xinkle.gettyviewer.R
import io.xinkle.gettyviewer.view.adapter.GettyImageAdapter
import io.xinkle.gettyviewer.view.presenter.ImageContract
import io.xinkle.gettyviewer.view.presenter.ImagePresenter
import kotlinx.android.synthetic.main.activity_gridshow.*

class GridShowActivity : AppCompatActivity(), ImageContract.View {
    private var imageAdapter: GettyImageAdapter? = null
    private var presenter: ImageContract.Presenter? = null

    override fun onLoadSuccess() {
        Toast.makeText(this, "Load Success", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gridshow)

        presenter = ImagePresenter()
        presenter?.view = this

        imageAdapter = GettyImageAdapter(this)

        presenter?.adapterModel = imageAdapter
        presenter?.adapterView = imageAdapter
        grid_image_view.adapter = imageAdapter

        (presenter as ImagePresenter).getImage()
    }
}
