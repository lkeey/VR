package dev.vr.com.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    object Arena: Route

    @Serializable
    object Zone: Route

    @Serializable
    object Holidays: Route

    @Serializable
    object Settings: Route

}
