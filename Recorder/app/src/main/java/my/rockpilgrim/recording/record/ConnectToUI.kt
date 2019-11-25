package my.rockpilgrim.recording.record

interface ConnectToUI {
    fun makeToast(line: String)
    fun changeState(isRecording: Boolean)
}