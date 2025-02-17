package com.gba.card.cardtransaction.infraestructure.persistence.entity

import jakarta.persistence.*

@Entity(name = "TRANSACTION")
data class TransactionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val accountId: Long,
    val amount: Double,
    val merchant: String,
    val mcc: String,
    @Version var version: Long? = null
) {
    constructor() :this (id = null, accountId = 0L, amount = 0.0, merchant = "", mcc = "")
}