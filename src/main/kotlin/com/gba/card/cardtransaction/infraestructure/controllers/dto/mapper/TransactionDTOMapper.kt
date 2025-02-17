package com.gba.card.cardtransaction.infraestructure.controllers.dto.mapper

import com.gba.card.cardtransaction.domain.entity.Transaction
import com.gba.card.cardtransaction.infraestructure.controllers.dto.TransactionRequestDTO
import com.gba.card.cardtransaction.infraestructure.controllers.dto.TransactionResponseDTO

object TransactionDTOMapper {
    fun toDomain(dto: TransactionRequestDTO) =
        Transaction(
            accountId = dto.accountId,
            amount = dto.amount,
            merchant = dto.merchant,
            mcc = dto.mcc
        )

    fun toResponseDTO(domain: Transaction) =
        TransactionResponseDTO(
            id = domain.id!!,
            accountId = domain.accountId,
            amount = domain.amount,
            merchant = domain.merchant,
            mcc = domain.mcc
        )
}