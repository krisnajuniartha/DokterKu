package com.example.dokterku.ui.application.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dokterku.ViewModelFactory
import com.example.dokterku.data.DokterRepository
import com.example.dokterku.ui.component.DokterCard
import com.example.dokterku.ui.component.DokterSearchBar




@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(
            DokterRepository()
    )
    ),
    navigateToDetail: (String) -> Unit,
) {
    val dokterData by viewModel.dokterData.collectAsState()
    val query by viewModel.query

    Scaffold(
        topBar = { DokterSearchBar(
            query = query,
            onQueryChange = { changed -> viewModel.search(changed) },
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        )
        }
    )
    { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dokterData, key = { it.id }) { user ->
                    DokterCard(
                        id = user.id,
                        name = user.name,
                        headline = user.headline,
                        bannerUrl = user.bannerUrl,
                        photoUrl = user.photoUrl,
                        modifier = Modifier.fillMaxWidth(),
                        navigateToDetail = navigateToDetail,
                    )

                }

            }

        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(
        modifier = Modifier,
        navigateToDetail = {}
    )
}