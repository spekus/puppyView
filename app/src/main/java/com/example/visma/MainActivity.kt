package com.example.visma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.visma.ui.main.MainFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        Timber.plant(Timber.DebugTree())
    }

}
