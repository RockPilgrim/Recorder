package my.rockpilgrim.recorder.settings

import android.util.Log
import my.rockpilgrim.recorder.RecorderSettings

class SettingsPresenter : RatesHolder {

    private var recordSettings: RecorderSettings
    val TAG = "SettingsPresenter"

    init {
        recordSettings = RecorderSettings
    }

    override fun getRatesSize(): Int {
        return recordSettings.rates.size
    }

    override fun getRate(position: Int): Int {
        return recordSettings.rates[position]
    }

    override fun getCurrentRate(): Int {
        return recordSettings.currentRate
    }

    override fun isCurrentRate(ratePosition:Int): Boolean {
        return recordSettings.currentRate == recordSettings.rates[ratePosition]
    }

    override fun changeRate(currentRateIndex: Int) {
        Log.i(TAG,"changeRate: ${recordSettings.currentRate}")
        recordSettings.currentRate = recordSettings.rates[currentRateIndex]
        Log.i(TAG,"changeRate: ${recordSettings.currentRate}")
    }
}