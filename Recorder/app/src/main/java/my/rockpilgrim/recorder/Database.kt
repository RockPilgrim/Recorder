package my.rockpilgrim.recorder

import android.net.Uri

interface Database {

    fun getPath(): String?
    fun getTrack(number: Int): Uri?
    fun getListCount(): Int
}