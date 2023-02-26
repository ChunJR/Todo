package com.example.todo.di

import com.example.todo.core.data.repository.TodoDataSource
import com.example.todo.core.interactors.TodoRepoImpl
import org.koin.dsl.module

val dataModule = module {
    single<TodoDataSource> { TodoRepoImpl( get() ) }
}