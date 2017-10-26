package com.hgianastasio.kgarconbagual.util

import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * Created by filipenunes on 10/19/17.
 */

public class StringUtil private constructor() {
    init { println("This ($this) is a singleton") }

    private object Holder { val INSTANCE = StringUtil() }

    companion object {
        val instance: StringUtil by lazy { Holder.INSTANCE }
    }
    val decimalFormat = DecimalFormat("#,##0.00")

    fun entryToFormatedSpannable(decimalValue: String): SpannableString {
        try {
            var cleanString = removeBRCurrencyAndSpecialChars(decimalValue)
            cleanString = addZeroWhetherNeeded(decimalValue, cleanString)

            val parsedDecimal = parseStringToBigDecimal(cleanString)

            return addBRCurrencyAndRigthSizes(decimalFormat.format(parsedDecimal))
        } catch (numberFormatException: NumberFormatException) {
            val parsedDecimal = parseStringToBigDecimal("0")
            return addBRCurrencyAndRigthSizes(decimalFormat.format(parsedDecimal))
        }

    }

    fun removeBRCurrencyAndSpecialChars(text: String?): String {
        return text?.replace("[$,.R ]".toRegex(), "") ?: ""
    }

    fun addZeroWhetherNeeded(decimalValue: String, cleanString: String): String {
        var cleanString = cleanString
        val decimal = decimalValue.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (decimal.size > 0) {
            if (decimal[decimal.size - 1].length == 1)
                cleanString += "0"
        } else
            cleanString += "00"
        return cleanString
    }

    fun parseStringToBigDecimal(value: String): BigDecimal {
        return BigDecimal(value)
                .setScale(2, BigDecimal.ROUND_FLOOR)
                .divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
    }


    fun addBRCurrencyAndRigthSizes(text: String?): SpannableString {
        return if (text != null)
            addRightSizes(addBRCurrency(text))
        else
            SpannableString("")
    }

    fun addRightSizes(text: String): SpannableString {
        val spannableString = SpannableString(text)
        spannableString.setSpan(RelativeSizeSpan(1.5f), 3, text.length - 3, 0)
        return spannableString
    }

    fun addBRCurrency(text: String): String {
        return "R$ $text"
    }

    fun unmask(value: String?): Double {
        return parseStringToBigDecimal(value?.replace("[^\\d]".toRegex(), "") ?: "").toDouble()
    }



}