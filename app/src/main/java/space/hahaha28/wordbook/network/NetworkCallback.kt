package space.hahaha28.wordbook.network

import android.util.Log

/**
 * 所有的网络请求都会回调这个类的方法
 */
class NetworkCallback {
    companion object{

        /**
         * 网络请求，状态码异常的回调
         * @param code 状态码
         * @param msg msg
         */
        fun onResponseFail(code:Int,msg:String){
//            showln("网络错误！状态码:$code,$msg")
            Log.e("MyDebug","服务器返回错误！")
        }

        /**
         * 网络错误的回调
         */
        fun onNetworkError(){
//            showln("网络错误！请检查是否有网络。")
            Log.e("MyDebug","网络错误！")
        }
    }
}