package my.rockpilgrim.recorder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService


object RecorderSettings {

    val TAG = "RecorderSettings"
    var currentRate: Int = 16000
    var rates: ArrayList<Int> = ArrayList()
    var audioManager:AudioManager?=null

    init {
        /*val audioManager =
            getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val rate = audioManager.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE)*/
        for (rate in arrayOf(8000, 11025, 16000, 22050,32000, 44100, 48000,96000,192000)) {
            if (checkRate(rate)) {
                Log.i(TAG, "Supported rate: $rate")
                val d = if(rates.lastIndex == -1) 0 else rates.lastIndex
                rates.add(rate)
            }
        }
    }

    fun setContext(context: Context) {
        val audioManager:AudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val intent= Intent()
        Log.i(TAG, "Bluetooth doesn't connected")

        if (AudioManager.SCO_AUDIO_STATE_CONNECTED == intent.getIntExtra(
                AudioManager.EXTRA_SCO_AUDIO_STATE,
                -1)) {
            Log.i(TAG, "Bluetooth")
        }
        if (audioManager.isBluetoothScoOn) {
            Log.i(TAG, "Bluetooth 2")
        }
        if (audioManager.isBluetoothScoAvailableOffCall) {
            Log.i(TAG, "Bluetooth 3")
            audioManager.startBluetoothSco()
        }
        audioManager.stopBluetoothSco()
    }

    fun isBluetoothConnected(context: Context): Boolean {
        audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager?.startBluetoothSco()
        val isExist:Boolean=audioManager?.isBluetoothScoOn!!
        audioManager?.stopBluetoothSco()
        return isExist
    }

    fun connectBluetooth() {
        audioManager?.startBluetoothSco()
    }
    fun disconnectBluetooth() {
        audioManager?.stopBluetoothSco()
    }

    private fun checkRate(rate: Int): Boolean {
        var recorder: AudioRecord? = null
        try {
            val bufferSize = AudioRecord.getMinBufferSize(
                rate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT
            )
            recorder = AudioRecord(
                MediaRecorder.AudioSource.DEFAULT,
                rate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize
            )
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "Rate: $rate does not supported")
            return false
        } finally {
            if (recorder != null) {
                recorder.release()
            }
        }
        return true
    }
}