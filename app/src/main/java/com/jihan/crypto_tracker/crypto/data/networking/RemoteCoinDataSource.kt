package com.jihan.crypto_tracker.crypto.data.networking

import com.jihan.crypto_tracker.core.data.networking.constructUrl
import com.jihan.crypto_tracker.core.data.networking.safeCall
import com.jihan.crypto_tracker.core.domain.util.NetworkError
import com.jihan.crypto_tracker.core.domain.util.Result
import com.jihan.crypto_tracker.core.domain.util.map
import com.jihan.crypto_tracker.crypto.data.mappers.toCoin
import com.jihan.crypto_tracker.crypto.data.networking.dto.CoinResponseDto
import com.jihan.crypto_tracker.crypto.domain.Coin
import com.jihan.crypto_tracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource (private val httpClient: HttpClient) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }

    }
}