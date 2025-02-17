package com.gba.card.cardtransaction.application.usecases

import com.gba.card.cardtransaction.application.gateways.AccountBalanceRepositoryGateway
import com.gba.card.cardtransaction.application.gateways.TransactionRepositoryGateway
import com.gba.card.cardtransaction.domain.entity.AccountBalance
import com.gba.card.cardtransaction.domain.entity.Transaction
import com.gba.card.cardtransaction.domain.enum.CategoryEnum
import com.gba.card.cardtransaction.domain.enum.CategoryEnum.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.springframework.stereotype.Service

@Service
class AuthorizeTransactionInteractor(
    private val accountBalanceRepository : AccountBalanceRepositoryGateway,
    private val transactionRepository: TransactionRepositoryGateway
) {
    private val mutexMap = mutableMapOf<Long, Mutex>()

    suspend fun execute(transaction: Transaction) : String {
        val mutex = getMutexForAccount(transaction.accountId)

        return mutex.withLock {
            if (transaction.amount < 1) return "07"

            val category = getCategory(transaction.mcc, transaction.merchant);

            val accountBalance = accountBalanceRepository.findById(transaction.accountId) ?: return "07"

            val response = processTransaction(category, accountBalance, transaction)

            transactionRepository.saveOrUpdate(transaction)

            response
        }
    }

    private fun getMutexForAccount(accountId: Long): Mutex = mutexMap.computeIfAbsent(accountId) { Mutex() }

    private fun getCategory(mcc: String?, merchant: String): CategoryEnum {
        val possibleMerchFoodNames = listOf("merc", "super", "ataca", "conveni")
        val possibleMerchMealNames = listOf("eat", "lanche", "restaurant", "pub", "conveni")

        return when {
            (mcc == "5411" || mcc == "5412") -> FOOD
            (mcc == "5811" || mcc == "5812") -> MEAL
            possibleMerchFoodNames.any { merchant.contains(it, ignoreCase = true) } -> FOOD
            possibleMerchMealNames.any { merchant.contains(it, ignoreCase = true) } -> MEAL
            else -> CASH
        }
    }

    private fun processTransaction(
        category: CategoryEnum,
        accountBalance: AccountBalance,
        transaction: Transaction
    ) : String {
        when (category) {
            FOOD -> if (accountBalance.foodTotal >= transaction.amount) {
                accountBalance.foodTotal -= transaction.amount
                accountBalanceRepository.saveOrUpdate(accountBalance);
                return "00"
            }

            MEAL -> if (accountBalance.mealTotal >= transaction.amount) {
                accountBalance.mealTotal -= transaction.amount
                accountBalanceRepository.saveOrUpdate(accountBalance);
                return "00"
            }

            CASH -> if (accountBalance.cashTotal >= transaction.amount) {
                accountBalance.cashTotal -= transaction.amount
                accountBalanceRepository.saveOrUpdate(accountBalance);
                return "00"
            }
        }
        return "51"
    }
}
