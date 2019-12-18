package my.rockpilgrim.recorder.tracklist

import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.track_item.view.*
import my.rockpilgrim.recorder.R

class TracklistHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ConnectToList{

    fun bind(position: Int, clickCallback: ClickCallback?) {
        itemView.trackNameTextView.text = clickCallback?.getTrackName(position)
//        itemView.trackNameTextView.text = "Track #${position + 1}"
        itemView.playButton.setOnClickListener {
            clickCallback?.onClick(position, this)
        }
        itemView.trackSettingsButton.setOnClickListener {v ->
            showMenu(v,clickCallback)
        }
    }

    private fun showMenu(v: View,clickCallback: ClickCallback?) {
        val popupMenu = PopupMenu(itemView.context, v)
        popupMenu.inflate(R.menu.menu)
        popupMenu.setOnMenuItemClickListener { itemMenu: MenuItem? ->
            when (itemMenu?.itemId) {
                R.id.deleteTrackMenuButton -> {
                    clickCallback?.deleteTrack(adapterPosition)
                    deleteTrack()
                    true
                }
                R.id.shareTrackMenuButton -> {
                    true
                }
                else ->
                    false
            }
        }
        popupMenu.show()
    }

    private fun deleteTrack() {
        makeToast(itemView.trackNameTextView.text.toString())
    }

    override fun changeState(isPlay: Boolean) {
        if (isPlay) {
            itemView.playButton.setImageResource(R.drawable.ic_stop)
            makeToast("Play track #${adapterPosition + 1}")
        } else {
            itemView.playButton.setImageResource(R.drawable.ic_play)
            makeToast("Track stopped")
        }
    }

    override fun makeToast(line: String) {
        Toast.makeText(itemView.context, line, Toast.LENGTH_SHORT).show()
    }
}