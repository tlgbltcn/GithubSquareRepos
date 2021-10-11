package com.tlgbltcn.githubsquarerepos.feature.detail

import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.tlgbltcn.githubsquarerepos.R
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import com.tlgbltcn.githubsquarerepos.feature.list.Item
import com.tlgbltcn.githubsquarerepos.feature.list.Loading
import com.tlgbltcn.githubsquarerepos.ui.component.LoadingView
import com.tlgbltcn.githubsquarerepos.ui.component.TextSnackBarContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import androidx.constraintlayout.compose.ConstraintLayout
import com.tlgbltcn.githubsquarerepos.util.visible

const val IMG_URL = "https://xms-production-f.squarecdn.com/xms/assets/public-web-styles/social/default-80476ca4a2046167a78eae04dead82fc956b41cfb7f0f65b71e4884354cad5d0.jpg"

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun DetailsPage(
    viewModel: DetailsViewModel,
    id: Long?,
    onBack: () -> Unit
) {

    viewModel.getRepository(id ?: 0)
    val state = viewModel.item.collectAsState().value
    val showSnackBar = viewModel.showSnackBar.observeAsState().value
    val isBookmarking = viewModel.isBookmarking.observeAsState().value

    when (state) {
        is Loading -> LoadingView()
        is Item -> Surface {
            TextSnackBarContainer(
                snackBarText = if (state.item.isBookmarked) stringResource(R.string.bookmark) else stringResource(
                    R.string.removed
                ),
                showSnackBar = showSnackBar ?: false,
                onDismissSnackBar = { viewModel.dismissSnackBar() }
            ) {
                Details(
                    isBookmarked = state.item.isBookmarked,
                    isBookmarking = isBookmarking ?: false,
                    modifier = Modifier,
                    item = state.item,
                    onBack = onBack,
                    onFab = { id ->
                        viewModel.updateBookmark(
                            value = state.item.isBookmarked.not(),
                            id = id
                        )
                    }
                )
            }
        }
    }
}

@VisibleForTesting
@Composable
fun Details(
    isBookmarked: Boolean,
    isBookmarking: Boolean,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onFab: (Long) -> Unit,
    item: RepositoryItem
) {
    val scrollState = rememberScrollState()
    var scroller by remember {
        mutableStateOf(DetailsScroller(scrollState, Float.MIN_VALUE))
    }
    val transitionState = remember(scroller) { scroller.toolbarTransitionState }
    val toolbarState = scroller.getToolbarState(LocalDensity.current)

    val transition = updateTransition(transitionState, label = "")
    val toolbarAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 0f else 1f
    }
    val contentAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 1f else 0f
    }

    Box(modifier) {
        DetailsContent(
            scrollState = scrollState,
            toolbarState = toolbarState,
            onNamePosition = { newNamePosition ->
                if (scroller.namePosition == Float.MIN_VALUE) {
                    scroller = scroller.copy(namePosition = newNamePosition)
                }
            },
            item = item,
            isBookmarked = isBookmarked,
            isBookmarking = isBookmarking,
            onFab = onFab,
            contentAlpha = { contentAlpha.value }
        )
        Toolbar(
            name = item.name,
            onBack = onBack,
            toolbarState = toolbarState,
            toolbarAlpha = { toolbarAlpha.value },
            contentAlpha = { contentAlpha.value }
        )
    }
}

@Composable
private fun DetailsContent(
    scrollState: ScrollState,
    toolbarState: ToolbarState,
    isBookmarked: Boolean,
    isBookmarking: Boolean,
    item: RepositoryItem,
    onNamePosition: (Float) -> Unit,
    onFab: (Long) -> Unit,
    contentAlpha: () -> Float
) {
    Column(Modifier.verticalScroll(scrollState)) {
        ConstraintLayout {
            val (image, fab, info) = createRefs()

            ToolbarImage(
                imageUrl = IMG_URL,
                modifier = Modifier
                    .constrainAs(image) { top.linkTo(parent.top) }
                    .alpha(contentAlpha())
            )

            val fabEndMargin = 8.dp
            Fab(
                isBookmarked = isBookmarked,
                isBookmarking = isBookmarking,
                onFab = {
                    onFab.invoke(item.repoId)
                },
                modifier = Modifier
                    .constrainAs(fab) {
                        centerAround(image.bottom)
                        absoluteRight.linkTo(parent.absoluteRight, margin = fabEndMargin)
                    }
                    .alpha(contentAlpha())
            )

            DetailsInformation(
                item = item,
                onNamePosition = { onNamePosition(it) },
                toolbarState = toolbarState,
                modifier = Modifier.constrainAs(info) {
                    top.linkTo(image.bottom)
                }
            )
        }
    }
}

@Composable
private fun DetailsInformation(
    item: RepositoryItem,
    onNamePosition: (Float) -> Unit,
    toolbarState: ToolbarState,
    modifier: Modifier = Modifier

) {
    Column(modifier = modifier.padding(vertical = 24.dp)) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
                .align(Alignment.CenterHorizontally)
                .onGloballyPositioned { onNamePosition(it.positionInWindow().y) }
                .visible { toolbarState == ToolbarState.HIDDEN }
        )

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            DescriptionTitleAndContent(
                id = R.string.description, subTitle = item.description
            )

            DescriptionTitleAndContent(
                id = R.string.language, subTitle = item.language
            )

            DescriptionTitleAndContent(
                id = R.string.url, subTitle = item.url
            )

            DescriptionTitleAndContent(
                id = R.string.stargazers_count, subTitle = item.stargazersCount.toString()
            )

            DescriptionTitleAndContent(
                id = R.string.watchers_count, subTitle = item.watchersCount.toString()
            )

            DescriptionTitleAndContent(
                id = R.string.update_date, subTitle = item.updatedAt
            )
        }
    }
}

@Composable
fun DescriptionTitleAndContent(
    @StringRes id: Int,
    subTitle: String?,
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = stringResource(id = id),
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally)
                .visible { subTitle != null }
        )

        Text(
            text = subTitle ?: "",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .visible { subTitle != null }
        )
    }
}

@Composable
private fun Fab(
    onFab: () -> Unit,
    isBookmarked: Boolean,
    isBookmarking: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onFab,
        modifier = modifier.defaultMinSize(minWidth = 56.dp, minHeight = 56.dp),
        enabled = isBookmarking.not(),
        shape = CircleShape

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_bookmark_24),
            colorFilter = ColorFilter.tint(
                color = if (isBookmarked) Color(0xFFDD6955) else Color(0xFF888888)
            ),
            contentDescription = null
        )
    }
}

@Composable
private fun Toolbar(
    toolbarState: ToolbarState,
    name: String,
    onBack: () -> Unit,
    toolbarAlpha: () -> Float,
    contentAlpha: () -> Float
) {
    if (toolbarState.isShown) {
        DetailsToolbarContent(
            name = name,
            onBack = onBack,
            modifier = Modifier.alpha(toolbarAlpha())
        )
    } else {
        RegularToolbarContent(
            onBack = onBack,
            modifier = Modifier.alpha(contentAlpha())
        )
    }
}

@Composable
private fun DetailsToolbarContent(
    name: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        TopAppBar(
            modifier = modifier.statusBarsPadding(),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.bookmark)
                )
            }
            Text(
                text = name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Composable
private fun RegularToolbarContent(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val iconModifier = Modifier
            .sizeIn(maxWidth = 32.dp, maxHeight = 32.dp)
            .background(color = MaterialTheme.colors.surface, shape = CircleShape)

        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(start = 12.dp)
                .then(iconModifier)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.bookmark)
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun ToolbarImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    placeholderColor: Color = MaterialTheme.colors.onSurface.copy(0.2f)
) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(278.dp)
    )

    if (painter.state is ImagePainter.State.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(placeholderColor)
        )
    }
}