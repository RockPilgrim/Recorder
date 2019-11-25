package my.rockpilgrim.recorder.settings

import android.view.View
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rate_item.view.*

class SettingsListHolder(itemView: View, val ratesHolder: RatesHolder) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(position: Int, checkGroup: CheckGroup) {
        val hertz = ratesHolder.getRate(position)
        itemView.radioButton.setText("${hertz} Hz")

        itemView.radioButton.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            checkGroup.onChecked(this)
        })
    }

    fun changeCheck(state:Boolean) {
        itemView.radioButton.isChecked = state
    }
}