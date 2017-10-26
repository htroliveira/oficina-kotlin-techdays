package com.hgianastasio.kgarconbagual.binding

import android.databinding.BindingAdapter
import android.widget.EditText
import com.hgianastasio.kgarconbagual.binding.model.Mask

/**
 * Created by filipenunes on 10/19/17.
 */

object BindAdapter {

    @JvmStatic
    @BindingAdapter("android:mask")
    fun setMask(editText: EditText, mask: String) {
        editText.addTextChangedListener(Mask.insert(mask, editText))
    }
}