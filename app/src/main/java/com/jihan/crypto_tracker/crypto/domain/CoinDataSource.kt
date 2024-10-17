package com.jihan.crypto_tracker.crypto.domain

import com.jihan.crypto_tracker.core.domain.util.NetworkError
import com.jihan.crypto_tracker.core.domain.util.Result

interface CoinDataSource {

    suspend fun getCoins(): Result<List<Coin>, NetworkError>

}