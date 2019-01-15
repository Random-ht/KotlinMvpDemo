package com.ht.random.login

/**
 * Created by hutao on 2019/1/15.
 */
interface LoginView {
    fun showLoading()
    fun hindLoading()
    fun loadSuccess()
    fun loadFail(erroe_msg: String)
}