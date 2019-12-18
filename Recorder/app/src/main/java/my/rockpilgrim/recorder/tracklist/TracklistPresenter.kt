package my.rockpilgrim.recorder.tracklist

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import my.rockpilgrim.recorder.Database
import my.rockpilgrim.recorder.Model

class TracklistPresenter(val context: Context) : ClickCallback, Player {

    val TAG = "TracklistPresenter"
    private var database: Database
    private lateinit var player: MediaPlayer
    private var isPlaing: Boolean = false
    private var currentTrack = -1
    private var connector: ConnectToList? = null

    init {
        database = Model()
    }

    override fun onClick(position: Int, connector: ConnectToList) {
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

    override fun deleteTrack(number: Int) {
        database.deleteTrack(number)
    }

    override fun startPlaying(track: Int) {
        player = MediaPlayer.create(context, database.getTrackURI(track))
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

    override fun getTrackName(number: Int): String {
        return database.getTrackName(number)
    }
}