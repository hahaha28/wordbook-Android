package space.hahaha28.wordbook.moudle

import android.util.Log
import org.litepal.LitePal
import org.litepal.extension.find
import space.hahaha28.wordbook.toJson
import java.util.*


class DBUtil {

    /**
     * 保存单词
     */
    fun saveWords(wordList:List<Word>){
        for(word in wordList){
            word.save()
        }
    }

    /**
     * 获取所有单词
     */
    fun getAllWords():List<Word>{
        return LitePal.findAll(Word::class.java)
    }

    /**
     * 获取当天的单词
     */
    fun getTodayWords():List<Word>{
        // 先获取当天零点的时间
        val current = System.currentTimeMillis()
        val zero = current-(current+TimeZone.getDefault().rawOffset)%(1000*3600*24)
        // 在数据库中查找
        val words =  LitePal.where("time > ?","$zero")
            .order("time desc")
            .find<Word>()
        return words
    }

    /**
     * 获取随机 num 个单词
     * @param num 获取单词的个数
     */
    fun getRandomWords(num:Int):List<Word>{
        val wordList = getAllWords()
        val size = if(num<=wordList.size) num else wordList.size
        val indexs = IntArray(num){-1}
        Log.e("MyDebug","indexs=${indexs.toJson()}")
        val resultList = arrayListOf<Word>()
        for(i in 0 until size){
            var index:Int
            do {
                index = Random().nextInt(wordList.size)
            }while(indexs.contains(index))
            indexs[i] = index
            resultList.add(wordList[index])
        }
        return resultList
    }

    /**
     * 获取最近的 num 个单词
     */
    fun getRecentWords(num:Int):List<Word>{
        val wordList = LitePal.order("time desc")
            .find(Word::class.java)
        val size = if(num<=wordList.size) num else wordList.size
        return wordList.subList(0,size)
    }

}