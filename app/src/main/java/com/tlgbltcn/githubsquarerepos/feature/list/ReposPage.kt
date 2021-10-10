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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.tlgbltcn.githubsquarerepos.R

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun ReposPage(
    actions: NavActions,
    viewModel: ReposViewModel
) {

    val repos = viewModel.repos.collectAsState().value

    when (repos) {
        is Loading -> LoadingView()
        is Error -> ErrorView(message = repos.message)
        is RepositoriesContent -> ReposList(repos = repos.list)
    }
}

@Composable
fun ReposList(repos: List<RepositoryItem>) {

    val list = remember { repos }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
    ) {
        items(
            items = list,
            itemContent = {
                RepositoryItemRow(item = it)
            }
        )
    }
}

@Composable
fun RepositoryItemRow(item: RepositoryItem) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Image(
            modifier = Modifier
                .size(36.dp)
                .padding(end = 16.dp),
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
            if (item.description != null) {
                Text(text = item.description, style = MaterialTheme.typography.subtitle2)
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Image(
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
                            drawCircle(color = Color.Red)
                        })
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Text(text = item.language)
                }
            }
        }
    }
}