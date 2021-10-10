package com.tlgbltcn.githubsquarerepos.ui.navigation

import androidx.annotation.StringRes
import com.tlgbltcn.githubsquarerepos.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Repos : Screen("repos_screen", R.string.repos_screen)
    object Details : Screen("details_screen", R.string.details_screen)
}
