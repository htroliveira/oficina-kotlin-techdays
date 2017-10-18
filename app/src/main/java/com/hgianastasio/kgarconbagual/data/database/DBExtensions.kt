package com.hgianastasio.kgarconbagual.data.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by heitorgianastasio on 16/10/17.
 */
fun SQLiteDatabase.execFromResource(resId: Int, context: Context){
    val inputStream = context.resources.openRawResource(resId)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val result = StringBuilder()
    do {
        result.append(reader.readLine())
    } while (reader.ready())

    this.execSQL(result.toString())
}


fun Cursor.getString(colunmName: String) = getString(getColumnIndex(colunmName))
fun Cursor.getLong(colunmName: String) = getLong(getColumnIndex(colunmName))
fun Cursor.getDouble(colunmName: String) = getDouble(getColumnIndex(colunmName))