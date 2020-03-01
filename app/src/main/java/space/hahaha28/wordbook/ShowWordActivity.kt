package space.hahaha28.wordbook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text
import space.hahaha28.wordbook.moudle.Word

class ShowWordActivity : BaseActivity() {

    private lateinit var word:Word
    private lateinit var wordTV:TextView
    private lateinit var soundMarkTV:TextView
    private lateinit var meanTV:TextView
    private lateinit var sentenceTV:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_word)

        getWord()
        wordTV = findViewById(R.id.word)
        soundMarkTV = findViewById(R.id.soundmark)
        meanTV = findViewById(R.id.mean)
        sentenceTV = findViewById(R.id.sentence)

        initUI()
    }

    private fun initUI(){
        wordTV.setText(word.word)
        soundMarkTV.text = "/${word.soundMark}/"
        meanTV.text = word.mean
        sentenceTV.text = word.sentence
    }

    private fun getWord(){
        val intent = intent
        word = intent.getSerializableExtra(DATA_KEY) as Word
    }

    /**
     * 播放按钮的点击事件
     */
    fun onPlayClick(v:View){
        word.word.trim().play()
    }

    /**
     * 句子的点击事件
     */
    fun onSentenceClick(v:View){
        word.sentence.trim().play()
    }

    companion object{
        const val DATA_KEY = "word"

        fun startActivity(activity:Activity,word: Word){
            val intent = Intent(activity,ShowWordActivity::class.java)
            intent.putExtra(DATA_KEY,word)
            activity.startActivity(intent)
        }
    }

}
