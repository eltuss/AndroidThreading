package com.example.androidthreading

import com.google.gson.annotations.SerializedName

data class AstrosResult(
    @SerializedName("message") val craft: String,
    @SerializedName("number") val name: String,
    @SerializedName("people") val people: List<AstroPeople>
)