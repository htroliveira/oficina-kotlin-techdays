package com.hgianastasio.kgarconbagual.binding.model

import android.widget.EditText
import com.hgianastasio.kgarconbagual.binding.textwatcher.CurrencyFormatWatcher

/**
 * Created by filipenunes on 10/19/17.
 */
object Mask {

    val DECIMAL = "decimal"

    fun insert(mask: String, editText: EditText): CurrencyFormatWatcher? {
        return if (mask == DECIMAL) {
            CurrencyFormatWatcher(editText)
        } else null
    }

    fun apply(currentMask: String, str: String): String {
        var stringFormated = ""

        var i = 0
        for (m in currentMask.toCharArray()) {
            if (m != '#') {
                stringFormated += m
                continue
            }

            try {
                stringFormated += str[i]
            } catch (e: Exception) {
                break
            }

            i++
        }
        return stringFormated
    }

}