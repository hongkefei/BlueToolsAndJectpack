package com.wf.tkt.bluetools.blutools

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast
import java.lang.reflect.Method

class BlueToolsUtils private constructor(){

    lateinit var defaultAdapter : BluetoothAdapter

    companion object {
        val blueToolsUtils: BlueToolsUtils by lazy {
            BlueToolsUtils()
        }
    }


    /**
     * 打开蓝牙
     */
    fun openBlueTools(context: Context){
//       得到蓝牙适配器
    defaultAdapter = BluetoothAdapter.getDefaultAdapter()
//       判断当前蓝牙是否开启
    if(defaultAdapter.isEnabled){
//           搜索蓝牙
        Toast.makeText(context,"蓝牙已经打开，请点击页面搜索按钮，开始搜索", Toast.LENGTH_SHORT).show()
    }else{
        defaultAdapter.enable()
        Toast.makeText(context,"蓝牙没有打开，通过代码，强制打开/n 且打开后可能没有任何提示", Toast.LENGTH_SHORT).show()
    }
    }

    /**
     *     搜索蓝牙
      */
    fun seachBlueTools(context: Context){
        Log.e("TAG", "点击了搜索按钮")
        //判断当前是否在蓝牙搜索
        if(!defaultAdapter.isDiscovering()) { //返回boolean
            seach()
        }else{
            Toast.makeText(context,"当前蓝牙正在搜索.....请等待结束",Toast.LENGTH_SHORT).show()
        }
    }

    /**
     *     开始搜索蓝牙
     */
    fun seach(){
        if (defaultAdapter.isEnabled){
            Log.e("TAG", "蓝牙开启，开始搜索.......")
            defaultAdapter.startDiscovery()
        }
    }


    /**
     * 暂停搜索
     */
    fun stopSeachBlue(){

    }

    /**
     * 退出当前页面的时候，释放所有蓝牙操作
     */
    fun overBlue(){

    }

    /**
     *    开启蓝牙配对
     */

    fun startPair(device: BluetoothDevice){
    val method: Method = BluetoothDevice::class.java.getMethod("createBond")
//          Log.e(packageName, "开始配对")
          method.invoke(device)
    }


}