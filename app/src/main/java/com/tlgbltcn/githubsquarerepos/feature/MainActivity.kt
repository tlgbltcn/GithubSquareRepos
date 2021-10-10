package com.tlgbltcn.githubsquarerepos.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tlgbltcn.githubsquarerepos.ui.navigation.NavGraph
import com.tlgbltcn.githubsquarerepos.ui.theme.GithubSquareReposTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubSquareReposTheme {
               NavGraph()
            }
        }
    }
}