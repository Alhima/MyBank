package com.alvarohidalgo.mybank.presentation.productdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.alvarohidalgo.mybank.R
import com.alvarohidalgo.mybank.base.presentation.BaseActivity
import com.alvarohidalgo.mybank.base.presentation.ViewmodelOwner
import com.alvarohidalgo.mybank.base.presentation.getBalanceTextColor
import com.alvarohidalgo.mybank.domain.model.Product
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivity : BaseActivity(),
    ViewmodelOwner<ProductDetailViewModel> {
    override val viewmodel: ProductDetailViewModel by viewModel()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val card = intent.extras?.getParcelable<Product.Card>(PRODUCT_CARD_EXTRA_KEY)
        val account = intent.extras?.getParcelable<Product.Account>(PRODUCT_ACCOUNT_EXTRA_KEY)
        viewmodel.setProduct(card ?: account)

        viewmodel.accounttLiveData.observe(this, Observer {
            supportActionBar?.setTitle(R.string.account)
            productName.text = it.alias
            productIdentifier.text = it.iban
            productBalance.setTextColor(it.balance.value.getBalanceTextColor(this))
            productBalance.text = it.balance.value.toString() + it.balance.currency
        })

        viewmodel.cardLiveData.observe(this, Observer {
            supportActionBar?.setTitle(R.string.card)
            productName.text = it.alias
            productIdentifier.text = it.pan
            productBalance.setTextColor(it.avalaible.value.getBalanceTextColor(this))
            productBalance.text = it.avalaible.value.toString() + it.avalaible.currency
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item);
    }


    companion object {
        const val PRODUCT_CARD_EXTRA_KEY = "product_card"
        const val PRODUCT_ACCOUNT_EXTRA_KEY = "product_account"
    }
}