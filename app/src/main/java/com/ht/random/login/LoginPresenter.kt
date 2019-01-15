package com.ht.random.login

import android.os.Handler

/**
 * Created by hutao on 2019/1/15.
 */
class LoginPresenter : LoginPresenterImpl {
    var loginView: LoginView
    var handler: Handler = Handler()

    constructor(loginView: LoginView?) {
        this.loginView = loginView!!
    }

    override fun load(name: String, pass: String) {
        loginView.showLoading()
        handler.postDelayed(object : Runnable {
            override fun run() {
                loginView.hindLoading()
                if (name.equals("random") && !pass.equals("random")) {
                    loginView.loadFail("密码错误")
                } else if (!name.equals("random")) {
                    loginView.loadFail("账户不存在或密码错误")
                } else if (name.equals("random") && pass.equals("random")) {
                    loginView.loadSuccess()
                } else {
                    loginView.loadFail("未知错误")
                }
            }
        }, 2000)
    }
}