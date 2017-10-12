package com.hrawat.kotlinsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class MyListActivity : AppCompatActivity(), View.OnClickListener{
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list)
        init()

    }

    fun init() {

        val recycler = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = MyListAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter

        adapter.fetchList()


        adapter.setOnListClickListener(object : MyListAdapter.ClickListener {
            override fun onListClick(position: Int) {
//                Toast.makeText(this@MyListActivity, "Position:" + position, Toast.LENGTH_SHORT).show()
            }
        })


    }
}
