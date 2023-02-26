package com.example.todo.core.data.repository

import com.example.todo.core.domain.Contact
import com.example.todo.core.domain.Product
import io.reactivex.Single
import retrofit2.http.GET


interface TodoService {

    @GET("call")
    fun getContactList(): Single<List<Contact>>

    @GET("buy")
    fun getProductList(): Single<List<Product>>
}