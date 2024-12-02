package com.example.feednoticias.di

import com.example.feednoticias.business.portal.PortalBusiness
import com.example.feednoticias.business.portal.PortalBusinessImpl
import com.example.feednoticias.data.api.portal.PortalApi
import com.example.feednoticias.data.repository.portal.PortalRepository
import com.example.feednoticias.data.repository.portal.PortalRepositoryImpl
import com.example.feednoticias.presentation.portal.viewmodel.PortalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val feedModule = module {
    addApis()
    addBusiness()
    addRepositories()
    addViewModels()
}

private fun Module.addApis() {
    single { get<Retrofit>().create(PortalApi::class.java) }
}

private fun Module.addBusiness() {
 single<PortalBusiness> { PortalBusinessImpl(portalRepository = get()) }
}

private fun Module.addRepositories() {
    single<PortalRepository> { PortalRepositoryImpl(api = get(), portalDao = get()) }
}

private fun Module.addViewModels() {
    viewModel { PortalViewModel(application = get(), business = get()) }
}