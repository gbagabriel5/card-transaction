package com.gba.card.cardtransaction.infraestructure.persistence

import com.gba.card.cardtransaction.infraestructure.persistence.entity.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<TransactionEntity, Long>