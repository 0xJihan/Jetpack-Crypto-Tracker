package com.jihan.crypto_tracker.core.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jihan.crypto_tracker.core.presentation.util.ObserveAsEvent
import com.jihan.crypto_tracker.core.presentation.util.toString
import com.jihan.crypto_tracker.crypto.presentation.coin_detial.CoinDetailScreen
import com.jihan.crypto_tracker.crypto.presentation.coin_list.CoinListAction
import com.jihan.crypto_tracker.crypto.presentation.coin_list.CoinListEvent
import com.jihan.crypto_tracker.crypto.presentation.coin_list.CoinListScreen
import com.jihan.crypto_tracker.crypto.presentation.coin_list.CoinListViewmodel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveCoinListDetailPane(
    viewmodel: CoinListViewmodel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val state by viewmodel.state.collectAsStateWithLifecycle()


    ObserveAsEvent(viewmodel.events) { event ->
        when (event) {
            is CoinListEvent.Error -> {
                Toast.makeText(
                    context, event.error.toString(context), Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()

    NavigableListDetailPaneScaffold(navigator = navigator, listPane = {
        CoinListScreen(
            state = state,
        ) {
            viewmodel.onAction(it)

            when (it) {
                is CoinListAction.OnCoinClick -> {
                    navigator.navigateTo(
                        pane = ListDetailPaneScaffoldRole.Detail
                    )
                }
            }
        }
    }, detailPane = {
        AnimatedPane {
            CoinDetailScreen(state = state)
        }
    }, modifier = modifier)


}