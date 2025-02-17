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
class FindAllAccountsBalanceInteractorTest {

    @Autowired
    private lateinit var accountBalanceRepository: AccountBalanceRepository
    private lateinit var accountBalanceRepositoryGateway: AccountBalanceRepositoryGateway
    private lateinit var interactor: FindAllAccountsBalanceInteractor;

    @BeforeEach
    fun setUp() {
         accountBalanceRepositoryGateway = AccountBalanceRepositoryGatewayP(accountBalanceRepository)
         interactor = FindAllAccountsBalanceInteractor(accountBalanceRepositoryGateway)
    }

    @Test
    fun `should return all Account Balances`() {
        val accountBalance = AccountBalance(foodTotal = 100.0, mealTotal = 50.0, cashTotal = 200.0)
        val accountBalance2 = AccountBalance(foodTotal = 40.0, mealTotal = 60.0, cashTotal = 500.0)
        listOf(accountBalance, accountBalance2).forEach(accountBalanceRepositoryGateway::saveOrUpdate)

        val accountBalancesReturned = interactor.execute()

        assertEquals(2, accountBalancesReturned.size)
        assertEquals(accountBalance.copy(id = 1L), accountBalancesReturned[0]);
        assertEquals(accountBalance2.copy(id = 2L), accountBalancesReturned[1]);
    }
}