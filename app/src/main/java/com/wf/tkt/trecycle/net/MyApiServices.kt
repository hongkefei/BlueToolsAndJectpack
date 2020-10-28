package com.wf.tkt.trecycle.net

import com.wf.tkt.trecycle.bean.ListBean
import retrofit2.http.GET

interface MyApiServices {


    @GET("data/福利/20/2")
   suspend fun getRecycleList():ListBean

}