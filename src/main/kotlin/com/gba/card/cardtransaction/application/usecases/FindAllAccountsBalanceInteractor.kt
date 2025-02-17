package com.gba.card.cardtransaction.application.usecases

import com.gba.card.cardtransaction.application.gateways.AccountBalanceRepositoryGateway
import org.springframework.stereotype.Service

@Service
class FindAllAccountsBalanceInteractor(private val accountBalanceRepositoryGateway: AccountBalanceRepositoryGateway) {
    fun execute() = accountBalanceRepositoryGateway.findAll()
}