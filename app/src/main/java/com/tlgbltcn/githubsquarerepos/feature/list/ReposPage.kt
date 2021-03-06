package com.tlgbltcn.githubsquarerepos.feature.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import com.tlgbltcn.githubsquarerepos.ui.component.ErrorView
import com.tlgbltcn.githubsquarerepos.ui.component.LoadingView
import com.tlgbltcn.githubsquarerepos.ui.navigation.NavActions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tlgbltcn.githubsquarerepos.R
import com.tlgbltcn.githubsquarerepos.util.languageColor
import com.tlgbltcn.githubsquarerepos.util.visible

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun ReposPage(
    actions: NavActions,
    viewModel: ReposViewModel
) {

    viewModel.fetchRepos()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                backgroundColor = Color.White,
                elevation = 4.dp
            )
        }
    ) {
        when (val repos = viewModel.repos.collectAsState().value) {
            is Loading -> LoadingView()
            is Error -> ErrorView(message = repos.message)
            is Content -> ReposList(repos = repos.repos, actions = actions)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ReposList(
    actions: NavActions,
    repos: List<RepositoryItem>
) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
    ) {
        items(
            items = repos,
            itemContent = {
                RepositoryItemRow(
                    item = it,
                    navigateToDetails = { id ->
                        actions.gotoDetails(id)
                    })
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun RepositoryItemRow(item: RepositoryItem, navigateToDetails: (Long) -> Unit) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            navigateToDetails.invoke(item.repoId)
        }
    ) {
        Image(
            modifier = Modifier
                .size(36.dp)
                .padding(end = 16.dp)
                .visible { item.isBookmarked },
            alignment = Alignment.TopEnd,
            painter = painterResource(id = R.drawable.ic_baseline_bookmark_24),
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 8.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = item.name, style = MaterialTheme.typography.h6)

            Text(
                text = item.description ?: "",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.visible { item.description != null })

            Row(modifier = Modifier.padding(top = 8.dp)) {
                Image(
                    modifier = Modifier.size(20.dp).align(CenterVertically),
                    painter = painterResource(id = R.drawable.ic_baseline_star_rate_24),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Text(text = item.stargazersCount.toString())
                if (item.language != null) {
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Canvas(modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.CenterVertically),
                        onDraw = {
                            drawCircle(color = item.language.languageColor())
                        })
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Text(text = item.language)
                }
            }
        }
    }
}

