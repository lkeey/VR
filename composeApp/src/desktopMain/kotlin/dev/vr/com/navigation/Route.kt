package dev.vr.com.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Arena: Route

    @Serializable
    data object Zone: Route

    @Serializable
    data object Holidays: Route

}