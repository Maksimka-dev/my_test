package com.example.mytedrssfeed.main

import android.util.Log
import kotlinx.coroutines.*

class MainPresenter(view: MainActivity) {

    private val mModel = TEDApiImpl
    private val mView = view

    fun start() {
        val job: Job = GlobalScope.launch(Dispatchers.Main) {
            val data = withContext(Dispatchers.IO) {
                mModel.getListTED()
            }
            data?.let{
                mView.setItems(data)
            }
        }
        job.start()
    }


}