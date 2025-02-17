package com.gba.card.cardtransaction.application.usecases

import com.gba.card.cardtransaction.application.gateways.TransactionRepositoryGateway
import com.gba.card.cardtransaction.domain.entity.Transaction
import com.gba.card.cardtransaction.infraestructure.persistence.TransactionRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.assertEquals
import com.gba.card.cardtransaction.infraestructure.gateways.TransactionRepositoryGateway as TransactionRepositoryGatewayP

@DataJpaTest
class FindAllTransactionsInteractorTest {

    @Autowired
    private lateinit var transactionRepository: TransactionRepository
    private lateinit var transactionRepositoryGateway: TransactionRepositoryGateway
    private lateinit var interactor: FindAllTransactionsInteractor;

    @BeforeEach
    fun setUp() {
     transactionRepositoryGateway = TransactionRepositoryGatewayP(transactionRepository)
     interactor = FindAllTransactionsInteractor(transactionRepositoryGateway)
    }

    @Test
    fun `should return all Transactions`() {
     val foodTransaction = Transaction(accountId = 1L, amount = 50.0, mcc = "5411", merchant = "burg")
     val mealTransaction = Transaction(accountId = 2L, amount = 105.50, mcc = "5812", merchant = "mercadinho")
     listOf(foodTransaction, mealTransaction).forEach(transactionRepositoryGateway::saveOrUpdate)

     val transactionsReturned = interactor.execute()

     assertEquals(2, transactionsReturned.size)
     assertEquals(foodTransaction.copy(id = 1L), transactionsReturned[0]);
     assertEquals(mealTransaction.copy(id = 2L), transactionsReturned[1]);
    }
}