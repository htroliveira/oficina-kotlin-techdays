package com.hgianastasio.kgarconbagual.viewmodel

import android.content.Context
import android.databinding.ObservableField
import com.hgianastasio.kgarconbagual.data.dao.ProdutoDAO
import com.hgianastasio.kgarconbagual.data.database.DBHelper
import com.hgianastasio.kgarconbagual.interaction.AddProductInteraction
import com.hgianastasio.kgarconbagual.model.Product
import com.hgianastasio.kgarconbagual.util.StringUtil

/**
 * Created by filipenunes on 10/21/17.
 */
class AddProductViewModel constructor(context: Context, addProductInteraction: AddProductInteraction) {

    var name: ObservableField<String> = ObservableField();
    var price: ObservableField<String> = ObservableField();
    var addInteraction: AddProductInteraction = addProductInteraction;
    var context: Context = context;


    fun addProduct() {
            if (!name.get().isEmpty() && !price.get().isEmpty())
                ProdutoDAO(DBHelper(context))
                        .save(Product(
                                preco = StringUtil.instance.unmask(price.get().toString()),
                                nome = name.get().toString()
                        ))
           addInteraction.saveProducts()
    }
}