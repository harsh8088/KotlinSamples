package com.hrawat.kotlinsamples

import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

/**
 * Created by hrawat on 10/11/2017.
 */
class MyListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: ArrayList<ItemModel> = ArrayList()

    var isLoading: Boolean = false

    companion object {
        private val TYPE_LOADING = 1
        private val TYPE_LIST = TYPE_LOADING + 1
        private val TYPE_EMPTY = TYPE_LIST + 1
    }

    lateinit var clickListener: ClickListener

    interface ClickListener {
        fun onListClick(position: Int)
    }

    fun setOnListClickListener(listener: ClickListener) {
        this.clickListener = listener
    }

    fun fetchList() {
        startLoading()

        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            items.add(ItemModel("john", "sec 50, noida", "2013"))
            items.add(ItemModel("arjun", "delhi", "2011"))
            items.add(ItemModel("ravi", "punjab", "2010"))
            items.add(ItemModel("kumar", "delhi", "2001"))
            items.add(ItemModel("Singh", "hld", "2015"))
            items.add(ItemModel("Deepak", "noida", "2000"))

            isLoading = false
            this.notifyDataSetChanged()
        }, 3000)


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is MyViewHolder) {
            val myViewHolder = holder
            val details = items[position]

            myViewHolder.tvName.text = details.name
            myViewHolder.tvAddress.text = details.address
            myViewHolder.tvYear.text = details.year
            myViewHolder.relativeLayout.setOnClickListener {
                clickListener.onListClick(position)
                Toast.makeText(context, "name:" + details.name, Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_LIST -> return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

            TYPE_LOADING -> return LoadingViewHolder((LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false)))
            TYPE_EMPTY -> return EmptyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_empty, parent, false))
            else -> return EmptyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_empty, parent, false))
        }

    }

    override fun getItemCount(): Int {
        if (isLoading)
            return 1
        else
            return if (items.size == 0) 1 else items.size
    }

    override fun getItemViewType(position: Int): Int {
        if (isLoading)
            return TYPE_LOADING
        else
            if (items.size == 0)
                return TYPE_EMPTY
            else
                return TYPE_LIST
    }

    fun startLoading() {
        this.isLoading = true
        notifyDataSetChanged()
    }


    private inner class MyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.title)
        val tvAddress: TextView = itemView.findViewById(R.id.address)
        val tvYear: TextView = itemView.findViewById(R.id.year)
        val relativeLayout: RelativeLayout = itemView.findViewById(R.id.rl_item)

    }

    private inner class EmptyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
    private inner class LoadingViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)


}