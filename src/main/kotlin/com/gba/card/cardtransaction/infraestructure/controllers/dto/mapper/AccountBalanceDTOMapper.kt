package com.gba.card.cardtransaction.infraestructure.controllers.dto.mapper

import com.gba.card.cardtransaction.domain.entity.AccountBalance
import com.gba.card.cardtransaction.infraestructure.controllers.dto.AccountBalanceDTO
import com.gba.card.cardtransaction.infraestructure.controllers.dto.AccountBalanceRequestDTO

object AccountBalanceDTOMapper {
    fun toDomain(dto: AccountBalanceRequestDTO) =
        AccountBalance(
            foodTotal = dto.foodTotal,
            mealTotal = dto.mealTotal,
            cashTotal = dto.cashTotal
        )

    fun toDTO(domain: AccountBalance) =
        AccountBalanceDTO(
            id = domain.id!!,
            foodTotal = domain.foodTotal,
            mealTotal = domain.mealTotal,
            cashTotal = domain.cashTotal
        )
}