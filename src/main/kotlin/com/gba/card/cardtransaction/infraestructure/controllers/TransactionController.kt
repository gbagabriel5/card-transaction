package com.gba.card.cardtransaction.infraestructure.controllers

import com.gba.card.cardtransaction.infraestructure.controllers.dto.TransactionRequestDTO
import com.gba.card.cardtransaction.infraestructure.controllers.dto.TransactionResponseDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("transaction")
interface TransactionController {
    @PostMapping
    @Operation(description = "Cria nova Transação de Cartão de Crédito")
    fun authorizeTransaction(@RequestBody transaction: TransactionRequestDTO) : ResponseEntity<String>

    @GetMapping
    @Operation(description = "Retorna Transações")
    fun findAll(): ResponseEntity<List<TransactionResponseDTO>>
}