package com.wf.tkt.home


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.wf.tkt.R
import kotlinx.android.synthetic.main.myxml.*
import java.io.IOException


class MainActivity : AppCompatActivity() {


//    val viewModel:MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.myxml)
//        第五步   ，   让activity和xml布局绑定

//        val db: MyxmlBinding = DataBindingUtil.setContentView(this, R.layout.myxml)

     /*
        db.viewmodel = viewModel
        submit_text.setOnClickListener {
            viewModel.notityTest()
        }
*/

       var result = opAssetsResult()

        Log.e("TAG", "onCreate:读取完毕： "+result )

        var  gson = Gson()

        val resultBean = gson.fromJson(result, ResultBean::class.java)

        Log.e("TAG", "onCreate:数据远： "+resultBean.toString() )
//
        myrecycle.apply {
            adapter = TListAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(SpacesItemDecoration(30))
        }

        val tListAdapter = myrecycle.adapter as TListAdapter
        tListAdapter.setData(resultBean.shoplist)

    }

    fun opAssetsResult():String{
        var result: String = ""
        try {
           val reluString = assets.open("result.json")
            var lenght = 0
            lenght = reluString.available()
            val buffer = ByteArray(lenght)
            reluString.read(buffer)
            result = String(buffer, Charsets.UTF_8)
//            tv.setText(result)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }



}