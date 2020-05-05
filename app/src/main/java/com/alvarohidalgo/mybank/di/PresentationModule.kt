package com.alvarohidalgo.mybank.di

import com.alvarohidalgo.mybank.base.resources.AndroidResourceProvider
import com.alvarohidalgo.mybank.base.resources.ResourceProvider
import com.alvarohidalgo.mybank.presentation.globalposition.GlobalPositionViewModel
import com.alvarohidalgo.mybank.presentation.info.InfoViewModel
import com.alvarohidalgo.mybank.presentation.login.DniValidator
import com.alvarohidalgo.mybank.presentation.login.LoginViewModel
import com.alvarohidalgo.mybank.presentation.login.PasswordValidator
import com.alvarohidalgo.mybank.presentation.productdetail.ProductDetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val presentationModule = module {
    viewModel<LoginViewModel>()
    viewModel<GlobalPositionViewModel>()
    viewModel<InfoViewModel>()
    viewModel<ProductDetailViewModel>()


    single<ResourceProvider> { AndroidResourceProvider(app = androidApplication()) }
    single { DniValidator() }
    single { PasswordValidator() }
}