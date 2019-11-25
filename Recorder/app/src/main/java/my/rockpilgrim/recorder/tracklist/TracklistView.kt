package my.rockpilgrim.recorder.tracklist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.list_layout.*
import my.rockpilgrim.recorder.R

class TracklistView : AppCompatActivity() {


    private lateinit var myadapter: TracklistAdapter
    private lateinit var presenter: TracklistPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)

        presenter = TracklistPresenter(this)
        myadapter = TracklistAdapter(presenter)
        myadapter.count = presenter.getCount()
        recyclerView.adapter = myadapter
    }
}