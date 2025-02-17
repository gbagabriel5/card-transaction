package com.gba.card.cardtransaction.domain.entity

data class Transaction(
    val id: Long? = null,
    val accountId: Long,
    val amount: Double,
    val merchant: String,
    val mcc: String
)