package com.wf.tkt.bluetools.adapter

import android.bluetooth.BluetoothDevice
import android.view.View
import androidx.appcompat.view.menu.MenuView
import com.wf.tkt.BR
import com.wf.tkt.R
import com.wf.tkt.bluetools.bean.BlueBean
import com.wf.tkt.trecycle.adapter.BaseAdapter
import com.wf.tkt.trecycle.adapter.BaseViewHolder

class BlueDevicesAdapter : BaseAdapter<BlueBean>(){

    lateinit var blueAdapterItemClick: BlueAdapterItemClick

    override fun getRecycleLayoutID() =
        R.layout.recycle__bluedevices_item

    fun setClickBlueItemClick(itemClick: BlueAdapterItemClick){
        this.blueAdapterItemClick = itemClick
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = holder.item
        item.setVariable(BR.deviceBean,list?.get(position))

        holder.itemView.setOnLongClickListener {
            blueAdapterItemClick.onBlueItemLongClickListener(holder.itemView,list.get(position).device)
           true
        }
        holder.itemView.setOnClickListener {
            blueAdapterItemClick.onBlueItemClickListener(holder.itemView,list.get(position).device)
        }
    }
}

interface BlueAdapterItemClick{
    fun onBlueItemClickListener(itemView:View,device: BluetoothDevice)
    fun onBlueItemLongClickListener(itemView: View, device: BluetoothDevice)
}