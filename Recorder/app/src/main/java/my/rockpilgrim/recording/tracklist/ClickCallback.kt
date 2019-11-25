package my.rockpilgrim.recording.tracklist

import my.rockpilgrim.recording.record.ConnectToUI

interface ClickCallback {
    fun onClick(position: Int, connector: ConnectToUI)
}