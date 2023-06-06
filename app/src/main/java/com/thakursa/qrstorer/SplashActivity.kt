package com.thakursa.qrstorer

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            var item = Intent(this, LoginActivity::class.java)
            startActivity(item)
            finish()
        },3000)
        val song : MediaPlayer = MediaPlayer.create(this,R.raw.woosh)
        song.start()
    }
}