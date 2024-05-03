package com.example.traveltaipei.attraction.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.traveltaipei.R
import com.example.traveltaipei.attraction.viewmodel.AttractionViewModel
import com.example.traveltaipei.ui.theme.Pink40
import com.example.traveltaipei.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttractionScreen(
    onUrlClick: (String) -> Unit,
    onBack: () -> Unit, attractionViewModel: AttractionViewModel = viewModel()) {
    //val openWebView = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Pink40,
                    titleContentColor = Pink40,
                ),
                title = {
                    Text(
                        text = "悠遊台北",
                        style = TextStyle(color = Color.White, fontSize = 28.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                AsyncImage(
                    model = if (attractionViewModel.attraction.images.isEmpty())
                        null else attractionViewModel.attraction.images[0].src,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth(),
                    error = painterResource(R.drawable.baseline_image_not_supported_24)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = attractionViewModel.attraction.address,
                    style = TextStyle(color = Color.Black, fontSize = 22.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = attractionViewModel.attraction.open_time,
                    style = TextStyle(color = Color.Black, fontSize = 16.sp),
                    textAlign = TextAlign.Start,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))

                ClickableText(
                    modifier = Modifier,
                    text = AnnotatedString("網址: ${attractionViewModel.attraction.url}"),
                    style = TextStyle(color = Purple40, fontSize = 16.sp),
                    onClick = {
//                        openWebView.value = true
                        onUrlClick(attractionViewModel.attraction.url)
                    }

                )
//        HyperlinkText(
//            fullText = "網址: ${attractionViewModel.attraction.url}",
//            hyperLinks = mutableMapOf(
//                attractionViewModel.attraction.url to attractionViewModel.attraction.url,
//            ),
//            textStyle = TextStyle(
//                textAlign = TextAlign.Center,
//                color = Gray
//            ),
//            linkTextColor = Pink40,
//            fontSize = 16.sp
//        ){
//            openWebView.value = true
//        }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = attractionViewModel.attraction.introduction,
                    style = TextStyle(color = Color.Black, fontSize = 16.sp),
                    textAlign = TextAlign.Start,
                )
                if (attractionViewModel.attraction.images.size > 1) {
                    for (i in 1 until attractionViewModel.attraction.images.size)
                        AsyncImage(
                            model = attractionViewModel.attraction.images[i].src,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth(),
                            error = painterResource(R.drawable.baseline_image_not_supported_24)
                        )
                    Spacer(modifier = Modifier.height(8.dp))
                }

            }
    }
}