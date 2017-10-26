package com.hgianastasio.kgarconbagual.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.NumberPicker
import android.widget.TextView
import com.hgianastasio.kgarconbagual.R
import com.hgianastasio.kgarconbagual.model.Product

/**
 * Created by heitorgianastasio on 16/10/17.
 */
class ProductAdapter(
        val list: List<Product>,
        val context: Context
) : BaseAdapter(){
    val qtdMap = mutableMapOf<Long,Int>()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_produto,parent,false)
        val npQuantidade = view.findViewById<NumberPicker>(R.id.npQuantidade)
        val tvNome = view.findViewById<TextView>(R.id.tvNome)

        npQuantidade.minValue = 0
        npQuantidade.maxValue = 30

        npQuantidade.setOnValueChangedListener{_,_, newVal ->
            qtdMap.put(list[position].id,newVal)
        }

        tvNome.text = list[position].nome

        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = list[position].id

    override fun getCount(): Int = list.size

}