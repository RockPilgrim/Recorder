package my.rockpilgrim.recorder.record

interface ConnectToUI {
    fun makeToast(line: String)
    fun changeState(isRecording: Boolean)
}