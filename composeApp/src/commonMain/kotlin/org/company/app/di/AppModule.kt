package org.company.app.di

import org.company.app.presentation.OrderViewModel
import org.company.app.repository.OrdersRepository
import org.company.app.repository.OrdersRepositoryImpl
import org.koin.dsl.module
fun appModule() = module {

    single<OrdersRepository> { OrdersRepositoryImpl() }

    factory { OrderViewModel(repository = get()) }

}