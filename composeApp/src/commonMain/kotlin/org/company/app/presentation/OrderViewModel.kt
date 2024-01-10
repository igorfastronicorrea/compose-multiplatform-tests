package org.company.app.presentation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.company.app.model.Order
import org.company.app.repository.OrdersRepository

data class OrderUIState(
    val orders : List<Order> = emptyList()
)

class OrderViewModel(val repository: OrdersRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<OrderUIState> =
        MutableStateFlow<OrderUIState>(OrderUIState())
    val uiState: StateFlow<OrderUIState> = _uiState.asStateFlow()

    init {
        updateOrders()
    }

    fun updateOrders(){
        viewModelScope.launch {
            val orders: List<Order> = repository.fetchOrders()
            _uiState.update {
                it.copy(orders)
            }
        }

    }

}