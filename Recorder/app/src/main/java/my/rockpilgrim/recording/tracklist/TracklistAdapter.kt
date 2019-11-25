package my.rockpilgrim.recording.tracklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.rockpilgrim.recording.R

class TracklistAdapter(var clickCallback: ClickCallback?) :
    RecyclerView.Adapter<TracklistHolder>() {

    var count: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracklistHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.track_item, parent, false)
        return TracklistHolder(root)
    }

    override fun onBindViewHolder(holder: TracklistHolder, position: Int) {
        holder.bind(position, clickCallback)
    }

    override fun getItemCount(): Int {
        return count
    }

}
