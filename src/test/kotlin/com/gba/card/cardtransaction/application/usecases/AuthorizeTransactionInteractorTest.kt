package com.gba.card.cardtransaction.application.usecases

import com.gba.card.cardtransaction.application.gateways.AccountBalanceRepositoryGateway
import com.gba.card.cardtransaction.application.gateways.TransactionRepositoryGateway
import com.gba.card.cardtransaction.domain.entity.AccountBalance
import com.gba.card.cardtransaction.domain.entity.Transaction
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorizeTransactionInteractorTest {
    private lateinit var accountBalanceRepository: AccountBalanceRepositoryGateway
    private lateinit var transactionRepositoryGateway: TransactionRepositoryGateway
    private lateinit var interactor: AuthorizeTransactionInteractor

    @BeforeAll
    fun setUp() {
        accountBalanceRepository = mockk()
        transactionRepositoryGateway = mockk()
        interactor = AuthorizeTransactionInteractor(accountBalanceRepository, transactionRepositoryGateway)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(accountBalanceRepository, transactionRepositoryGateway)
    }

    @Test
    fun `should return 00 when food transaction is successful by mcc code`() = runBlocking {
        val transaction = Transaction(id = 1, accountId = 1L, amount = 50.0, mcc = "5411", merchant = "carrefour")
        val accountBalance = AccountBalance(id = 1L, foodTotal = 100.0, mealTotal = 50.0, cashTotal = 200.0)

        coEvery { accountBalanceRepository.findById(transaction.accountId) } returns accountBalance
        coEvery { accountBalanceRepository.saveOrUpdate(any()) } returns accountBalance
        coEvery { transactionRepositoryGateway.saveOrUpdate(transaction) } returns transaction

        val result = interactor.execute(transaction)

        assertEquals("00", result)
        coVerify { accountBalanceRepository.saveOrUpdate(match { it.foodTotal == 50.0 }) }
        coVerify { transactionRepositoryGateway.saveOrUpdate(transaction) }
    }

    @Test
    fun `should return 00 when meal transaction is successful by mcc code`() = runBlocking {
        val transaction = Transaction(id = 1, accountId = 1L, amount = 50.0, mcc = "5812", merchant = "gingerbread")
        val accountBalance = AccountBalance(id = 1L, foodTotal = 100.0, mealTotal = 50.0, cashTotal = 200.0)

        coEvery { accountBalanceRepository.findById(transaction.accountId) } returns accountBalance
        coEvery { accountBalanceRepository.saveOrUpdate(any()) } returns accountBalance
        coEvery { transactionRepositoryGateway.saveOrUpdate(transaction) } returns transaction

        val result = interactor.execute(transaction)

        assertEquals("00", result)
        coVerify { accountBalanceRepository.saveOrUpdate(match { it.mealTotal == 0.0 }) }
        coVerify { transactionRepositoryGateway.saveOrUpdate(transaction) }
    }

    @Test
    fun `should return 00 when food transaction is successful by merchant name`() = runBlocking {
        val transaction = Transaction(id = 1, accountId = 1L, amount = 100.0, mcc = "1", merchant = "mercadinho do ze")
        val accountBalance = AccountBalance(id = 1L, foodTotal = 101.00, mealTotal = 50.0, cashTotal = 200.0)

        coEvery { accountBalanceRepository.findById(transaction.accountId) } returns accountBalance
        coEvery { accountBalanceRepository.saveOrUpdate(any()) } returns accountBalance
        coEvery { transactionRepositoryGateway.saveOrUpdate(transaction) } returns transaction

        val result = interactor.execute(transaction)

        assertEquals("00", result)
        coVerify { accountBalanceRepository.saveOrUpdate(match { it.foodTotal == 1.00 }) }
        coVerify { transactionRepositoryGateway.saveOrUpdate(transaction) }
    }

    @Test
    fun `should return 00 when cash transaction is successful`() = runBlocking {
        val transaction = Transaction(id = 1, accountId = 1L, amount = 157.0, mcc = "1", merchant = "game play")
        val accountBalance = AccountBalance(id = 1L, foodTotal = 101.00, mealTotal = 50.0, cashTotal = 200.0)

        coEvery { accountBalanceRepository.findById(transaction.accountId) } returns accountBalance
        coEvery { accountBalanceRepository.saveOrUpdate(any()) } returns accountBalance
        coEvery { transactionRepositoryGateway.saveOrUpdate(transaction) } returns transaction

        val result = interactor.execute(transaction)

        assertEquals("00", result)
        coVerify { accountBalanceRepository.saveOrUpdate(match { it.cashTotal == 43.00 }) }
        coVerify { transactionRepositoryGateway.saveOrUpdate(transaction) }
    }

    @Test
    fun `should return 51 when transaction amount exceeds balance`() = runBlocking {
        val transaction = Transaction(id = 1, accountId = 1L, amount = 150.0, mcc = "5411", merchant = "mercado")
        val accountBalance = AccountBalance(id = 1L, foodTotal = 100.0, mealTotal = 50.0, cashTotal = 200.0)

        coEvery { accountBalanceRepository.findById(transaction.accountId) } returns accountBalance
        coEvery { transactionRepositoryGateway.saveOrUpdate(transaction) } returns transaction

        val result = interactor.execute(transaction)

        assertEquals("51", result)
        coVerify(exactly = 0) { accountBalanceRepository.saveOrUpdate(any()) }
        coVerify(exactly = 1) { transactionRepositoryGateway.saveOrUpdate(transaction) }
    }

    @Test
    fun `should return 07 when account is not found`() = runBlocking {
        val transaction = Transaction(id = 1, accountId = 1L, amount = 50.0, mcc = "5411", merchant = "mercado")

        coEvery { accountBalanceRepository.findById(transaction.accountId) } returns null

        val result = interactor.execute(transaction)

        assertEquals("07", result)
        coVerify(exactly = 0) { accountBalanceRepository.saveOrUpdate(any()) }
        coVerify(exactly = 0) { transactionRepositoryGateway.saveOrUpdate(transaction) }
    }

    @Test
    fun `should return 07 when amount is not valid`() = runBlocking {
        val transaction = Transaction(id = 1, accountId = 1L, amount = 0.0, mcc = "5411", merchant = "mercado")

        val result = interactor.execute(transaction)

        assertEquals("07", result)
    }
}