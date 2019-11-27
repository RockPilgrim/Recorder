package my.rockpilgrim.recorder.tracklist

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.track_item.view.*
import my.rockpilgrim.recorder.R
import my.rockpilgrim.recorder.record.ConnectToUI

class TracklistHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ConnectToList {

    fun bind(position: Int, clickCallback: ClickCallback?) {
        itemView.trackNameTextView.text = "Track #${position + 1}"
        itemView.playButton.setOnClickListener {
            clickCallback?.onClick(position, this)
        }
    }

    override fun makeToast(line: String) {
        Toast.makeText(itemView.context, line, Toast.LENGTH_SHORT).show()
    }

    override fun changeState(isPlay: Boolean) {
        if (isPlay) {
            itemView.playButton.setImageResource(R.drawable.ic_stop)
            Toast.makeText(
                itemView.context,
                "Play track #${adapterPosition + 1}",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            itemView.playButton.setImageResource(R.drawable.ic_play)
            Toast.makeText(itemView.context, "Track stopped", Toast.LENGTH_SHORT).show()
        }
    }
}