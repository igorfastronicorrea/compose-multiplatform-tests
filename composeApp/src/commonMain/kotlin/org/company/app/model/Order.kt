package org.company.app.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: String,
    val name: String
)