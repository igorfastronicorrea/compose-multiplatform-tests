package org.company.app.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import org.company.app.model.Order

class OrdersRepositoryImpl() : OrdersRepository  {

    private val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation){
            json()
        }
    }
    override suspend fun fetchOrders(): List<Order> {
        val orders =  httpClient.get("https://demo1201562.mockable.io/orders")
            .body<List<Order>>()
        return orders
    }
}