package com.example.dokterku.ui.application.detail


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dokterku.R
import com.example.dokterku.ViewModelFactory
import com.example.dokterku.data.DokterRepository
import com.example.dokterku.model.Dokter
import com.example.dokterku.ui.State.UiState


@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailDokterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory(
        DokterRepository()
    )
    ),
    modifier: Modifier = Modifier,

    ) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getDokterDetailById(id)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(dokterData = data, viewModel = viewModel)
            }
            is UiState.Error -> {
            }
        }
    }
}

@Composable
fun DetailContent(
    dokterData: Dokter,
    modifier: Modifier = Modifier,
    viewModel: DetailDokterViewModel,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AsyncImage(
            model = dokterData.bannerUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = Color.LightGray)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = 70.dp))
        {
            AsyncImage(
                model = dokterData.photoUrl,
                contentDescription = "${dokterData.name} photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
                    .background(Color.Gray)
            )

            Text(
                text = dokterData.name,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp),
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = dokterData.headline,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {

                Text(
                    text = "${dokterData.rating} rating",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "${dokterData.follower} following",
                    fontSize = 14.sp,
                    modifier = Modifier
                )
            }
            Column {
                Text(
                    text = stringResource(id = R.string.about_me),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 8.dp),
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = dokterData.about,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Start,
                )
            }

        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailPreview() {

}