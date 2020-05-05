package com.alvarohidalgo.mybank.presentation.globalposition

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.alvarohidalgo.mybank.R
import com.alvarohidalgo.mybank.base.presentation.BaseActivity
import com.alvarohidalgo.mybank.base.presentation.ViewmodelOwner
import com.alvarohidalgo.mybank.domain.model.Product
import com.alvarohidalgo.mybank.presentation.info.InfoActivity
import com.alvarohidalgo.mybank.presentation.productdetail.ProductDetailActivity
import kotlinx.android.synthetic.main.activity_global_position.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GlobalPositionActivity : BaseActivity(),
    ViewmodelOwner<GlobalPositionViewModel> {

    override val viewmodel by viewModel<GlobalPositionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_position)
        setSupportActionBar(toolbar)
        viewmodel.onCreate()

        viewmodel.globalPositionLiveData.observe(this, Observer {
            supportActionBar?.title = it.name
            productList.adapter = ProductAdapter(it.products) { product ->
                navigateToProductDetail(product)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.info -> navigateToInfoScreen()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToInfoScreen() {
        val i = Intent(this, InfoActivity::class.java)
        startActivity(i)
    }

    private fun navigateToProductDetail(product: Product) {
        // I have to duplicate code because you can not annotate with @Parcelice an abstract nor sealed class (Product)
        // So I need to infer the type to be able to put it inside the parcelable
        val i = when (product) {
            is Product.Card -> {
                Intent(this, ProductDetailActivity::class.java).apply {
                    putExtras(Bundle().apply {
                        putParcelable(ProductDetailActivity.PRODUCT_CARD_EXTRA_KEY, product)
                    })
                }
            }
            is Product.Account -> {
                Intent(this, ProductDetailActivity::class.java).apply {
                    putExtras(Bundle().apply {
                        putParcelable(ProductDetailActivity.PRODUCT_ACCOUNT_EXTRA_KEY, product)
                    })
                }
            }
        }
        startActivity(i)
    }
}

