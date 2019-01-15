package com.ht.random.main

/**
 * Created by hutao on 2019/1/15.
 */
interface MainView {
    fun noData()
    fun loadDataSuccess(list:List<String>)
    fun loadDataMoreSuccess(list:List<String>)
}