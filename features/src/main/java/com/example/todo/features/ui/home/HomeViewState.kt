package com.example.todo.features.ui.home

import com.example.todo.core.domain.Contact
import com.example.todo.core.domain.Product

sealed class HomeViewState {
    object onLoading: HomeViewState()
    data class onSuccessGetContact(val contacts: List<Contact>): HomeViewState()
    data class onSuccessGetProduct(val products: List<Product>): HomeViewState()
    data class onError(val message: String): HomeViewState()
}