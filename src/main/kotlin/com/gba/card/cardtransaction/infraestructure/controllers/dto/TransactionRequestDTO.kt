package com.gba.card.cardtransaction.infraestructure.controllers.dto

data class TransactionRequestDTO(
    val accountId: Long,
    val amount: Double,
    val merchant: String,
    val mcc: String
)