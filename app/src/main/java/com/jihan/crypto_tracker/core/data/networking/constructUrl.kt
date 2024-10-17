package com.jihan.crypto_tracker.core.data.networking

import com.jihan.crypto_tracker.BuildConfig

fun constructUrl(url:String) : String{
    return when{
        url.contains(BuildConfig.BASE_URL) -> url
        url.contains("/")-> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}