package com.example.personalitytestapp.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personalitytestapp.repository.QuestionListRepository
import com.example.personalitytestapp.R
import com.example.personalitytestapp.model.Data
import com.example.personalitytestapp.network.QuestionListApi
import com.example.personalitytestapp.network.QuestionListService
import com.example.personalitytestapp.view.adapters.QuestionListAdapter
import com.example.personalitytestapp.viewmodel.QuestionListViewModel
import com.example.personalitytestapp.viewmodel.QuestionListViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class QuestionListFragment : Fragment() {

    private lateinit var questionListAdapter: QuestionListAdapter
    private lateinit var viewModel : QuestionListViewModel
    private lateinit var viewModelFactory: QuestionListViewModelFactory
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:5000/") //make sure it matches with your current ip address
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(QuestionListApi::class.java)

    private val service = QuestionListService(api)
    private val repository = QuestionListRepository(service)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        setupViewModel()
        viewModel.questionListLiveData.observe(this as LifecycleOwner, { questionList ->
            Log.d("list ***********", questionList.isSuccess.toString())
            if (questionList.getOrNull() !=null)
                setupList(view, questionList.getOrNull()!!)
            else {
                Toast.makeText(activity, "NO data",Toast.LENGTH_SHORT).show()
            }
        })
        return view
    }

    private fun setupList(
        view: View?,
        questionList: Data
    ) {
//        with(view as RecyclerView) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = QuestionListAdapter(questionList)
//            questionListAdapter.notifyDataSetChanged()
//        }
        val recyclerview = view?.findViewById<RecyclerView>(R.id.list_of_questions)
        recyclerview?.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerview?.addItemDecoration(decoration)
        recyclerview?.adapter = QuestionListAdapter(questionList)
        questionListAdapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModelFactory = QuestionListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[QuestionListViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            QuestionListFragment()
    }
}