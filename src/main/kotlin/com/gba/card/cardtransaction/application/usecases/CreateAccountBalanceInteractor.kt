package com.gba.card.cardtransaction.application.usecases

import com.gba.card.cardtransaction.application.gateways.AccountBalanceRepositoryGateway
import com.gba.card.cardtransaction.domain.entity.AccountBalance
import org.springframework.stereotype.Service

@Service
class CreateAccountBalanceInteractor(private val accountBalanceRepositoryGateway: AccountBalanceRepositoryGateway) {
    fun execute(accountBalance: AccountBalance) = accountBalanceRepositoryGateway.saveOrUpdate(accountBalance)
}