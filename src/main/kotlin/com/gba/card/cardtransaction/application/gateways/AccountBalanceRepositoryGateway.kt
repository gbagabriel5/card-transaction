package com.gba.card.cardtransaction.application.gateways

import com.gba.card.cardtransaction.domain.entity.AccountBalance

interface AccountBalanceRepositoryGateway {
    fun saveOrUpdate(accountBalance: AccountBalance) : AccountBalance
    fun findById(id: Long) : AccountBalance?
    fun findAll() : List<AccountBalance>
}