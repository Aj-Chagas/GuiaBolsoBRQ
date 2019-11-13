package br.com.ajchagas.guiabolsobrq.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.ajchagas.guiabolsobrq.R

class SplashScreen : AppCompatActivity() {

    private val delay : Long = 2000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, ListAccountActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()

        handler.postDelayed(mRunnable, delay)
    }

}
