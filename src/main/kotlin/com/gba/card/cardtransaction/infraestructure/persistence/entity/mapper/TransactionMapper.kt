package com.gba.card.cardtransaction.infraestructure.persistence.entity.mapper

import com.gba.card.cardtransaction.domain.entity.Transaction
import com.gba.card.cardtransaction.infraestructure.persistence.entity.TransactionEntity

object TransactionMapper {
    fun toDomain(entity: TransactionEntity) =
        Transaction(
            id = entity.id,
            accountId = entity.accountId,
            amount = entity.amount,
            merchant = entity.merchant,
            mcc = entity.mcc
        )

    fun toEntity(domain: Transaction) =
        TransactionEntity(
            id = domain.id ,
            domain.accountId,
            amount = domain.amount,
            merchant = domain.merchant,
            mcc = domain.mcc
        )
}