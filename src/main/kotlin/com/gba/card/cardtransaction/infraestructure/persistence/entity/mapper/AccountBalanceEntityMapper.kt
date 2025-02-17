package com.gba.card.cardtransaction.infraestructure.persistence.entity.mapper

import com.gba.card.cardtransaction.domain.entity.AccountBalance
import com.gba.card.cardtransaction.infraestructure.persistence.entity.AccountBalanceEntity

object AccountBalanceEntityMapper {
     fun toDomain(entity: AccountBalanceEntity) =
        AccountBalance(
            id = entity.id!!,
            foodTotal = entity.foodTotal,
            mealTotal = entity.mealTotal,
            cashTotal = entity.cashTotal,
            version = entity.version
        )

    fun toEntity(domain: AccountBalance) =
        AccountBalanceEntity(
            id = domain.id,
            foodTotal = domain.foodTotal,
            mealTotal = domain.mealTotal,
            cashTotal = domain.cashTotal,
            version = domain.version
        )
}