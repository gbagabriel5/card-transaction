package com.gba.card.cardtransaction.infraestructure.gateways

import com.gba.card.cardtransaction.application.gateways.TransactionRepositoryGateway
import com.gba.card.cardtransaction.domain.entity.Transaction
import com.gba.card.cardtransaction.infraestructure.persistence.TransactionRepository
import com.gba.card.cardtransaction.infraestructure.persistence.entity.mapper.TransactionMapper.toDomain
import com.gba.card.cardtransaction.infraestructure.persistence.entity.mapper.TransactionMapper.toEntity
import org.springframework.stereotype.Component

@Component
class TransactionRepositoryGateway(private val transactionRepository: TransactionRepository)
    : TransactionRepositoryGateway
{
    override fun saveOrUpdate(transaction: Transaction) = toDomain(
        transactionRepository.save(toEntity(transaction))
    )

    override fun findAll(): List<Transaction> = transactionRepository.findAll()
        .map { transaction -> toDomain(transaction) }
}