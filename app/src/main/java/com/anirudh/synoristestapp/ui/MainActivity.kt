package com.anirudh.synoristestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anirudh.synoristestapp.R
import com.anirudh.synoristestapp.data.db.entities.LocalDocData
import com.anirudh.synoristestapp.data.others.showToast
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(),MainListener, KodeinAware {
    private lateinit var adapter: MainAdapter
    override val kodein by kodein()
    val itemList = arrayListOf<LocalDocData>()
    private val factory by instance<MainViewModelFactory>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("This is the test log")

        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this,itemList) {
            println("All list clicked")
        }
        recycler_view.adapter = adapter


        val viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
        viewModel.mainListener = this
        viewModel.mainList.observe(this, Observer {
            onSuccess()
            itemList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        swipeToRefresh.setOnRefreshListener {
            itemList.clear()
            swipeToRefresh.isRefreshing = false
            viewModel.getAllOrderList()
        }
    }
    private fun branchTODO() {
        
    }
    private fun branch2TODO() {

    }

    override fun onStarted() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressBar.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressBar.visibility = View.GONE
        showToast(message)
    }
}