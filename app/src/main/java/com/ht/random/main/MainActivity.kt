package com.ht.random.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.ht.random.R
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, View.OnClickListener, OnRefreshListener, OnLoadMoreListener, DemoAdapter.OnItemClickListener {

    var mData = ArrayList<String>()
    lateinit var mAdapter: DemoAdapter
    lateinit var presenter: MainPresenter
    var page: Int = 1
    var pageSize: Int = 20

    var handler: Handler? = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            if (msg?.what == 0) {
                //下拉刷新
                refresh?.autoRefresh()
                page = 1
                presenter.loadList(page, pageSize)
            } else if (msg?.what == 1) {
                //上拉加载更多
                page = page + 1
                presenter.loadList(page, pageSize)
            } else {
                //其他操作
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        presenter = MainPresenter(this)
        handler?.sendEmptyMessage(0)
        initListener()
    }

    private fun initData() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = DemoAdapter(this@MainActivity, mData)
        recyclerView.adapter = mAdapter
    }

    private fun initListener() {
        refresh.setRefreshHeader(ClassicsHeader(this))
        refresh.setHeaderHeight(60F)
        refresh.setRefreshFooter(ClassicsFooter(this))
        refresh.setFooterHeight(60F)

        refresh.setOnRefreshListener(this)
        refresh.setOnLoadMoreListener(this)
        mAdapter.setOnItemClickListener(this)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        handler?.sendEmptyMessage(1)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        handler?.sendEmptyMessage(0)
    }

    override fun onItemClick(string: String) {
        Toast.makeText(this@MainActivity, string, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.no_data) {
            no_data.visibility = View.GONE
            refresh.visibility = View.VISIBLE
            handler?.sendEmptyMessage(0)
        }
    }

    override fun noData() {
        mData.clear()
        mAdapter.notifyDataSetChanged()
        refresh?.finishRefresh()
        no_data.visibility = View.VISIBLE
        refresh.visibility = View.GONE
    }

    override fun loadDataSuccess(list: List<String>) {
        mData.clear()
        mData.addAll(list)
        refresh?.finishRefresh()
        no_data.visibility = View.GONE
        refresh.visibility = View.VISIBLE
        mAdapter.notifyDataSetChanged()
    }

    override fun loadDataMoreSuccess(list: List<String>) {
        mData.addAll(list)
        refresh?.finishLoadMore()
        no_data.visibility = View.GONE
        refresh.visibility = View.VISIBLE
        mAdapter.notifyDataSetChanged()
    }

}
