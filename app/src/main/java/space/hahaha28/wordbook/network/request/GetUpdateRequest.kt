package space.hahaha28.wordbook.network.request

import space.hahaha28.network.request.Request
import space.hahaha28.wordbook.GlobalUtil

class GetUpdateRequest:Request() {
    override fun url(): String {
        return "${GlobalUtil.URL_BASE}/get_update"
    }

    override fun params(): Map<String, String>? {
        return mapOf("user" to GlobalUtil.USER)
    }

    override fun method(): Int {
        return GET
    }
}