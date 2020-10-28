package com.wf.tkt

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel/* : ViewModel(),Observable{

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()


    var userBean = MutableLiveData<UserBean>()

    //被观察者
    var result = MutableLiveData<String>()

    fun textChanged(){

    }
*//*    fun getHttpResult(){
//网络请求发生变化。
        result.value = "变化"
    }*//*
//    取消监听
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

//    添加监听
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    var count = 1

    fun notityTest(){
        userBean.value = UserBean("里斯:"+count++,15)
//        userBean.name = "李四"
//        userBean.age = 19
        notifyChange()
    }


    //    通知所有属性变化
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    *//**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     *//*
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

}*/