package com.wf.tkt.trecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.wf.tkt.trecycle.adapter.MyAdapter
import kotlinx.android.synthetic.main.activity_recy.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.wf.tkt.R
import com.wf.tkt.trecycle.bean.Result

class RecyActivity :AppCompatActivity(){

//获取viewmodel
    val viewModel : RecycleViewModel by viewModels()

    var list = listOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recy)

        mrecycleview.apply {

            layoutManager = LinearLayoutManager(this@RecyActivity)
            adapter  = MyAdapter()
        }

        viewModel.listBean.observe(this, Observer {
            Log.e("TAG", "网络请求结束 "+it.toString() )
            val myAdapter = mrecycleview.adapter as MyAdapter

            myAdapter.setData(it.results)
            myAdapter.notifyDataSetChanged()
            })


    }
}