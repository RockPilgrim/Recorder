package my.rockpilgrim.recording.record

interface Recorder {

    fun startRecording()
    fun stopRecording()
    fun setRate(rate: Int)
}