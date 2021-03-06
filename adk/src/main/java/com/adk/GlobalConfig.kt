package com.adk

import android.content.Context
import android.content.SharedPreferences
import okhttp3.OkHttpClient

/**
 * 全局配置信息
 * 必须在application中进行初始化
 * 主要是是否是debug模式 和applicationContext
 */
object GlobalConfig {
    private var isDebug = true
    private lateinit var appContext: Context
    private lateinit var okHttpClient: OkHttpClient

    fun init(context: Context, isDebug: Boolean = true, okHttpClient: OkHttpClient = getDefaultOkHttpClient()) {
        this.appContext = context
        this.isDebug = isDebug
        this.okHttpClient = okHttpClient
    }

    fun isDebugModel() = isDebug

    fun getAppContext() = this.appContext

    fun getOkHttpClient() = this.okHttpClient

    fun getAppShare(): SharedPreferences {
        return appContext.getSharedPreferences(appContext.packageName, Context.MODE_PRIVATE)
    }
}