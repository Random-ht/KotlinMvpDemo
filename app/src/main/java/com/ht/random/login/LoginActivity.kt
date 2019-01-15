package com.ht.random.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.ht.random.R
import com.ht.random.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView, View.OnClickListener {

    private lateinit var presenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.login) {
            var _name: String = name.text.toString().trim()
            var _pass: String = pass.text.toString().trim()
            presenter.load(_name, _pass)
        }
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hindLoading() {
        progress.visibility = View.GONE
    }

    override fun loadSuccess() {//跳转到下一个界面
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        showToast("登陆成功")
        finish()
    }

    override fun loadFail(error_msg: String) {
        showToast(error_msg)
    }

    fun showToast(string: String) {
        Toast.makeText(this@LoginActivity, string, Toast.LENGTH_SHORT).show()
    }
}
