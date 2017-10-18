package com.hgianastasio.kgarconbagual.data.dao

import android.content.ContentValues
import android.database.Cursor
import com.hgianastasio.kgarconbagual.data.database.DBHelper
import com.hgianastasio.kgarconbagual.data.database.getDouble
import com.hgianastasio.kgarconbagual.data.database.getLong
import com.hgianastasio.kgarconbagual.data.database.getString
import com.hgianastasio.kgarconbagual.data.models.Produto
import java.sql.SQLException

/**
 * Created by heitorgianastasio on 16/10/17.
 */
class ProdutoDAO(val dbHelper: DBHelper) {

    fun save(produto: Produto): Long{
        val db = dbHelper.writableDatabase
        return try {
            db.insertOrThrow(PRODUTO_TABLE_NAME,null,produto.toContentValues())
        }catch (ex : SQLException){
            db.replace(PRODUTO_TABLE_NAME,null,produto.toContentValues())
        }
    }


    fun getAll():List<Produto>{
        val db = dbHelper.readableDatabase
        val cursor = db.query(PRODUTO_TABLE_NAME, arrayOf(PRODUTO_ID_COLUMN, PRODUTO_NOME_COLUMN, PRODUTO_PRECO_COLUMN),null,null,null,null,null)
        val result = mutableListOf<Produto>()
        if (cursor.moveToFirst()){
            do{
                result.add(cursor.getProduto())
            }while (cursor.moveToNext())
        }
        return result
    }


    private fun Cursor.getProduto():Produto{
        return Produto(
                getLong(PRODUTO_ID_COLUMN),
                getDouble(PRODUTO_PRECO_COLUMN),
                getString(PRODUTO_NOME_COLUMN)
        )
    }

    private fun Produto.toContentValues():ContentValues{
        val values = ContentValues()
        if(this.id>-1)values.put(PRODUTO_ID_COLUMN,this.id)
        values.put(PRODUTO_NOME_COLUMN,this.nome)
        values.put(PRODUTO_PRECO_COLUMN,this.preco)
        return values
    }

    companion object {
        val PRODUTO_TABLE_NAME = "PRODUTO"
        val PRODUTO_ID_COLUMN = "_id"
        val PRODUTO_NOME_COLUMN = "NOME"
        val PRODUTO_PRECO_COLUMN = "PRECO"
    }
}