package com.hgianastasio.kgarconbagual.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.hgianastasio.kgarconbagual.R
import com.hgianastasio.kgarconbagual.data.dao.ProdutoDAO
import com.hgianastasio.kgarconbagual.data.database.DBHelper
import com.hgianastasio.kgarconbagual.view.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var adapter : ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = ProductAdapter(ProdutoDAO(DBHelper(this)).getAll(), this)
        lvProdutos.adapter = adapter
        btnFinalizar.setOnClickListener {
            finalize()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.addProduto_mi -> startActivityForResult(Intent(this, AddProductActivity::class.java), REQUSETCODE_ADD)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode== REQUSETCODE_ADD && resultCode == AddProductActivity.RESULT_OK){
            adapter = ProductAdapter(ProdutoDAO(DBHelper(this)).getAll(), this)
            lvProdutos.adapter = adapter
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }


    fun finalize(){
        val bundle = Bundle()
        adapter?.qtdMap?.forEach {
            bundle.putInt(it.key.toString(),it.value)
        }
        startActivity(Intent(this, FinalizeActivity::class.java).putExtras(bundle))
    }

    companion object {
        val REQUSETCODE_ADD = 123
    }
}
