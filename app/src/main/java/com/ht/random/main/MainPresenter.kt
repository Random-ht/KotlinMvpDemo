package com.ht.random.main

import android.os.Handler
import java.util.*

/**
 * Created by hutao on 2019/1/15.
 */
class MainPresenter : MainPresenterImpl {
    lateinit var mainView: MainView
    var handler:Handler = Handler()

    constructor(mainView: MainView) {
        this.mainView = mainView
    }

    override fun loadList(page: Int, pageSize: Int) {
        handler.postDelayed(object :Runnable{
            override fun run() {
                if (page == 1) {
                    var random = Random()
                    var rand: Int = random.nextInt(5)
                    if (rand < 3) {
                        mainView.noData()
                    } else {
                        var list = ArrayList<String>()
                        for (index in 1..10) {
                            list.add(index.toString())
                        }
                        mainView.loadDataSuccess(list)
                    }
                } else {
                    var list = ArrayList<String>()
                    for (index in page * 10..page * 10 + 10) {
                        list.add(index.toString())
                    }
                    mainView.loadDataMoreSuccess(list)
                }
            }
        },2000)
    }

}