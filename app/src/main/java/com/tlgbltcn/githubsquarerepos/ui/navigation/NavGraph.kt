package com.tlgbltcn.githubsquarerepos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import com.tlgbltcn.githubsquarerepos.feature.list.ReposPage
import com.tlgbltcn.githubsquarerepos.feature.list.ReposViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) { NavActions(navController) }

    NavHost(navController, startDestination = Screen.Repos.route) {

        composable(Screen.Repos.route) {
            val viewModel: ReposViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            ReposPage(
                viewModel = viewModel,
                actions = actions
            )
        }

        composable(Screen.Details.route) {
            val viewModel: ReposViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            ReposPage(
                viewModel = viewModel,
                actions = actions
            )
        }
    }
}

class NavActions(navController: NavHostController) {

    val gotoDetails: (item: RepositoryItem) -> Unit = {
        navController.navigate(Screen.Details.route)
    }

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }
}