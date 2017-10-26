package com.hgianastasio.kgarconbagual.binding.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.hgianastasio.kgarconbagual.util.StringUtil
import java.lang.ref.WeakReference

/**
 * Created by filipenunes on 10/19/17.
 */

class CurrencyFormatWatcher(editText: EditText) : TextWatcher {

    var editTextWeakReference: WeakReference<EditText> = WeakReference(editText)

    override fun afterTextChanged(editable: Editable?) {
        try {
            val editText = editTextWeakReference.get() ?: return
            editText.removeTextChangedListener(this)

            val decimalValueFormated = StringUtil.instance.entryToFormatedSpannable(editable.toString())
            editText.setText(decimalValueFormated)
            editText.setSelection(decimalValueFormated.length)
            editText.addTextChangedListener(this)
        } catch (numberFormatException: NumberFormatException) {

        }


    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}

