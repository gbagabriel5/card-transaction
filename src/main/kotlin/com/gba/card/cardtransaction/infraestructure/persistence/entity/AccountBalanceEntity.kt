package com.gba.card.cardtransaction.infraestructure.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "ACCOUNT_BALANCE")
data class AccountBalanceEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    var foodTotal: Double,
    var mealTotal: Double,
    var cashTotal: Double,
    @Version var version: Long? = 0
) {
    constructor() : this(null, 0.0, 0.0, 0.0)
}
