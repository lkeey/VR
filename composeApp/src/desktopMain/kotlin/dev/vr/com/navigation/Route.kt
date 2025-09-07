package dev.vr.com.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    object Arena: Route

    @Serializable
    object Zone: Route

    @Serializable
    object Holidays: Route

}
