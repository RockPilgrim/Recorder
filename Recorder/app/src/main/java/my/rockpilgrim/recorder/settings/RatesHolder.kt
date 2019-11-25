package my.rockpilgrim.recorder.settings

import android.content.Context

interface RatesHolder {
    fun getRatesSize(): Int
    fun getRate(position: Int): Int
    fun getCurrentRate(): Int
    fun isCurrentRate(ratePosition: Int): Boolean
    fun changeRate(currentRateIndex: Int)

    fun isBluetoothConnected(context: Context): Boolean
    fun connectBluetooth()
    fun disconnectBluetooth()
}