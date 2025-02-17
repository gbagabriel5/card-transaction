package com.gba.card.cardtransaction.infraestructure.controllers.impl

import com.gba.card.cardtransaction.application.usecases.CreateAccountBalanceInteractor
import com.gba.card.cardtransaction.application.usecases.FindAllAccountsBalanceInteractor
import com.gba.card.cardtransaction.infraestructure.controllers.AccountBalanceController
import com.gba.card.cardtransaction.infraestructure.controllers.dto.AccountBalanceRequestDTO
import com.gba.card.cardtransaction.infraestructure.controllers.dto.mapper.AccountBalanceDTOMapper.toDTO
import com.gba.card.cardtransaction.infraestructure.controllers.dto.mapper.AccountBalanceDTOMapper.toDomain
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountBalanceControllerImpl(
    private val createAccountBalanceInteractor: CreateAccountBalanceInteractor,
    private val findAllAccountsBalanceInteractor: FindAllAccountsBalanceInteractor
) : AccountBalanceController {

    override fun create(accountBalanceDto: AccountBalanceRequestDTO) = ResponseEntity(toDTO(
        createAccountBalanceInteractor.execute(toDomain(accountBalanceDto))
    ), HttpStatus.CREATED)

    override fun findAll() = ok(findAllAccountsBalanceInteractor.execute().map { account -> toDTO(account) })
}