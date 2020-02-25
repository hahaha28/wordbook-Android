package space.hahaha28.wordbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.hahaha28.wordbook.adapters.RapidStudyAdapter
import space.hahaha28.wordbook.moudle.DBUtil
import space.hahaha28.wordbook.moudle.Word

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
        wordList = dbUtil.getAllWords()
    }

}
