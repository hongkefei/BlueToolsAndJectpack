package com.wf.tkt.bluetools

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wf.tkt.R
import com.wf.tkt.bluetools.adapter.BlueAdapterItemClick
import com.wf.tkt.bluetools.adapter.BlueDevicesAdapter
import com.wf.tkt.bluetools.bean.BlueBean
import com.wf.tkt.bluetools.blutools.BlueToolsUtils
import com.wf.tkt.bluetools.broadcast.BlueToolsBroadcast
import com.wf.tkt.bluetools.broadcast.BlueToolsBroadcast.Companion.adListDevices
import com.wf.tkt.bluetools.broadcast.BlueToolsBroadcast.Companion.dmpmap
import com.wf.tkt.bluetools.broadcast.BlueToolsBroadcastListener
import com.wf.tkt.bluetools.utils.LogUtils
import com.wf.tkt.bluetools.widget.PopupWindowUtils
import kotlinx.android.synthetic.main.activity_bluetoos.*
import kotlinx.android.synthetic.main.pop_blue_cancleconnection.view.*


class BuleToolsUActivity :AppCompatActivity() , BlueAdapterItemClick,BlueToolsBroadcastListener{



    val broadcast = BlueToolsBroadcast()

    val popupWindowUtils: PopupWindowUtils = PopupWindowUtils(this)

    //        创建广播对象


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetoos)

//        开启注册广播
        broadcast.registerBroadcast(this)
//        广播监听
        broadcast.setBlueToolsBroadcastListener(this)
//        inBule()
//        打开蓝牙
        BlueToolsUtils.blueToolsUtils.openBlueTools(this)

//        请求权限
        getPermission()

        initView()

        initdata()

//        搜索按钮监听
        seachbluet.setOnClickListener{
            seachbluet.text = "正在搜索...."
            BlueToolsUtils.blueToolsUtils.seachBlueTools(this)
        }
    }
        fun initView(){
//            适配器
            bluetoos_recycle.apply {
                adapter = BlueDevicesAdapter()
                layoutManager = LinearLayoutManager(this.context)
                addItemDecoration(com.wf.tkt.bluetools.decoration.SpacesItemDecoration(90))
                val ap = adapter as BlueDevicesAdapter
                ap.setClickBlueItemClick(this@BuleToolsUActivity)
            }
        }

        fun initdata(){
            adListDevices?.observe(this, Observer {
                LogUtils.e("数据发生变化了，修改" )
                var tlist: BlueBean? = null
                dmpmap.apply {
                    if (!containsKey(it.device.name)) {
                        put(it.device.name, it.device.address)
                        tlist = BlueBean( it.device )
                        val blueDevicesAdapter = bluetoos_recycle.adapter as BlueDevicesAdapter
                        blueDevicesAdapter.list.add(tlist!!)
                        blueDevicesAdapter.notifyDataSetChanged()
                    }
                }

            })
        }

    override fun onDestroy() {
        super.onDestroy()
//        解除广播注册
//        unregisterReceiver(broadcast)
        broadcast.unReceive(this)
    }

    /**
     * 解决：无法发现蓝牙设备的问题
     *
     * 对于发现新设备这个功能, 还需另外两个权限(Android M 以上版本需要显式获取授权,附授权代码):
     */
    private val ACCESS_LOCATION = 1

    @SuppressLint("WrongConstant")
    private fun getPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            var permissionCheck = 0
            permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            permissionCheck += checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                //未获得权限
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    ACCESS_LOCATION
                ) // 自定义常量,任意整型
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            ACCESS_LOCATION -> if (hasAllPermissionGranted(grantResults)) {
                LogUtils.e( "onRequestPermissionsResult: 用户允许权限")
            } else {
                LogUtils.e( "onRequestPermissionsResult: 拒绝搜索设备权限")
            }
        }
    }

    private fun hasAllPermissionGranted(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }

    /**
     * recycleitem点击事件
     */
    override fun onBlueItemClickListener(itemView: View, device: BluetoothDevice) {
//      如果已经配对，就直接链接，没有配对，就执行配对
        if (device.bondState == BluetoothDevice.BOND_BONDED){//已配对--执行连接
            LogUtils.e("已经配对了，展示Pop")
            popupWindowUtils.showPopupWindow(R.layout.pop_blue_wait,itemView)






        }else{//未配对
            val method = BluetoothDevice::class.java.getMethod("createBond")
            LogUtils.e("开始配对")
            method.invoke(device)
        }

        Toast.makeText(this,"点击事件",Toast.LENGTH_SHORT).show()
    }

    /**
     * recycleview长安事件
     */
    override fun onBlueItemLongClickListener(itemView: View, device: BluetoothDevice) {

      val view =  popupWindowUtils.showPopupWindow(R.layout.pop_blue_cancleconnection,itemView)
        view.cancle_connect.setOnClickListener {
            val method = BluetoothDevice::class.java.getMethod("removeBond")
            LogUtils.e("取消配对")
            method.invoke(device)
        }
        Toast.makeText(this,"长按事件",Toast.LENGTH_SHORT).show()
    }

    /**
     * 广播事件监听
     */
    override fun onBlueToolsBroadcastStateListener(state: Int) {
        when(state){
            BlueToolsBroadcast.ACTION_DISCOVERY_FINISHED->{//蓝牙搜索结束
                seachbluet.text = "蓝牙搜索结束，可以从新开始搜索"
                popupWindowUtils.dismissPopWindow()
                 }
            BlueToolsBroadcast.ACTION_DISCOVERY_STARTED->{//蓝牙搜索中
                seachbluet.text = "蓝牙正在搜索中....."
                popupWindowUtils.showPopupWindow(R.layout.pop_blue_wait,seachbluet)
            }
            BlueToolsBroadcast.BOND_NONE->{//蓝牙取消配对广播
                popupWindowUtils.dismissPopWindow()
            }

        }

    }

}