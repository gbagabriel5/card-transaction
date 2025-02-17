package com.gba.card.cardtransaction.infraestructure.controllers.impl

import com.gba.card.cardtransaction.application.usecases.AuthorizeTransactionInteractor
import com.gba.card.cardtransaction.application.usecases.FindAllTransactionsInteractor
import com.gba.card.cardtransaction.infraestructure.controllers.TransactionController
import com.gba.card.cardtransaction.infraestructure.controllers.dto.mapper.TransactionDTOMapper.toDomain
import com.gba.card.cardtransaction.infraestructure.controllers.dto.mapper.TransactionDTOMapper.toResponseDTO
import com.gba.card.cardtransaction.infraestructure.controllers.dto.TransactionRequestDTO
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.RestController

@RestController
class TransactionControllerImpl(
    private val authorizeTransactionInteractor: AuthorizeTransactionInteractor,
    private val findAllTransactionsInteractor: FindAllTransactionsInteractor
) : TransactionController
{
    override fun authorizeTransaction(transaction: TransactionRequestDTO) : ResponseEntity<String> = runBlocking {
        ok(authorizeTransactionInteractor.execute(toDomain(transaction)))
    }

    override fun findAll() = ok(findAllTransactionsInteractor.execute()
        .map { transaction -> toResponseDTO(transaction) })
}