package my.rockpilgrim.recorder.tracklist

import my.rockpilgrim.recorder.record.ConnectToUI

interface ClickCallback {
    fun onClick(position: Int, connector: ConnectToList)
    fun deleteTrack(number: Int)

    fun getTrackName(number: Int): String

}