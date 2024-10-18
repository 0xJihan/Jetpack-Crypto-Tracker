package com.jihan.crypto_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jihan.crypto_tracker.crypto.presentation.coin_list.CoinListScreen
import com.jihan.crypto_tracker.crypto.presentation.coin_list.CoinListViewmodel
import com.jihan.crypto_tracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel = koinViewModel<CoinListViewmodel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    CoinListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state
                    )

                }
            }
        }
    }
}

