package com.hgianastasio.kgarconbagual.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.hgianastasio.kgarconbagual.R
import com.hgianastasio.kgarconbagual.data.dao.ProdutoDAO
import com.hgianastasio.kgarconbagual.data.database.DBHelper
import com.hgianastasio.kgarconbagual.data.models.Produto
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var adapter : ProdutoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = ProdutoAdapter(ProdutoDAO(DBHelper(this)).getAll(),this)
        lvProdutos.adapter = adapter
        btnFinalizar.setOnClickListener {
            finalize()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.addProduto_mi -> startActivityForResult(Intent(this,AddProdutoActvity::class.java), REQUSETCODE_ADD)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode== REQUSETCODE_ADD && resultCode == AddProdutoActvity.RESULT_OK){
            adapter = ProdutoAdapter(ProdutoDAO(DBHelper(this)).getAll(),this)
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
        startActivity(Intent(this,FinalizeActvity::class.java).putExtras(bundle))
    }

    companion object {
        val REQUSETCODE_ADD = 123
    }
}
