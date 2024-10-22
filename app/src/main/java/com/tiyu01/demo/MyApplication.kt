package com.tiyu01.demo

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化 SDK
        FacebookSdk.setAutoLogAppEventsEnabled(true) // 启用自动记录应用事件
        AppEventsLogger.activateApp(this) //激活应用事件记录
    }
}