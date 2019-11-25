package my.rockpilgrim.recorder.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.list_layout.*
import kotlinx.android.synthetic.main.list_layout.recyclerView
import kotlinx.android.synthetic.main.settings_layout.*
import my.rockpilgrim.recorder.Constants
import my.rockpilgrim.recorder.R
import my.rockpilgrim.recorder.RecorderSettings

class SettingsView : AppCompatActivity() {

    private lateinit var ratesHolder: RatesHolder
    private lateinit var myadapter: SettingsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_layout)

        ratesHolder = SettingsPresenter()
        myadapter = SettingsListAdapter(ratesHolder)
        recyclerView.adapter = myadapter

        initCheckBox()
    }

    fun initCheckBox() {
        if (ratesHolder.isBluetoothConnected(this)) {
            bluetoothCheckBox.visibility = View.VISIBLE
        }else
            bluetoothCheckBox.visibility = View.GONE
        bluetoothCheckBox
            . setOnCheckedChangeListener (CompoundButton.OnCheckedChangeListener()
            { buttonView, isChecked ->
                if (isChecked) {
                    ratesHolder.connectBluetooth()
                } else {
                    ratesHolder.disconnectBluetooth()
                }
            })
    }

    override fun onPause() {
        super.onPause()
        Log.i("SettingsView","onPause")
        val editor :SharedPreferences.Editor=getSharedPreferences(Constants().APP_PREFERENCES,
            Context.MODE_PRIVATE).edit()
        editor.putInt(Constants().APP_PREFERENCES_RATE,ratesHolder.getCurrentRate())
        editor.apply()
    }
}