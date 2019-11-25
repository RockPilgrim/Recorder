package my.rockpilgrim.recording.settings

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.rockpilgrim.recording.R

class SettingsListAdapter(val ratesHolder: RatesHolder) :
    RecyclerView.Adapter<SettingsListHolder>(), CheckGroup {

    val TAG = "SettingsListAdapter"
    var current: SettingsListHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsListHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.rate_item, parent, false)
        return SettingsListHolder(root, ratesHolder)
    }

    override fun onBindViewHolder(holder: SettingsListHolder, position: Int) {
        holder.bind(position,this)
        if (ratesHolder.isCurrentRate(position)) {
            holder.changeCheck(true)
            current = holder
        }
    }

    override fun getItemCount(): Int {
        return ratesHolder.getRatesSize()
    }

    override fun onChecked(current: SettingsListHolder) {
        Log.i(TAG,"onChecked")
        this.current?.changeCheck(false)
        this.current = current
        val position=current.adapterPosition
        ratesHolder.changeRate(position)
    }
}