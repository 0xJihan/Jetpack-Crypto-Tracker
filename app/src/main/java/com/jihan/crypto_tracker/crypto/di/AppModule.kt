package com.jihan.crypto_tracker.crypto.di

import com.jihan.crypto_tracker.core.data.networking.HttpClientFactory
import com.jihan.crypto_tracker.crypto.data.networking.RemoteCoinDataSource
import com.jihan.crypto_tracker.crypto.domain.CoinDataSource
import com.jihan.crypto_tracker.crypto.presentation.coin_list.CoinListViewmodel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single { HttpClientFactory.create(CIO.create()) }

    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewmodel)


}