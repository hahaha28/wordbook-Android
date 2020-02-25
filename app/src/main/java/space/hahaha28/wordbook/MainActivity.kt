package space.hahaha28.wordbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import space.hahaha28.wordbook.moudle.DBUtil
import space.hahaha28.wordbook.moudle.Word
import space.hahaha28.wordbook.network.request.GetUpdateRequest

class MainActivity : BaseActivity() {

    private val dbUtil = DBUtil()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 点击获取更新时
     */
    fun onUpdateClick(v: View){
        GetUpdateRequest().getResponse(true){
            val wordList = gson.fromJson<List<Word>>(it,object:TypeToken<List<Word>>(){}.type)
            Log.e("MyDebug",wordList.toJson())
            dbUtil.saveWords(wordList)
        }
    }

    /**
     * 点击复习时
     */
    fun onReviewClick(v:View){
        val intent = Intent(this,ReviewActivity::class.java)
        startActivity(intent)
    }
}
