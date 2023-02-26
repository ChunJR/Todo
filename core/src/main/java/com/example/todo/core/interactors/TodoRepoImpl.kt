package com.example.todo.core.interactors

import com.example.todo.core.data.repository.TodoDataSource
import com.example.todo.core.data.repository.TodoService
import com.example.todo.core.domain.Contact
import com.example.todo.core.domain.Product
import io.reactivex.Single

class TodoRepoImpl(private val service: TodoService) : TodoDataSource {
    override fun getContacts(): Single<List<Contact>> {
        return service.getContactList()
    }

    override fun getProducts(): Single<List<Product>> {
        return service.getProductList()
    }
}