package com.gba.card.cardtransaction.infraestructure.controllers.dto

data class TransactionResponseDTO(
    val id: Long,
    val accountId: Long,
    val amount: Double,
    val merchant: String,
    val mcc: String
)