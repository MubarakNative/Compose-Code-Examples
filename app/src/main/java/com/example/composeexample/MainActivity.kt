package com.example.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeexample.ui.theme.ComposeExampleTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            ComposeSampleApp(windowSize = windowSize)
        }
    }
}

@Composable
fun ComposeSampleApp(windowSize: WindowSizeClass) {
    when(windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> BaseContainerPortrait()
        WindowWidthSizeClass.Expanded -> BaseContainerLandscape()
    }
}

@Composable
fun BaseContainerPortrait() {
    ComposeExampleTheme {
        Scaffold(bottomBar = { ComposeBottomNavigation() }) { paddingValues ->
            HomeScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun BaseContainerLandscape(modifier: Modifier = Modifier) {
    ComposeExampleTheme {
        Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
            Row {
                ComposeNavRail()
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.verticalScroll(
            rememberScrollState()
        )
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.top_languages) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favourite_collections) {
            FavoriteCollectionsGrid(modifier)
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {

    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = "Search")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun LanguageItem(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int = R.drawable.swift,
    @StringRes text: Int = R.string.swift
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.padding(2.dp))
        Image(
            painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavouriteCollectionCard(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int = R.drawable.java,
    @StringRes text: Int
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = modifier.size(80.dp)
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

data class Data(@DrawableRes val drawable: Int, @StringRes val text: Int)

val data = listOf(
    Data(R.drawable.swift, R.string.swift),
    Data(R.drawable.java, R.string.java),
    Data(R.drawable.ruby, R.string.ruby),
    Data(R.drawable.c_sharp, R.string.c_sharp),
    Data(R.drawable.scala, R.string.scala),
    Data(R.drawable.python, R.string.python),
    Data(R.drawable.typescript, R.string.typescript)
)

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data) {
            LanguageItem(modifier, drawable = it.drawable, text = it.text)
        }
    }

}

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp),
        rows = GridCells.Fixed(2)
    ) {
        items(data) {
            FavouriteCollectionCard(drawable = it.drawable, text = it.text)
        }
    }
}

@Composable
fun ComposeBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {

        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = "Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile"
                )
            },
            label = { Text(text = "Profile") }
        )

    }
}

@Composable
fun ComposeNavRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                label = { Text(text = "Home") },
                icon = {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                }
            )

            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                label = { Text(text = "Profile") },
                icon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar()
}

@Preview(showBackground = true)
@Composable
private fun AlignYourBodyElementPreview() {
    ComposeExampleTheme {
        LanguageItem()
    }
}

@Preview
@Composable
private fun FavouriteCollectionCardPreview() {
    ComposeExampleTheme {
        FavouriteCollectionCard(text = R.string.swift)
    }
}

@Preview(showBackground = true)
@Composable
private fun AlignYourBodyRowPreview() {
    ComposeExampleTheme {
        AlignYourBodyRow()
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteCollectionsGridPreview() {
    ComposeExampleTheme {
        FavoriteCollectionsGrid()
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeSectionPreview() {
    ComposeExampleTheme {
        HomeSection(title = R.string.top_languages) {
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ComposeExampleTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, heightDp = 180)
@Composable
private fun HomeScreenContentPreview() {
    ComposeExampleTheme(darkTheme = true) {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun ComposeBottomNavigationPreview() {
    ComposeExampleTheme {
        ComposeBottomNavigation()
    }
}

@Preview(showBackground = true)
@Composable
private fun ComposeNavRailPreview() {
    ComposeExampleTheme {
        ComposeNavRail()
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseContainerPortraitPreview() {
    ComposeExampleTheme {
        BaseContainerPortrait()
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseContainerLandscapePreview() {
    ComposeExampleTheme {
        BaseContainerLandscape()
    }
}