//package dev.vr.com.navigation
//
//import androidx.compose.animation.slideInHorizontally
//import androidx.compose.animation.slideOutHorizontally
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavController
//import dev.vr.com.presentation.screen.ArenaScreen
//import dev.vr.com.presentation.screen.HolidaysScreen
//import dev.vr.com.presentation.screen.ZoneScreen
//
//@Composable
//fun VRNavHost (
//    navController: NavController
//) {
//    NavHost(
//        navController = navController,
//        startDestination = Route.Arena,
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//
//        composable<Route.Zone>(
//            exitTransition = { slideOutHorizontally() },
//            popEnterTransition = { slideInHorizontally() }
//        ) {
//            ZoneScreen(
//                navController = navController
//            )
//        }
//
//        composable<Route.Arena>(
//            exitTransition = { slideOutHorizontally() },
//            popEnterTransition = { slideInHorizontally() }
//        ) {
//            ArenaScreen(
//                navController = navController
//            )
//        }
//
//        composable<Route.Holidays>(
//            exitTransition = { slideOutHorizontally() },
//            popEnterTransition = { slideInHorizontally() }
//        ) {
//            HolidaysScreen(
//                navController = navController
//            )
//        }
//    }
//}
