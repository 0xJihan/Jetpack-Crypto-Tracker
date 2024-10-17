package com.jihan.crypto_tracker.crypto.presentation.coin_list

import com.jihan.crypto_tracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
}