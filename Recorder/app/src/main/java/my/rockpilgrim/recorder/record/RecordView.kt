package my.rockpilgrim.recorder.record

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.list_layout.*
import kotlinx.android.synthetic.main.record_layout.*
import kotlinx.android.synthetic.main.settings_layout.*
import kotlinx.android.synthetic.main.track_item.*
import my.rockpilgrim.recorder.Constants
import my.rockpilgrim.recorder.R
import my.rockpilgrim.recorder.settings.SettingsView
import my.rockpilgrim.recorder.tracklist.TracklistView

class RecordView : AppCompatActivity(), ConnectToUI {
    private val TAG = "RecordView"

    private lateinit var recorder: Recorder
    private lateinit var mySettings:SharedPreferences

    init {
        Log.i(TAG, "init")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.record_layout)
        Log.i(TAG, "onCreate")

        mySettings = getSharedPreferences(Constants().APP_PREFERENCES, Context.MODE_PRIVATE)
        recorder = RecordPresenter(this)
        initClickListener()
    }



    override fun onResume() {
        super.onResume()
        val rate=mySettings.getInt(Constants().APP_PREFERENCES_RATE, 16000)
        Log.i(TAG, "onResume: ${rate}")
        recorder.setRate(rate)
    }

    private fun initClickListener() {
        startRecordingButton.setOnClickListener {
            startRecording()
        }

        stopRecordingButton.setOnClickListener {
            stopRecording()
        }

        listButton.setOnClickListener {
            val intent = Intent(this, TracklistView().javaClass)
            startActivity(intent)
        }
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsView().javaClass)
            startActivity(intent)
        }
    }

    private fun startRecording() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(this, permissions, 0)
            Log.i(TAG, "No permission")
        } else {
            recorder.startRecording()
        }
    }


    private fun stopRecording() {
        recorder.stopRecording()
    }

    override fun changeState(isRecording: Boolean) {
        if (isRecording)
            stateImageView.setImageResource(R.drawable.ic_record_on)
        else
            stateImageView.setImageResource(R.drawable.ic_record_off)
    }

    override fun setTime(time: String) {
        timeTextView.setText(time)
    }

    override fun makeToast(line: String) {
        Toast.makeText(this, line, Toast.LENGTH_SHORT).show()
    }
}
