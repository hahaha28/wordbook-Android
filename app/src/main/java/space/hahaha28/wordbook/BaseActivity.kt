package space.hahaha28.wordbook

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zoe.example.kyswipeback.SwipeBackActivity

open class BaseActivity: SwipeBackActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalUtil.context = applicationContext
    }
}