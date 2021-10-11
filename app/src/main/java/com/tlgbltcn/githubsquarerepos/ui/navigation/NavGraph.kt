package com.tlgbltcn.githubsquarerepos.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tlgbltcn.githubsquarerepos.feature.detail.DetailsPage
import com.tlgbltcn.githubsquarerepos.feature.detail.DetailsViewModel
import com.tlgbltcn.githubsquarerepos.feature.list.ReposPage
import com.tlgbltcn.githubsquarerepos.feature.list.ReposViewModel
import com.tlgbltcn.githubsquarerepos.ui.navigation.DestinationArgKey.ID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


object DestinationArgKey {
    const val ID = "id"
}

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) { NavActions(navController) }

    NavHost(navController, startDestination = Screen.Repos.route) {

        composable(Screen.Repos.route) { navBackStackEntry ->
            val viewModel: ReposViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            )
            ReposPage(
                viewModel = viewModel,
                actions = actions
            )
        }

        composable("${Screen.Details.route}/{$ID}") { navBackStackEntry ->
            val viewModel: DetailsViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            )
            DetailsPage(
                viewModel = viewModel,
                id = navBackStackEntry.arguments?.getString(ID)?.toLong(),
                onBack = actions.navigateBack
            )
        }
    }
}

class NavActions(navController: NavHostController) {

    val gotoDetails: (id: Long) -> Unit = { id: Long ->
        navController.navigate("${Screen.Details.route}/$id")
    }

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }
}