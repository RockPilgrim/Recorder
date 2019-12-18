package my.rockpilgrim.recorder

import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException
import java.util.regex.Pattern

class Model : Database {

    val PACKAGE_PATH = "/myrecorder/"
    val TRACK_NAME = "Track #"
    val TRACK_FORMAT = ".mp3"
    private val TAG = "Model"

    private var output: String? = null
    private var dir: File =
        File(Environment.getExternalStorageDirectory().absolutePath + PACKAGE_PATH)

    private var maxNumber = 1


    init {
        Log.i(TAG, "init")
        try {
            val recorderDirectory =
                File(Environment.getExternalStorageDirectory().absolutePath + PACKAGE_PATH)
            recorderDirectory.mkdirs()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getTrackURI(number: Int): Uri? {
        Log.i(
            TAG, "getTrack ${dir.listFiles()[number].nameWithoutExtension}\n" +
                    Environment.getExternalStorageDirectory().absolutePath + PACKAGE_PATH + dir.listFiles()[number].name
        )
        return Uri.parse(Environment.getExternalStorageDirectory().absolutePath + PACKAGE_PATH + dir.listFiles()[number].name)
    }

    override fun getListCount(): Int {
        if (dir.exists()) {
            Log.i(TAG, "getListCount: Count = ${dir.listFiles().size}")
            return dir.listFiles().size
        } else {
            Log.i(TAG, "getListCount: dir not exists")
            return 0
        }
    }

    override fun getTrackName(number: Int): String {
        return dir.listFiles()[number].nameWithoutExtension
    }

    override fun getNextTrackPath(): String? {
        if (dir.exists()) {
            val count = getLastNumber()+1
            output =
                Environment.getExternalStorageDirectory().absolutePath + "${PACKAGE_PATH}${TRACK_NAME}" + count + TRACK_FORMAT
            Log.i(TAG, "getNextTrackPath: ${dir.listFiles().indexOf(File(output))}")
            return output
        }
        return null
    }

    private fun getLastNumber(): Int {
        if (!dir.listFiles().isEmpty()) {
            for (line in dir.listFiles()) {
                // TODO trouble if name not in standard
                val newLine = line.nameWithoutExtension.replace(TRACK_NAME, "")
                if (maxNumber <= newLine.toInt()) {
                    maxNumber=newLine.toInt()
                }
            }
            Log.i(TAG, "getLastNumber: MaxNumber = $maxNumber")
            return maxNumber
        } else {
            Log.i(TAG, "getLastNumber: dir is empty, MaxNumber = 1")
            return 1
        }
    }

    override fun deleteTrack(number: Int) {
        Log.i(TAG, "deleteTrack: ${dir.listFiles()[number].nameWithoutExtension} from position $number")
        dir.listFiles()[number].deleteRecursively()
        maxNumber = 1
        updateList(number)
    }

    private fun updateList(number: Int) {
/*        Log.i(TAG, dir.listFiles()[number + 1].nameWithoutExtension)
        for (i: Int in number until dir.listFiles().size - 1) {
            dir.listFiles().set(i, dir.listFiles()[i + 1])
            Log.i(TAG, "Number #$i")
        }
        Log.i(TAG, dir.listFiles()[number].nameWithoutExtension)*/
    }


}