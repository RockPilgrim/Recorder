package my.rockpilgrim.recorder.settings

interface RatesHolder {
    fun getRatesSize(): Int
    fun getRate(position: Int): Int
    fun getCurrentRate(): Int
    fun isCurrentRate(ratePosition: Int): Boolean
    fun changeRate(currentRateIndex: Int)
}