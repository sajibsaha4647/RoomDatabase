package com.example.roomdatabase

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var database:ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "contact.db")
            .build()

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"sajib","9999"))
        }



    }

    fun getData(view: View) {

        database.contactDao().readContact().observe(this, Observer {
            Log.d("contact", it.toString())
        })

    }
}