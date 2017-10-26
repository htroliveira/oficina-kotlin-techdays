package com.hgianastasio.kgarconbagual.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hgianastasio.kgarconbagual.R
import com.hgianastasio.kgarconbagual.databinding.ActvityAddProdutoBinding
import com.hgianastasio.kgarconbagual.interaction.AddProductInteraction
import com.hgianastasio.kgarconbagual.viewmodel.AddProductViewModel

/**
 * Created by heitorgianastasio on 16/10/17.
 */
class AddProductActivity : AppCompatActivity(), AddProductInteraction {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActvityAddProdutoBinding =
                DataBindingUtil.setContentView(this, R.layout.actvity_add_produto)

        binding.addProductVM = AddProductViewModel(this, this)
    }

    override fun saveProducts() {
        setResult(RESULT_OK,intent)
        finish()
    }

    companion object {
        val RESULT_OK = 1
    }
}