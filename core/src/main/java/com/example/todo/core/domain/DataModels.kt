package com.example.todo.core.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("number") var number: String? = null
) : Parcelable

@Parcelize
data class Product(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("quantity") var quantity: Int? = null,
    @SerializedName("type") var type: Int? = null,
) : Parcelable


