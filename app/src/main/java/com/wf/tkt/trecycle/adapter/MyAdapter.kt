package com.wf.tkt.trecycle.adapter

import android.provider.ContactsContract
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wf.tkt.BR
import com.wf.tkt.R
import com.wf.tkt.trecycle.bean.Result
import kotlinx.android.synthetic.main.recyle_item.view.*

class MyAdapter: BaseAdapter<Result>(){

    override fun getRecycleLayoutID()=  R.layout.recyle_item


    override fun onBindViewHolder(holder:BaseViewHolder, position: Int) {
        val  viewDat: ViewDataBinding = holder.item
        viewDat.setVariable(BR.result,list.get(position))
        Glide.with(holder.itemView.context).load(list.get(position).url).into(holder.itemView.recycle_image)
    }
}