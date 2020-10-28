package com.wf.tkt.trecycle

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wf.tkt.trecycle.bean.ListBean
import com.wf.tkt.trecycle.net.MyApiServices
import com.wf.tkt.trecycle.net.RetrofitUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
//viewmodel层
class RecycleViewModel :ViewModel() , Observable{

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()
    val listBean : MutableLiveData<ListBean> by lazy {
        MutableLiveData<ListBean>().also {
            getHttpResult()
        }
    }

    fun getHttpResult(){
        viewModelScope.launch {
           val data = withContext(Dispatchers.IO){
             RetrofitUtils.retrofitUtils.getApiServices(MyApiServices::class.java).getRecycleList()
            }
            listBean.value = data
//            notifysAll()
        }
    }

    fun notifysAll(){

        callbacks.notifyCallbacks(this,0, null)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }


}