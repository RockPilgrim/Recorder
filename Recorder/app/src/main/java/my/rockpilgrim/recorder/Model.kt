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

    private var output: String? = null
    private val TAG = "Model"
    private var dir: File =
        File(Environment.getExternalStorageDirectory().absolutePath + PACKAGE_PATH)
    private var maxNumber = 1
    private var list: ArrayList<File> = ArrayList()


    init {
        Log.i(TAG, "init")
        try {
            val recorderDirectory =
                File(Environment.getExternalStorageDirectory().absolutePath + PACKAGE_PATH)
            recorderDirectory.mkdirs()
            makeList()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun makeList() {
        list = ArrayList()
        for (track in dir.listFiles()) {
            list.add(track)
        }
    }

    override fun getNextTrackPath(): String? {
        if (dir.exists()) {
            val count = getLastNumber()
            output =
                Environment.getExternalStorageDirectory().absolutePath + "${PACKAGE_PATH}${TRACK_NAME}" + count + TRACK_FORMAT
            list.add(File(output))
        }
        return output
    }

    override fun getTrack(number: Int): Uri? {
        Log.i(TAG, "getTrack ${dir.listFiles()[number].nameWithoutExtension}\n" +
                Environment.getExternalStorageDirectory().absolutePath + PACKAGE_PATH + dir.listFiles()[number].name)
        return Uri.parse(Environment.getExternalStorageDirectory().absolutePath + PACKAGE_PATH + dir.listFiles()[number].name)
    }

    override fun getListCount(): Int {
        if (dir.exists()) {
            Log.i(TAG,"getListCount dir: ${dir.listFiles().size}")
            return dir.listFiles().size
        } else {
            return 0
        }
    }

    override fun getTrackName(number: Int): String {
        val line = dir.listFiles()[number].nameWithoutExtension
        val pattern=Pattern.compile("-?\\d+")
        val matcher = pattern.matcher(line)
        while (matcher.find()) {
            if (matcher.group().toInt() > maxNumber) {
                maxNumber=matcher.group().toInt()+1
            }
        }
//        Log.i(TAG, "Max number: $maxNumber")
        return line
    }

    private fun getLastNumber(): Int {
        Log.i(TAG,"Max1 $maxNumber")
        if (!dir.listFiles().isEmpty()) {
            val line = list.last().nameWithoutExtension
            val pattern=Pattern.compile("-?\\d+")
            val matcher = pattern.matcher(line)
            while (matcher.find()) {
                if (matcher.group().toInt() >= maxNumber) {
                    Log.i(TAG,"Max2 $maxNumber")
                    maxNumber=matcher.group().toInt()+1
                }
                Log.i(TAG, "getLastNumber: $maxNumber")
                return maxNumber
            }
        }
/*        if (!dir.listFiles().isEmpty()) {
            val line = dir.listFiles()[dir.listFiles().size-1].nameWithoutExtension
            val pattern=Pattern.compile("-?\\d+")
            val matcher = pattern.matcher(line)
            while (matcher.find()) {
                if (matcher.group().toInt() >= maxNumber) {
                    Log.i(TAG,"Max2 $maxNumber")
                    maxNumber=matcher.group().toInt()+1
                }
                Log.i(TAG, "getLastNumber: $maxNumber")
                return maxNumber
            }
        }*/
        return 1
    }

    override fun deleteTrack(number: Int) {
        dir.listFiles()[number].deleteRecursively()
        list.remove(list.get(number))
    }


}