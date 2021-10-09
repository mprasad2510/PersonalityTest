package com.example.personalitytestapp.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personalitytestapp.R
import com.example.personalitytestapp.model.QuestionList
import com.example.personalitytestapp.model.QuestionsItem
import com.example.personalitytestapp.view.adapters.QuestionListAdapter
import com.example.personalitytestapp.viewmodel.QuestionListViewModel

class QuestionListActivity : AppCompatActivity() {

    private lateinit var questionListAdapter: QuestionListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViewComponents()
        initViewModel()
    }

    private fun initViewModel() {
    val questionListViewModel = ViewModelProvider(this).get(QuestionListViewModel::class.java)
        questionListViewModel.getQuestionListObserver().observe(this , Observer {
            if (it !=null) {
            questionListAdapter.setUpdatedData(it.questions as ArrayList<QuestionsItem>)
            } else {
                Toast.makeText(this, "Unable to get Data",Toast.LENGTH_SHORT).show()
            }
        })
        questionListViewModel.makeApiCall()
    }

    private fun initializeViewComponents() {
        val questionListView = findViewById<RecyclerView>(R.id.list_of_questions)
        questionListView.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        questionListView.addItemDecoration(decoration)
        questionListAdapter = QuestionListAdapter()
        questionListView.adapter = questionListAdapter
    }

}