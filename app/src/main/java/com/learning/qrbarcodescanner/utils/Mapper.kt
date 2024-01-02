package com.learning.qrbarcodescanner.utils

interface Mapper<in I, out O> {
    fun map(input: I): O
}