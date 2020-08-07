package com.example.mytedrssfeed.main

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.mytedrssfeed.R


class NewActivity: AppCompatActivity()  {

    private lateinit var video: VideoView
    private var position = 0
    private lateinit var link: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(PLAYBACK_TIME)
        }

        val title = findViewById<TextView>(R.id.new_text_title)
        val author = findViewById<TextView>(R.id.new_text_author)
        val desc = findViewById<TextView>(R.id.new_text_descr)
        val arguments = intent.extras
        title.text = arguments?.getString("title")?.substringBefore('|')?.trim()
        author.text = arguments?.getString("title")?.substringAfter('|')?.trim()
        desc.text = arguments?.getString("desc")


        link = arguments?.getString("link").toString()
        video = findViewById(R.id.new_video_view)
        val mediaController = MediaController(this)
        mediaController.setMediaPlayer(video)
        video.setMediaController(mediaController)


    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            video.pause()
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        video.pause()
        outState.putInt(PLAYBACK_TIME, video.currentPosition)
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        position = savedInstanceState.getInt(PLAYBACK_TIME)
//    }

    @SuppressLint("LongLogTag")
    private fun initializePlayer() {
        val uri = Uri.parse(link)
        video.setVideoURI(uri)
        video.setOnPreparedListener{
            Log.d("initializePlayer position", "$position")
            Log.d("initializePlayer currentPosition", "${video.currentPosition}")
            if (position > 0) {
                video.seekTo(position)
            } else {
                video.seekTo(1)
            }
            video.start()
        }
    }

    private fun releasePlayer() {
        video.stopPlayback()
    }


    companion object {
        private const val PLAYBACK_TIME = "play_time"
    }
}