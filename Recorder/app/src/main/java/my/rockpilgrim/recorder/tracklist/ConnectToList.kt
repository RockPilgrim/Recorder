package my.rockpilgrim.recorder.tracklist


interface ConnectToList {
    fun makeToast(line: String)
    fun changeState(isRecording: Boolean)
}