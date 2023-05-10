package com.purple.hello.feature.setting.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.purple.hello.feature.setting.app.AppSettingRoute

const val appSettingNavigationRoute = "app_setting_route"

fun NavController.navigateToAppSetting(navOptions: NavOptions? = null) {
    this.navigate(appSettingNavigationRoute, navOptions)
}

fun NavGraphBuilder.appSettingScreen(
    onBackClick: () -> Unit,
) {
    composable(route = appSettingNavigationRoute) {
        AppSettingRoute(onBackClick = onBackClick)
    }
}
