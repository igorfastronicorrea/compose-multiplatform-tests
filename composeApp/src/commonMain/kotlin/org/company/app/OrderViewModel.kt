package org.company.app

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.company.app.model.Order

data class OrderUIState(
    val orders : List<Order> = emptyList()
)

class OrderViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<OrderUIState> =
        MutableStateFlow<OrderUIState>(OrderUIState())
    val uiState: StateFlow<OrderUIState> = _uiState.asStateFlow()


    init {
        updateOrders()
    }


    fun updateOrders(){
        viewModelScope.launch {
            val orders: List<Order> = getOrders()
            _uiState.update {
                it.copy(orders)
            }
        }

    }

    val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation){
            json()
        }
    }

    private suspend fun getOrders(): List<Order> {
        val orders =  httpClient.get("https://demo1201562.mockable.io/orders")
            .body<List<Order>>()
        return orders
    }

}