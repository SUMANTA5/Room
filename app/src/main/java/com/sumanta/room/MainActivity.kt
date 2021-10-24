package com.sumanta.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.room.Database
import androidx.room.Room
import com.sumanta.room.db.Contact
import com.sumanta.room.db.ContactDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"Java","845151515"))
        }

    }

    fun getData(view: android.view.View) {
        database.contactDao().getContact().observe(this, Observer {
            Log.d("Sumanta",it.toString())
        })
    }
}