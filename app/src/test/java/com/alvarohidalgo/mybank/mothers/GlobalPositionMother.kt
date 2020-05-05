package com.alvarohidalgo.mybank.mothers

import com.alvarohidalgo.mybank.domain.model.Balance
import com.alvarohidalgo.mybank.domain.model.GlobalPosition
import com.alvarohidalgo.mybank.domain.model.Product

object GlobalPositionMother {

    fun createGlobalPosition(
        name: String = "Mock",
        cardList: List<Product.Card> = listOf(),
        accountList: List<Product.Account> = listOf()
    ): GlobalPosition {
        return GlobalPosition(
            name = name,
            products = cardList + accountList
        )
    }


    fun createCard(
        pan: String = "",
        alias: String = "",
        balance: Balance = createBalance()
    ): Product.Card {
        return Product.Card(
            pan = pan,
            alias = alias,
            avalaible = balance
        )
    }


    fun createAccount(
        iban: String = "",
        alias: String = "",
        balance: Balance = createBalance()

    ): Product.Account {
        return Product.Account(
            iban = iban,
            alias = alias,
            balance = balance
        )
    }

    fun createBalance(
        value: Double = 0.0,
        currency: String = "EUR"
    ): Balance {
        return Balance(value, currency)
    }
}