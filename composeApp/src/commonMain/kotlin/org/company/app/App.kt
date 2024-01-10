package org.company.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.company.app.di.appModule
import org.company.app.presentation.OrderViewModel
import org.company.app.theme.AppTheme
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
internal fun App() {
    KoinApplication(application = {
        modules(appModule())
    }) {
        AppTheme {
            val ordersViewModel: OrderViewModel = koinInject()
            listView(ordersViewModel)
        }
    }
}

@Composable
fun listView(ordersViewModel: OrderViewModel) {
    val uiState by ordersViewModel.uiState.collectAsState()

    Spacer(modifier = Modifier.height(40.dp))
    Column {
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn {
            items(uiState.orders) { order ->
                Text("${order.name} ${order.id}")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { ordersViewModel.updateOrders() }) {
            Text("Update")
        }
    }

}


internal expect fun openUrl(url: String?)
