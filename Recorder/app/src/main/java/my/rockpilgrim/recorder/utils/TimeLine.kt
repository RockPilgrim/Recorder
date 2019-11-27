package my.rockpilgrim.recorder.utils

import java.util.*

class TimeLine {

    private var timeListener:TimeListener?=null
    private var timer: Timer? = null
    var currentTime = 0
    val TAG = "TimeLine"

    fun start() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                currentTime++
                setTime()
            }
        },1000,1000)
    }

    fun stop() {
        timer?.cancel()
        timer = null
        currentTime = 0
        timeListener?.setTime("0:00")
    }

    fun setTimeListener(timeListener: TimeListener) {
        this.timeListener = timeListener
    }

    fun setTime() {
        val minutes = currentTime / (60)
        val seconds = currentTime % 60
        val time = String.format("%d:%02d", minutes, seconds)
        timeListener?.setTime(time)
    }
}