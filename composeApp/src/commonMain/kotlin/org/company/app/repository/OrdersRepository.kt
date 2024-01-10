package org.company.app.repository

import org.company.app.model.Order

interface OrdersRepository {
    suspend fun fetchOrders(): List<Order>
}