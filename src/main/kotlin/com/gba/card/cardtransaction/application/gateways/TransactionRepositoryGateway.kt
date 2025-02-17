package com.gba.card.cardtransaction.application.gateways

import com.gba.card.cardtransaction.domain.entity.Transaction

interface TransactionRepositoryGateway {
    fun saveOrUpdate(transaction: Transaction) : Transaction
    fun findAll() : List<Transaction>
}