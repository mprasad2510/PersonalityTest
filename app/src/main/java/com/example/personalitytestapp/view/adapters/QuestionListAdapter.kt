package com.example.personalitytestapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personalitytestapp.R
import com.example.personalitytestapp.model.QuestionsItem

class QuestionListAdapter : RecyclerView.Adapter<QuestionListAdapter.MyViewHolder>() {

    var items = ArrayList<QuestionsItem>()

    fun setUpdatedData(items: ArrayList<QuestionsItem>) {
        this.items = items
        notifyDataSetChanged()
    }
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val category = view.findViewById<TextView>(R.id.category)
        val question = view.findViewById<TextView>(R.id.question)
        val option1 = view.findViewById<RadioButton>(R.id.option1)
        val option2 = view.findViewById<RadioButton>(R.id.option2)
        val option3 = view.findViewById<RadioButton>(R.id.option3)
        val option4 = view.findViewById<RadioButton>(R.id.option4)
        val option5 = view.findViewById<RadioButton>(R.id.option5)

        fun bind(data : QuestionsItem) {
            question.text = data.question

          }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
     holder.bind(items[position])
    }
}