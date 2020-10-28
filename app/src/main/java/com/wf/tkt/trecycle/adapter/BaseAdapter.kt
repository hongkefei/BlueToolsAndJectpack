package com.wf.tkt.trecycle.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.wf.tkt.R
import com.wf.tkt.trecycle.bean.Result

 abstract class BaseAdapter<T>  : RecyclerView.Adapter<BaseViewHolder>(){

    var list:ArrayList<T> = arrayListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val viewDataBinding: ViewDataBinding =  DataBindingUtil.inflate(
            LayoutInflater.from(parent?.context),
            getRecycleLayoutID(),parent,false)
        return BaseViewHolder(viewDataBinding)
    }

   abstract fun  getRecycleLayoutID():Int

    override fun getItemCount(): Int {
        return list!!.size
    }

   abstract override fun onBindViewHolder(holder: BaseViewHolder, position: Int)



    fun setData(alist:ArrayList<T>){
        this.list = alist
        notifyDataSetChanged()
    }

     fun notafile(){
         notifyDataSetChanged()
     }

}

class BaseViewHolder(var item:ViewDataBinding):RecyclerView.ViewHolder(item.root){

}
