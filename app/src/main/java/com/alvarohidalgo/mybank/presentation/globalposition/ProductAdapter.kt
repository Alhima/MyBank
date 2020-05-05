package com.alvarohidalgo.mybank.presentation.globalposition

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarohidalgo.mybank.R
import com.alvarohidalgo.mybank.base.presentation.getBalanceTextColor
import com.alvarohidalgo.mybank.domain.model.Product
import kotlinx.android.synthetic.main.item_account.view.*
import kotlinx.android.synthetic.main.item_card.view.*


class ProductAdapter(
    private val productList: List<Product>,
    private val onProductClick: (product: Product) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PRODUCT_ACCOUNT ->
                AccountViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.item_account,
                            parent,
                            false
                        )
                )
            else ->
                CardViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_card,
                        parent,
                        false
                    )
                )
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardViewHolder -> holder.bind(productList[position] as Product.Card, onProductClick)
            is AccountViewHolder -> holder.bind(productList[position] as Product.Account, onProductClick)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (productList[position]) {
            is Product.Card -> PRODUCT_CARD
            is Product.Account -> PRODUCT_ACCOUNT
        }
    }

    companion object {
        const val PRODUCT_ACCOUNT = 0
        const val PRODUCT_CARD = 1
    }
}


class AccountViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

    @SuppressLint("SetTextI18n")
    fun bind(account: Product.Account,onAccountClick: (product: Product) -> Unit ) {
        with(itemView) {
            accountContainer.setOnClickListener {
                onAccountClick(account)
            }
            accountBalance.setTextColor(account.balance.value.getBalanceTextColor(v.context))
            accountBalance.text = account.balance.value.toString() + account.balance.currency
            accountAlias.text = account.alias
            accountIban.text = account.iban
        }
    }
}


class CardViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

    @SuppressLint("SetTextI18n")
    fun bind(card: Product.Card, onCardClick: (product: Product) -> Unit) {
        with(itemView) {
            cardContainer.setOnClickListener {
                onCardClick(card)
            }
            cardBalance.setTextColor(card.avalaible.value.getBalanceTextColor(v.context))
            cardBalance.text = card.avalaible.value.toString() + card.avalaible.currency
            cardAlias.text = card.alias
            cardPan.text = card.pan
        }
    }
}

