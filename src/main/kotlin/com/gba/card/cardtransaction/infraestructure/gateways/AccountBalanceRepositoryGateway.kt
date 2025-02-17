package com.gba.card.cardtransaction.infraestructure.gateways

import com.gba.card.cardtransaction.application.gateways.AccountBalanceRepositoryGateway
import com.gba.card.cardtransaction.domain.entity.AccountBalance
import com.gba.card.cardtransaction.infraestructure.persistence.AccountBalanceRepository
import com.gba.card.cardtransaction.infraestructure.persistence.entity.mapper.AccountBalanceEntityMapper.toDomain
import com.gba.card.cardtransaction.infraestructure.persistence.entity.mapper.AccountBalanceEntityMapper.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AccountBalanceRepositoryGateway(private val accountBalanceRepository: AccountBalanceRepository)
    : AccountBalanceRepositoryGateway
{
    override fun saveOrUpdate(accountBalance: AccountBalance) = toDomain(
        accountBalanceRepository.save(toEntity(accountBalance))
    )

    override fun findById(id: Long) = accountBalanceRepository.findByIdOrNull(id)?.let { toDomain(it) }

    override fun findAll() = accountBalanceRepository.findAll().map { account -> toDomain(account) }
}