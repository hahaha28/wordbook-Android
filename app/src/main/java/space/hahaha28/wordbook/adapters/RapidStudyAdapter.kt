package space.hahaha28.wordbook.adapters

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.hahaha28.wordbook.R
import space.hahaha28.wordbook.moudle.Word


class RapidStudyAdapter(wordList:List<Word>) : RecyclerView.Adapter<RapidStudyAdapter.ViewHolder>(){

    private val wordList:List<Word>
    private val flags:IntArray
    var onLongClickListener:(word:Word)->Unit = {}

    init{
        this.wordList = wordList
        this.flags = IntArray(wordList.size)
    }


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val view = itemView
        val textView:TextView
        init{
            textView = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rapid_study,parent,false)
        val holder = ViewHolder(view)
        holder.view.setOnClickListener {
            //set Animation
            val clickAnim: ObjectAnimator = ObjectAnimator.ofFloat(
                holder.view,
                "rotationX", 180f, 360f
            )
//            clickAnim.setDuration(500);
            val position = holder.adapterPosition
            val word: Word = wordList.get(position)
            val content = holder.textView.text.toString()
            if (content == word.word) {
//                String newContent=word.getMeaning();
//                holder.textView.setText(newContent);
                flags[position] = 1
                notifyItemChanged(position)
                clickAnim.start()
            } else {
                flags[position] = 0
                notifyItemChanged(position)
//                holder.textView.setText(word.getWord());
                clickAnim.start()
            }
        }
        holder.view.setOnLongClickListener {
            val word = wordList.get(holder.adapterPosition)
            onLongClickListener(word)
            true
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = wordList[position]
        val flag = flags[position]
        if(flag == 0){
            holder.textView.text = word.word
        }else{
            holder.textView.text = word.mean
        }
    }

    override fun getItemCount(): Int = wordList.size
}