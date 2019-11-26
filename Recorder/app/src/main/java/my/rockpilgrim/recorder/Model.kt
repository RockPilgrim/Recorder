package my.rockpilgrim.recorder

import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException

class Model : Database {

    val packagePath = "/myrecorder/"
    val trackName = "record_"
    val trackFormat = ".mp3"

    private var output: String? = null
    val TAG = "Model"
    private val dir: File =
        File(Environment.getExternalStorageDirectory().absolutePath + packagePath)


    init {
        try {
            val recorderDirectory =
                File(Environment.getExternalStorageDirectory().absolutePath + packagePath)
            recorderDirectory.mkdirs()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getPath(): String? {
        if (dir.exists()) {
            val count = dir.listFiles().size
            output =
                Environment.getExternalStorageDirectory().absolutePath + "${packagePath}${trackName}" + count + trackFormat
        }
        return output
    }

    override fun getTrack(number: Int): Uri? {
        return Uri.parse(Environment.getExternalStorageDirectory().absolutePath + "${packagePath}${trackName}" + number + trackFormat)
    }

    override fun getListCount(): Int {
        if (dir.exists()) {
            Log.i(TAG,"getListCount dir: ${dir.listFiles().size}")
            return dir.listFiles().size
        } else {
            return 0
        }
    }
}