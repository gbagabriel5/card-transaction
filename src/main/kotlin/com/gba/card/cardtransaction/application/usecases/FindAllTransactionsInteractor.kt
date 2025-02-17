package com.gba.card.cardtransaction.application.usecases

import com.gba.card.cardtransaction.application.gateways.TransactionRepositoryGateway
import org.springframework.stereotype.Service

@Service
class FindAllTransactionsInteractor(private val transactionRepositoryGateway: TransactionRepositoryGateway) {
    fun execute() = transactionRepositoryGateway.findAll()
}