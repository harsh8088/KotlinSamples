package com.hrawat.kotlinsamples

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn_fetch)
        btn.setOnClickListener {
            val intent = Intent(this@MainActivity, MyListActivity::class.java)
            startActivity(intent)
        }

    }
}
