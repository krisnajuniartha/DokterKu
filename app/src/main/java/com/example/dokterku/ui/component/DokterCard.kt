package com.example.dokterku.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun DokterCard(
    id: String,
    name: String,
    headline: String,
    bannerUrl: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    Card(border = BorderStroke(1.dp, Color(0xffE6E6E6)), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Box(contentAlignment = Alignment.TopCenter, modifier = modifier.clickable { navigateToDetail(id) }){
            AsyncImage(
                model = bannerUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(250.dp)
                    .height(90.dp)
                    .background(color = Color.LightGray)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = "$name photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(16.dp)
                        .offset(y = 2.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )

                Text(
                    text = name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 3.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    text = headline,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    onClick = { },
                ) {
                    Text(text = "View", color = Color.Black)

                }
            }

        }
    }
}

@Preview
@Composable
fun UserListItemPreview() {
    DokterCard(
        id ="1",
        name = "Krisna Juniartha",
        headline = "Dokter Bedah",
        bannerUrl = "https://images.unsplash.com/photo-1603398938378-e54eab446dde?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        photoUrl = "https://avatars.githubusercontent.com/u/90083755?v=4",
        modifier = Modifier.width(150.dp),
        navigateToDetail = {}
    )

}