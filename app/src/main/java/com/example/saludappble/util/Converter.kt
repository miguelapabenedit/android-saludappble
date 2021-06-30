package com.example.saludappble.util

import androidx.databinding.InverseMethod

@InverseMethod("toLong")
fun toString(entero: Long): String? {
    return if (entero == 0L) "" else entero.toString()
}

fun toLong(string: String): Long {
    return if (string.isEmpty()) 0L else string.toLong()
}