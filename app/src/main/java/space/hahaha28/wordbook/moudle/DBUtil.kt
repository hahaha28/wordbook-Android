package space.hahaha28.wordbook.moudle

import org.litepal.LitePal

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
        val wordList = LitePal.findAll(Word::class.java)
        return wordList
    }

}