package com.gba.card.cardtransaction.domain.entity

data class AccountBalance(
    val id: Long? = null,
    var foodTotal: Double,
    var mealTotal: Double,
    var cashTotal: Double,
    var version: Long? = 0
)