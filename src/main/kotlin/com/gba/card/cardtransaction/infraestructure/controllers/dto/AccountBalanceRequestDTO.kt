package com.gba.card.cardtransaction.infraestructure.controllers.dto

data class AccountBalanceRequestDTO (
    var foodTotal: Double,
    var mealTotal: Double,
    var cashTotal: Double
)