package com.ht.random.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ht.random.R

/**
 * Created by hutao on 2019/1/15.
 */
class DemoAdapter : RecyclerView.Adapter<DemoAdapter.ViewHolder> {
    var context: Context? = null
    var mData: List<String>? = null

    constructor(context: Context?, mData: List<String>?) : super() {
        this.context = context
        this.mData = mData
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_demo, parent, false))
    }

    override fun getItemCount(): Int = mData?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var cont: String? = mData?.get(position)
        holder?.content?.text = cont
        holder?.content?.setOnClickListener {
            listener.onItemClick(cont.toString())
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content: TextView = itemView.findViewById(R.id.content)
    }

    lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(string: String)
    }
}