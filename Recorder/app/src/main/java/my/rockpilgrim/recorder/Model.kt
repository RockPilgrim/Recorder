package my.rockpilgrim.recorder

import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.IOException

class Model : Database {

    val packagePath = "/myrecorder/"
    private var output: String? = null
    private val dir: File =
        File(Environment.getExternalStorageDirectory().absolutePath + packagePath)


    init {
        try {
            // create a File object for the parent directory
            val recorderDirectory =
                File(Environment.getExternalStorageDirectory().absolutePath + packagePath)
            // have the object build the directory structure, if needed.
            recorderDirectory.mkdirs()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getPath(): String? {
        if (dir.exists()) {
            val count = dir.listFiles().size
            output =
                Environment.getExternalStorageDirectory().absolutePath + "${packagePath}recording" + count + ".mp3"
        }
        return output
    }

    override fun getTrack(number: Int): Uri? {
        return Uri.parse(Environment.getExternalStorageDirectory().absolutePath + "${packagePath}recording" + number + ".mp3")
    }

    override fun getListCount(): Int {
        if (dir.exists()) {
            return dir.listFiles().size
        } else {
            return 0
        }
    }
}