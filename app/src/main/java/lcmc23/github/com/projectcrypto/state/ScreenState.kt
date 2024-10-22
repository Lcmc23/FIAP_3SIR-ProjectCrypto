package lcmc23.github.com.projectcrypto.state

import lcmc23.github.com.projectcrypto.service.Ticker

sealed class ScreenState {
    object Loading: ScreenState()
    data class Success(val data: Ticker): ScreenState()
    data class Error(val exception: Throwable): ScreenState()
}