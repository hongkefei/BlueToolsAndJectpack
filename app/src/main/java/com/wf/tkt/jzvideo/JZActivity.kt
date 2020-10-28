package com.wf.tkt.jzvideo

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.jzvd.Jzvd
import com.wf.tkt.R
import kotlinx.android.synthetic.main.acitivity_jz.*


class JZActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_jz)

        jz_video.setUp("https://pipijoke.oss-cn-hangzhou.aliyuncs.com/6825960242983999755.mp4","播放视频");


        jz_video.posterImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"))

    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }


}