package com.gba.card.cardtransaction.application.usecases

import com.gba.card.cardtransaction.application.gateways.AccountBalanceRepositoryGateway
import com.gba.card.cardtransaction.domain.entity.AccountBalance
import com.gba.card.cardtransaction.infraestructure.persistence.AccountBalanceRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.assertEquals
import com.gba.card.cardtransaction.infraestructure.gateways.AccountBalanceRepositoryGateway as AccountBalanceRepositoryGatewayP

@DataJpaTest
class CreateAccountBalanceInteractorTest {
    @Autowired
    private lateinit var accountBalanceRepository: AccountBalanceRepository
    private lateinit var accountBalanceRepositoryGateway: AccountBalanceRepositoryGateway
    private lateinit var interactor: CreateAccountBalanceInteractor;

    @BeforeEach
    fun setUp() {
     accountBalanceRepositoryGateway = AccountBalanceRepositoryGatewayP(accountBalanceRepository)
     interactor = CreateAccountBalanceInteractor(accountBalanceRepositoryGateway)
    }

    @Test
    fun `should create Account Balance`() {
     val accountBalance = AccountBalance(foodTotal = 100.0, mealTotal = 50.0, cashTotal = 200.0)

     val accountBalanceReturned = interactor.execute(accountBalance)

     assertEquals(accountBalance.copy(id = 1L), accountBalanceReturned);
    }
}