package com.wf.tkt.home

/*
data  class UserBean(var name:String= "张三",var age:Int =18,var sex:String= "男")*/
data class ResultBean(
    val code: Int,
    val message: String,
    val shoplist: ArrayList<Shoplist>
)

data class Shoplist(
    var isChecked : Boolean = false,
    val sList: ArrayList<S>,
    val shopingID: Int,
    val shopingName: String
)

data class S(
    var isChecked : Boolean = false,
    val sCount: String,
    val sID: Int,
    val sNmae: String,
    val sPrice: String
)