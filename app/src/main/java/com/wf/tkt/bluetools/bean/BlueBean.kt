package com.wf.tkt.bluetools.bean

import android.bluetooth.BluetoothDevice

data  class BlueBean (var device: BluetoothDevice){
    val noPair:String = "未配对"
    val alreadyPair:String = "已配对"
}