package my.rockpilgrim.recorder

import android.net.Uri

interface Database {

    fun getNextTrackPath(): String?
    fun getTrack(number: Int): Uri?
    fun getListCount(): Int

    fun deleteTrack(number: Int)
    fun getTrackName(number: Int): String
}