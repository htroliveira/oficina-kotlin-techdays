package com.hgianastasio.kgarconbagual.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.hgianastasio.kgarconbagual.R
import com.hgianastasio.kgarconbagual.data.dao.ProdutoDAO
import com.hgianastasio.kgarconbagual.data.database.DBHelper
import com.hgianastasio.kgarconbagual.data.models.Produto
import kotlinx.android.synthetic.main.activity_finalize.*

/**
 * Created by heitorgianastasio on 16/10/17.
 */
class FinalizeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalize)
        var total = 0.toDouble()



        val adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                ProdutoDAO(DBHelper(this))
                        .getAll()
                        .filter { intent.getIntExtra(it.id.toString(), 0) > 0 }
                        .map{
                            val qtd = intent.getIntExtra(it.id.toString(), 0)
                            val prodTotal = it.preco.times(qtd)
                            total += prodTotal
                            "${it.nome}(R$${it.preco.format(2)}) x $qtd = ${prodTotal.format(2)}"
                        }
        )

        lvProdutos.adapter = adapter
        tvTotal.text = "R$${total.format(2)}"
        btnCalcTroco.setOnClickListener{
            if(!etDinheiro.text.isEmpty()) {
                val dinheiro = etDinheiro.text.toString().toDouble();
                if (dinheiro < total)
                    Toast.makeText(this, "O dinheiro não é suficiente", Toast.LENGTH_SHORT).show()
                else
                    tvTroco.text = "R$${dinheiro.minus(total).format(2)}"
            }
        }

    }

    private fun Double.format(digits:Int) = String.format("%.${digits}f",this)


}