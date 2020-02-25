package space.hahaha28.network.request

import android.util.Log
import okhttp3.*
import okhttp3.Request
import space.hahaha28.wordbook.network.NetworkCallback
import java.io.IOException

/**
 * 请求的基类
 * @Author: 等待云间月
 * @Date: 2020/2/18 18:40
 */
abstract class Request {
    companion object {
        const val GET = 0
        const val POST = 1
    }

    private val client = OkHttpClient()

    /**
     * 请求的 url
     */
    abstract fun url(): String

    /**
     * 当GET请求携带参数的时候，将参数以key=value的形式拼装到GET请求URL的后面，并且中间以?符号隔开。
     * @return 携带参数的URL请求地址。
     */
    private fun urlWithParam(): String {
        val params = params()
        if (params != null) {
            val keys = params.keys
            if (keys.isNotEmpty()) {
                val paramsBuilder = StringBuilder()
                var needAnd = false
                for (key in keys) {
                    if (needAnd) {
                        paramsBuilder.append("&")
                    }
                    paramsBuilder.append(key).append("=").append(params[key])
                    needAnd = true
                }
                return url() + "?" + paramsBuilder.toString()
            }
        }
        return url()
    }

    /**
     * 请求的参数
     * @return 以Map的形式返回参数
     */
    open fun params(): Map<String, String>? {
        return null
    }

    /**
     * 构建POST、PUT、DELETE请求的参数体。
     *
     * @return 组装参数后的FormBody。
     */
    private fun formBody(): FormBody {
        val builder = FormBody.Builder()
        val params = params()
        if (params != null) {
            val keys = params.keys
            if (keys.isNotEmpty()) {
                for (key in keys) {
                    val value = params[key]
                    if (value != null) {
                        builder.add(key, value)
                    }
                }
            }
        }
        return builder.build()
    }

    /**
     * 请求所用的方法
     */
    abstract fun method(): Int

    /**
     * 发送请求，获取回复
     * @param callback 请求成功的回调，data 为请求的数据
     * @param async 是否用异步的方式，默认为否
     */
    fun getResponse(async: Boolean = false, callback: (data: String) -> Unit) {
        val requestBuilder = Request.Builder()
        if (method() == GET && params() != null) {
            requestBuilder.url(urlWithParam())
            Log.e("MyDebug","url=${urlWithParam()}")
        } else {
            requestBuilder.url(url())
        }
        when (method()) {
            POST -> requestBuilder.post(formBody())
        }
        if (!async) {    // 使用同步方式发送请求
            try {
                val response = client.newCall(requestBuilder.build()).execute()
                if (!response.isSuccessful) {
                    NetworkCallback.onResponseFail(response.code, response.message)
                } else {
                    val body = response.body
                    callback(body?.string() ?: "")
                }
            } catch (e: IOException) {
                NetworkCallback.onNetworkError()
            }
        } else {
            // 使用异步方式发送请求
            Log.e("MyDebug","发送请求")
            client.newCall(requestBuilder.build()).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) {
                        NetworkCallback.onResponseFail(response.code, response.message)
                    } else {
                        val body = response.body
                        callback(body?.string() ?: "")
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("MyDebug","打印哪去了")
                    e.printStackTrace()
                    NetworkCallback.onNetworkError()
                }
            })
        }
    }

}