package com.wf.tkt.home

import com.wf.tkt.BR
import com.wf.tkt.R
import com.wf.tkt.trecycle.adapter.BaseAdapter
import com.wf.tkt.trecycle.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.secondlisti.view.*

class TwoListAdapter :BaseAdapter<S>(){

    lateinit var ucl: ChildToParentListener

    override fun getRecycleLayoutID()= R.layout.secondlisti

    fun setUpdataListener(up:ChildToParentListener):Unit{
        ucl = up
    }
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = holder.item
        item.setVariable(BR.slist,list?.get(position))

        holder.itemView.two_check.setOnClickListener {

            if(list?.get(position).isChecked){
                list?.get(position).isChecked = false
            }else{
                list.get(position).isChecked = true
            }
            val checkListTrueORFlase = checkListTrueORFlase()
            if(checkListTrueORFlase){
                ucl.toListUpdata(checkListTrueORFlase)
            }else{
                ucl.toListUpdata(checkListTrueORFlase)
            }
        }
    }
    fun checkListTrueORFlase():Boolean{
        for (slist in list){
            if(slist.isChecked)
            else return false
        }

        return  true
    }

}



