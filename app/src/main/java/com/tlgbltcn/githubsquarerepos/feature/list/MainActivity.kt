package com.tlgbltcn.githubsquarerepos.feature.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.tlgbltcn.githubsquarerepos.ui.component.ErrorView
import com.tlgbltcn.githubsquarerepos.ui.component.LoadingView
import com.tlgbltcn.githubsquarerepos.ui.theme.GithubSquareReposTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubSquareReposTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(viewModel)
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun Greeting(viewModel: MainViewModel) {

    val repos = viewModel.repos.collectAsState().value

    when (repos) {
        is Loading -> LoadingView()
        is Error -> ErrorView(message = repos.message)
        is RepositoriesContent -> Text(
            text = repos.list?.map { it.name }?.joinToString(" - ") ?: ""
        )
    }

}