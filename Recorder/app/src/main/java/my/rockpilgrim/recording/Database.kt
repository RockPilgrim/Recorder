package my.rockpilgrim.recording

import android.net.Uri

interface Database {

    fun getPath(): String?
    fun getTrack(number: Int): Uri?
    fun getListCount(): Int
}