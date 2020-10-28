package com.wf.tkt.bluetools.widget

import android.app.Activity
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout

/**
 *@time : 2020-10-27-20:47
 *@author : wf
 */
class PopupWindowUtils(val context: Activity) {


     lateinit var popupWindow : PopupWindow



    fun showPopupWindow(layoutID:Int,showView:View):View{
        val pview =  View.inflate(context,layoutID,null)
        popupWindow = PopupWindow(pview,ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT)
        popupWindow.isOutsideTouchable = false
        popupWindow.isFocusable = false
        popupWindow.setBackgroundDrawable(ColorDrawable())
        setwindBackground(0.5f)//设置window半透明
        popupWindow.setOnDismissListener(PopupWindowDismissListener())
        popupWindow.showAsDropDown(showView)
        return pview
    }

    fun dismissPopWindow(){
        setwindBackground(1f)
        popupWindow.dismiss()
    }

    fun setwindBackground(bgAlpha:Float){
        var lp = context.window.attributes
        lp.alpha = bgAlpha
        context.window.attributes = lp
    }

  inner class PopupWindowDismissListener : PopupWindow.OnDismissListener{
        override fun onDismiss() {
            setwindBackground(1f)
        }
    }
}