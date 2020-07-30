package com.example.mytedrssfeed.main

import android.util.Log
import kotlinx.coroutines.*

class MainPresenter(view: MainActivity) {

    private val mModel = TEDApiImpl
    private val mView = view

    fun start() {
        val job: Job = GlobalScope.launch {

            val data = withContext(Dispatchers.IO) {
                Log.d("MainP", mModel.getListTED().toString())
                mModel.getListTED()
            }
            mView.setItems(data)
        }
        job.start()
    }


}