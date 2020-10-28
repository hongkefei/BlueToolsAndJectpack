package com.wf.tkt.bluetools.utils

import android.util.Log

/**
 *@time : 2020-10-27-20:30
 *@author : wf
 */
class LogUtils   {

    /**
     *包装log.d日志
     */

    companion object {

        private val isDebug: Boolean = true
        private val TAG: String = "TAG"

        fun d(msg: String) {
            if (isDebug) {
                Log.d(TAG, msg)
            }
        }

        /**
         *包装log.e日志
         */
        fun e(msg: String) {
            if (isDebug) {
                Log.e(TAG, msg)
            }
        }

        /**
         * v类型的log.v日志
         */
        fun v(msg: String) {
            if (isDebug) {
                Log.v(TAG, msg)
            }
        }
    }
}