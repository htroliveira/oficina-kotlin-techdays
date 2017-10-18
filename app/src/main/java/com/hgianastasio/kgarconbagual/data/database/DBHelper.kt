package com.hgianastasio.kgarconbagual.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.hgianastasio.kgarconbagual.R

/**
 * Created by heitorgianastasio on 16/10/17.
 */
class DBHelper(val context: Context)
    :SQLiteOpenHelper(context,"garconbagualdb",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execFromResource(R.raw.createdb,context)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}