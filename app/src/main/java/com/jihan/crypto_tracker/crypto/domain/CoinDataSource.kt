package com.jihan.crypto_tracker.crypto.domain

import com.jihan.crypto_tracker.core.domain.util.NetworkError
import com.jihan.crypto_tracker.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {

    suspend fun getCoins(): Result<List<Coin>, NetworkError>

    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): Result<List<CoinPrice>, NetworkError>

}