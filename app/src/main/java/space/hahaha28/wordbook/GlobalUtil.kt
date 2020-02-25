package space.hahaha28.wordbook

import android.content.Context
import com.google.gson.Gson

class GlobalUtil {
    companion object{
        const val URL_BASE = "http://123.57.133.29:8080"
        var USER = "android-meizu"

        lateinit var context:Context
    }
}

fun Any.toJson():String{
    val gson = Gson()
    return gson.toJson(this)
}