package space.hahaha28.wordbook

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import com.google.gson.Gson
import space.hahaha28.wordbook.network.NetworkCallback
import java.lang.Exception

class GlobalUtil {
    companion object{
        const val URL_BASE = "http://123.57.133.29:8080"
        var USER = "android-Xiaomi-CC9"

        lateinit var context:Context
    }
}

fun Any.toJson():String{
    val gson = Gson()
    return gson.toJson(this)
}

/**
 * 英音在线播放
 */
fun String.play(){
    try {
        val myUrl = "http://dict.youdao.com/dictvoice?type=1&audio=${this}"
        val mediaPlayer = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(myUrl)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }catch (e:Exception){
        NetworkCallback.onNetworkError()
    }
}