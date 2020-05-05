package com.alvarohidalgo.mybank.presentation.info

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.alvarohidalgo.mybank.R
import com.alvarohidalgo.mybank.base.presentation.BaseActivity
import com.alvarohidalgo.mybank.base.presentation.ViewmodelOwner
import kotlinx.android.synthetic.main.activity_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoActivity : BaseActivity(),
    ViewmodelOwner<InfoViewModel> {

    override val viewmodel: InfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewmodel.onCreate()


        viewmodel.infoUrlLiveData.observe(this, Observer {
            url.text = it
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item);
    }

}