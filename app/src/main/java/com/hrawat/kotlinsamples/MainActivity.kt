package com.hrawat.kotlinsamples

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hrawat.kotlinsamples.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUIListeners()
    }

    private fun setupUIListeners() {
        binding.btnFetch.setOnClickListener {
            val intent = Intent(this@MainActivity, MyListActivity::class.java)
            startActivity(intent)
        }

        binding.btnLaunchApp.setOnClickListener {
            try {
                val intent = Intent()
                intent.setClassName(
                    "com.harsh8088.tv",
                    "com.harsh8088.tv.ui.main.view.MainActivity"
                )
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this@MainActivity,
                    "Target app not found or cannot be launched!",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

}
