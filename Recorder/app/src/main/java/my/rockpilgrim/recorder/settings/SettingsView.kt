package my.rockpilgrim.recorder.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.list_layout.*
import my.rockpilgrim.recorder.Constants
import my.rockpilgrim.recorder.R

class SettingsView : AppCompatActivity() {

    private lateinit var ratesHolder: RatesHolder
    private lateinit var myadapter: SettingsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_layout)

        ratesHolder = SettingsPresenter()
        myadapter = SettingsListAdapter(ratesHolder)
        recyclerView.adapter = myadapter
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