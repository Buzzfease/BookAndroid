package com.buzz.bookandroid.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buzz.bookandroid.R
import com.buzz.bookandroid.di.AppComponentHolder
import com.buzz.bookandroid.screen.booklist.view.BookListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppComponentHolder.getComponent().mainComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, BookListFragment(), BookListFragment::class.simpleName)
            .commitNow()
    }

}
