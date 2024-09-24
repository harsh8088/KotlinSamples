package com.hrawat.kotlinsamples

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hrawat.kotlinsamples.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private var index: Int = -1
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = this.intent

        if (intent.hasExtra("index")) {
            // Intent is from passed data
            index = intent.getIntExtra("index", -1)
        }
        if (intent.data?.scheme == "https" && intent.data?.host == "www.harshtest.com") {
            // Intent is from a specific app link
            val appLinkAction: String? = intent.action
            val appLinkData: Uri? = intent.data

            println("intent.action:${intent.action}")
            println("intent.data:${intent.data}")
            println("intent.data:lastPathSegment${intent.data!!.lastPathSegment}")

            index = intent.data!!.lastPathSegment!!.toInt()
        }

        handler = Handler(Looper.getMainLooper())
        //background task and update UI
        startBackgroundTask()
    }


    private fun startBackgroundTask() {
        val runnable = Runnable {
            val result = performLongRunningTask()

            // Update UI on the main thread
            handler.post {
                updateUI(result)
            }
        }

        handler.postDelayed(runnable, 2000)
    }

    private fun performLongRunningTask(): List<ItemModel> {
        // Simulate a long-running task
        val items = listOf(
            ItemModel("john", "sec 50, noida", "2013"),
            ItemModel("arjun", "delhi", "2011"),
            ItemModel("ravi", "punjab", "2010"),
            ItemModel("kumar", "delhi", "2001"),
            ItemModel("Singh", "hld", "2015"),
            ItemModel("Deepak", "noida", "2000"),
            ItemModel("ravi", "punjab", "2010"),
            ItemModel("kumar", "delhi", "2001"),
            ItemModel("Singh", "hld", "2015"),
            ItemModel("Deepak", "noida", "2000"),
            ItemModel("ravi", "punjab", "2010"),
            ItemModel("kumar", "delhi", "2001"),
            ItemModel("Singh", "hld", "2015"),
            ItemModel("Deepak", "noida", "2000"),
            ItemModel("ravi", "punjab", "2010"),
            ItemModel("kumar", "delhi", "2001"),
            ItemModel("Singh", "hld", "2015"),
            ItemModel("Deepak", "noida", "2000")
        )
        return items
    }

    private fun updateUI(result: List<ItemModel>) {
        // Update UI elements with the result
        binding.progressBar.visibility = View.GONE
        binding.rlItem.visibility = View.VISIBLE
        if (index > -1 && index < result.size) { // Check index validity
            val item = result[index]
            binding.year.text = item.year
            binding.title.text = item.name
            binding.address.text = item.address

        } else {
            Toast.makeText(this, "Data Not Fetched at index($index)", Toast.LENGTH_SHORT).show()
        }
    }

}
