package com.example.todo.core.data.repository

import com.example.todo.core.domain.Contact
import com.example.todo.core.domain.Product
import io.reactivex.Single

interface TodoDataSource {
    fun getContacts(): Single<List<Contact>>
    fun getProducts(): Single<List<Product>>
}