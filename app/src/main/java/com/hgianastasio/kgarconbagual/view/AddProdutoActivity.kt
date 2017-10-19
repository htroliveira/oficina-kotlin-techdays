package com.hgianastasio.kgarconbagual.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hgianastasio.kgarconbagual.R
import com.hgianastasio.kgarconbagual.data.dao.ProdutoDAO
import com.hgianastasio.kgarconbagual.data.database.DBHelper
import com.hgianastasio.kgarconbagual.data.models.Produto
import com.hgianastasio.kgarconbagual.databinding.ActvityAddProdutoBinding
import com.hgianastasio.kgarconbagual.util.StringUtil
import kotlinx.android.synthetic.main.actvity_add_produto.*

/**
 * Created by heitorgianastasio on 16/10/17.
 */
class AddProdutoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActvityAddProdutoBinding =
                DataBindingUtil.setContentView(this, R.layout.actvity_add_produto)

        btnSalvar.setOnClickListener {
            if (!etNome.text.isEmpty() && !etPreco.text.isEmpty())
                ProdutoDAO(DBHelper(this))
                        .save(Produto(
                                preco = StringUtil.instance.unmask(etPreco.text.toString()),
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