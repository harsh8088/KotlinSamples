package com.hrawat.kotlinsamples

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MyListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list)
        initUI()
    }

    private fun initUI() {
        val recycler = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = MyListAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
        adapter.fetchList()
        adapter.setOnListClickListener(object : MyListAdapter.ClickListener {
            override fun onListClick(position: Int) {
                Intent(this@MyListActivity, DetailsActivity::class.java).apply {
//                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    putExtra("index", position)
                    startActivity(this)
//                    finish()
                }
//                Toast.makeText(this@MyListActivity, "Position:$position", Toast.LENGTH_SHORT).show()
            }
        })


    }
}
