package com.jihan.crypto_tracker.crypto.presentation.coin_list

import com.jihan.crypto_tracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error : NetworkError) : CoinListEvent
}