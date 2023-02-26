package com.example.todo.features.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.core.data.repository.TodoDataSource
import com.example.todo.core.domain.Contact
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val todoRepo: TodoDataSource) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState> get() = _state


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getContacts() {
        val disposable = todoRepo.getContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _state.value = HomeViewState.onLoading }
            .subscribe(
                { contacts ->
                    _state.value = HomeViewState.onSuccessGetContact(contacts)
                },
                { error ->
                    error.message?.let {
                        _state.value = HomeViewState.onError(it)
                    }
                })

        compositeDisposable.add(disposable)
    }

    fun getProducts() {
        val disposable = todoRepo.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _state.value = HomeViewState.onLoading }
            .subscribe(
                { products ->
                    _state.value = HomeViewState.onSuccessGetProduct(products)
                },
                { error ->
                    error.message?.let {
                        _state.value = HomeViewState.onError(it)
                    }
                })

        compositeDisposable.add(disposable)
    }
}