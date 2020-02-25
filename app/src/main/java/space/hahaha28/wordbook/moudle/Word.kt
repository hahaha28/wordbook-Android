package space.hahaha28.wordbook.moudle

import org.litepal.crud.LitePalSupport
import java.io.Serializable

class Word :LitePalSupport(),Serializable{

    /**
     * 单词
     */
    var word :String = ""

    /**
     * 音标
     */
    var soundMark:String = ""

    /**
     * 中文解释
     */
    var mean :String = ""

    /**
     * 例句
     */
    var sentence:String = ""

    /**
     * 添加时间
     */
    var time:Long = 0L
}