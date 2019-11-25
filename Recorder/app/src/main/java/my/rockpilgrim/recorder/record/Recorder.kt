package my.rockpilgrim.recorder.record

interface Recorder {

    fun startRecording()
    fun stopRecording()
    fun setRate(rate: Int)
}