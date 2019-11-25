package my.rockpilgrim.recording.tracklist

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import my.rockpilgrim.recording.Database
import my.rockpilgrim.recording.Model
import my.rockpilgrim.recording.record.ConnectToUI

class TracklistPresenter(val context: Context) : ClickCallback, Player {

    val TAG = "TracklistPresenter"
    private var database: Database
    private lateinit var player: MediaPlayer
    private var isPlaing: Boolean = false
    private var currentTrack = -1
    private var connector: ConnectToUI? = null

    init {
        database = Model()
    }

    override fun onClick(position: Int, connector: ConnectToUI) {
        if (isPlaing && currentTrack == position) {
            isPlaing = false
            stopPlaying()
        } else if (!isPlaing) {
            isPlaing = true
            currentTrack = position
            this.connector = connector
            startPlaying(position)
        }

        Log.i(TAG, "Track ${position + 1}, clicked")
    }

    override fun startPlaying(track: Int) {
        player = MediaPlayer.create(context, database.getTrack(track))
        player.start()
        connector?.changeState(isPlaing)
        player.setOnCompletionListener {
            Log.i(TAG, "OnCompletionListener")
            isPlaing = false
            connector?.changeState(isPlaing)
        }
    }

    override fun stopPlaying() {
        player.stop()
        player.reset()
        player.release()
        connector?.changeState(isPlaing)
    }

    fun getCount(): Int {
        return database.getListCount()
    }


}