package space.hahaha28.wordbook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.hahaha28.wordbook.adapters.RapidStudyAdapter
import space.hahaha28.wordbook.moudle.DBUtil
import space.hahaha28.wordbook.moudle.Word
import java.io.Serializable

class ReviewActivity : BaseActivity() {

    lateinit var recyclerView:RecyclerView
    lateinit var wordList:List<Word>
    private val dbUtil = DBUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        recyclerView = findViewById(R.id.recyclerView)
        getData()
        showData()
    }

    private fun showData(){
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = RapidStudyAdapter(wordList)
        adapter.onLongClickListener = {
            ShowWordActivity.startActivity(this,it)
        }
        recyclerView.adapter = adapter
    }

    private fun getData(){
        wordList = intent.getSerializableExtra(DATA_KEY) as List<Word>
    }

    companion object{
        const val DATA_KEY = "words"

        fun startActivity(activity:Activity,wordList:List<Word>){
            val intent = Intent(activity,ReviewActivity::class.java)
            val dataList = arrayListOf<Word>()
            dataList.addAll(wordList)
            intent.putExtra(DATA_KEY,dataList as Serializable)
            activity.startActivity(intent)
        }
    }

}
