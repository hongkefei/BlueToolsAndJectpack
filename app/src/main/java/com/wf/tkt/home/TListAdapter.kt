package com.wf.tkt.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.wf.tkt.BR
import com.wf.tkt.R
import com.wf.tkt.trecycle.adapter.BaseAdapter
import com.wf.tkt.trecycle.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_one_recycle.view.*

//一级列表
class TListAdapter : BaseAdapter<Shoplist>(){

    override fun getRecycleLayoutID()= R.layout.item_one_recycle

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        val item = holder.item
        item.setVariable(BR.shlist,list?.get(position))

        holder.itemView.two_recycle.apply {
            adapter = TwoListAdapter()
            layoutManager = LinearLayoutManager(this.context)
            val twoListAdapter = adapter as TwoListAdapter
            twoListAdapter.setData(list?.get(position)?.sList!!)
            twoListAdapter.setUpdataListener(object : ChildToParentListener{
                override fun toListUpdata(b: Boolean):Unit {

//                    接收
                }
            })



        }
//        只是修改当前一级列表
        holder.itemView.ch_box.setOnClickListener{
           if( list?.get(position)?.isChecked!!){
               list?.get(position)?.isChecked = false

//               循环生成了新对象，原数据，可能没有背修改
              for(child in list?.get(position)?.sList!!){
                  child.isChecked = false
              }
//               val twoListAdapter = holder.itemView.two_recycle.adapter as TwoListAdapter
//               twoListAdapter.notifyDataSetChanged()
           }else{
               list?.get(position)?.isChecked = true
               for(child in list?.get(position)?.sList!!){
                   child.isChecked = true
               }
//               val twoListAdapter = holder.itemView.two_recycle.adapter as TwoListAdapter
//               twoListAdapter.notifyDataSetChanged()
           }
            notifyItemChanged(position)
        }







    }

}