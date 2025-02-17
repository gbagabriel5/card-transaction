package com.gba.card.cardtransaction.infraestructure.controllers.dto

data class AccountBalanceDTO(
    val id: Long,
    var foodTotal: Double,
    var mealTotal: Double,
    var cashTotal: Double
)