package com.gba.card.cardtransaction.infraestructure.controllers

import com.gba.card.cardtransaction.infraestructure.controllers.dto.AccountBalanceDTO
import com.gba.card.cardtransaction.infraestructure.controllers.dto.AccountBalanceRequestDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("account-balance")
interface AccountBalanceController {

    @PostMapping
    @Operation(description = "Cria novo Saldo de Conta")
    @ApiResponses(
        value = [ApiResponse(responseCode = "201", description = "Criado com sucesso!")]
    )
    fun create(@RequestBody accountBalanceDto: AccountBalanceRequestDTO) : ResponseEntity<AccountBalanceDTO>

    @GetMapping
    @Operation(description = "Retorna Saldos de Contas")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Criado com sucesso!")]
    )
    fun findAll(): ResponseEntity<List<AccountBalanceDTO>>
}