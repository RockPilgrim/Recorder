package my.rockpilgrim.recorder.record

import android.media.MediaRecorder
import my.rockpilgrim.recorder.Database
import my.rockpilgrim.recorder.Model
import my.rockpilgrim.recorder.RecorderSettings
import my.rockpilgrim.recorder.utils.TimeLine
import java.io.IOException

class RecordPresenter(val recordUI: ConnectToUI) : Recorder {


    private var isRecording = false
    private var mediaRecorder: MediaRecorder? = null
    private var database: Database = Model()
    private val recordSettings: RecorderSettings
    private val timeLine: TimeLine = TimeLine()

    init {
        recordSettings = RecorderSettings
        timeLine.setTimeListener(recordUI)
    }

    override fun startRecording() {
        if (!isRecording) {
            try {
                initRecorder()
                mediaRecorder?.prepare()
                mediaRecorder?.start()
                isRecording = true
                recordUI.changeState(isRecording)
                recordUI.makeToast("start talking")
                timeLine.start()
            } catch (e: IllegalStateException) {
                recordUI.makeToast("Error")
                e.printStackTrace()
            } catch (e: IOException) {
                recordUI.makeToast("Error")
                e.printStackTrace()
            }
        }
    }

    override fun stopRecording() {
        if (isRecording) {
            mediaRecorder?.stop()
            mediaRecorder?.release()
            isRecording = false
            recordUI.changeState(isRecording)
            recordUI.makeToast("stop talking")
            timeLine.stop()
        }
    }

    override fun setRate(rate: Int) {
        recordSettings.currentRate=rate
    }

    private fun initRecorder() {
        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setAudioSamplingRate(recordSettings.currentRate)
        mediaRecorder?.setOutputFile(database.getPath())
    }
}