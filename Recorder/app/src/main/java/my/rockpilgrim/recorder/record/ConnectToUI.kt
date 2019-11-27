package my.rockpilgrim.recorder.record

import my.rockpilgrim.recorder.utils.TimeListener

interface ConnectToUI : TimeListener {
    fun makeToast(line: String)
    fun changeState(isRecording: Boolean)
}