package com.wf.tkt.bluetools.broadcast

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wf.tkt.bluetools.bean.BlueBean
import com.wf.tkt.bluetools.utils.LogUtils

class BlueToolsBroadcast : BroadcastReceiver() {

    companion object {

        val STATE_TURNING_ON:Int = 100001//蓝牙打开中
        val STATE_ON:Int = 100002//蓝牙打开完成
        val STATE_TURNING_OFF:Int = 100003//蓝牙关闭中
        val STATE_OFF:Int = 100004//蓝牙关闭完成
        val ACTION_DISCOVERY_FINISHED:Int = 100005//蓝牙搜索结束
        val ACTION_DISCOVERY_STARTED:Int = 100006//蓝牙搜索中
        val BOND_BONDING:Int = 100007//蓝牙配对中
        val BOND_BONDED:Int = 100008 //蓝牙配对完成
        val BOND_NONE : Int  = 100009//取消配对蓝牙

        var adListDevices: MutableLiveData<BlueBean> = MutableLiveData()

        var dmpmap: HashMap<String, String> = HashMap()

    }

    //    注册蓝牙广播
    fun registerBroadcast(context: Context) {
        Log.e("TAG", "开始注册蓝牙广播")
        val filter = IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED) //蓝牙状态改变的广播
        filter.addAction(BluetoothDevice.ACTION_FOUND) //找到设备的广播
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED) //搜索完成的广播
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED) //开始扫描的广播
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED) //状态改变
        context.registerReceiver(this, filter)
    }




    /**
     * 取消注册广播
     */
    fun unReceive(context: Context) {
        context.unregisterReceiver(this)
    }


    override fun onReceive(context: Context?, intent: Intent?) {

        Log.e("TAG", "接收到广播")
        Log.e("TAG", "广播内容：" + intent?.action)

        when (intent?.action) {
            BluetoothAdapter.ACTION_STATE_CHANGED -> {//蓝牙打开、关闭状态
                val blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0)
                when (blueState) {
                    BluetoothAdapter.STATE_TURNING_ON -> {//蓝牙打开中
                        LogUtils.e("蓝牙打开中");
                    }
                    BluetoothAdapter.STATE_ON -> {//蓝牙打开完成
                        LogUtils.e("蓝牙打开完成");
                    }
                    BluetoothAdapter.STATE_TURNING_OFF -> {//蓝牙关闭中
                        LogUtils.e("蓝牙关闭中");
                    }
                    BluetoothAdapter.STATE_OFF -> {//蓝牙关闭完成
                        LogUtils.e("蓝牙关闭完成");
                    }
                }

            }
            BluetoothDevice.ACTION_FOUND -> {//找到设备

                val parcelableExtra: BluetoothDevice? = intent.getParcelableExtra(
                    BluetoothDevice.EXTRA_DEVICE
                )
                if (null != parcelableExtra?.name && parcelableExtra?.name != "") {
                    adListDevices.value = BlueBean(parcelableExtra)
                }
                if (parcelableExtra?.bondState == BluetoothDevice.BOND_BONDED) {
                    Log.e("TAG", "当前已经配对蓝牙设备名称：" + parcelableExtra?.name)
                    Log.e("TAG", "${parcelableExtra.uuids}当前已经配对蓝牙设备地址：" + parcelableExtra?.address)
                    Log.e("TAG", "---------------------------------------------------")
                } else if (parcelableExtra?.bondState != BluetoothDevice.BOND_BONDED) {
                    Log.e("TAG", "当前没有配对蓝牙设备名称：" + parcelableExtra?.name)
                    Log.e(
                        "TAG",
                        "${parcelableExtra?.uuids}当前没有配对蓝牙设备地址：" + parcelableExtra?.address
                    )
                    Log.e("TAG", "---------------------------------------------------")
//                    initdata()
                }
            }
            BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {//搜索完成
                LogUtils.e("蓝牙搜索结束")
                broadcastListener.onBlueToolsBroadcastStateListener(ACTION_DISCOVERY_FINISHED)
            }
            BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {//开始扫描
                LogUtils.e("蓝牙开始扫描")
                broadcastListener.onBlueToolsBroadcastStateListener(ACTION_DISCOVERY_STARTED)
            }
            BluetoothDevice.ACTION_BOND_STATE_CHANGED -> {//蓝牙设备状态改变
                val de = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                when (de?.bondState) {
                    BluetoothDevice.BOND_NONE -> {
                        LogUtils.e("已经配对蓝牙取消配对")
                    }
                    BluetoothDevice.BOND_BONDING -> {
                        LogUtils.e("配对中")
                    }
                    BluetoothDevice.BOND_BONDED -> {
                        LogUtils.e("配对完成")
                    }
                }
            }
        }
    }

    fun setBlueToolsBroadcastListener(listener:BlueToolsBroadcastListener){
        broadcastListener = listener
    }

    lateinit var broadcastListener: BlueToolsBroadcastListener

}

interface BlueToolsBroadcastListener{


   fun onBlueToolsBroadcastStateListener(state:Int)


}