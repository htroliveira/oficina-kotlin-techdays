package com.hgianastasio.kgarconbagual.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hgianastasio.kgarconbagual.R
import com.hgianastasio.kgarconbagual.data.dao.ProdutoDAO
import com.hgianastasio.kgarconbagual.data.database.DBHelper
import com.hgianastasio.kgarconbagual.data.models.Produto
import kotlinx.android.synthetic.main.actvity_add_produto.*

/**
 * Created by heitorgianastasio on 16/10/17.
 */
class AddProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_add_produto)

        btnSalvar.setOnClickListener {
            if (!etNome.text.isEmpty() && !etPreco.text.isEmpty())
                ProdutoDAO(DBHelper(this))
                        .save(Produto(
                                preco = etPreco.text.toString().toDouble(),
                                nome = etNome.text.toString()
                        ))
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    companion object {
        val RESULT_OK = 1
    }
}