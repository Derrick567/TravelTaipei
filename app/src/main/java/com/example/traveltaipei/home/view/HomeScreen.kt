package com.example.traveltaipei.home.view

import android.os.Parcelable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.traveltaipei.R
import com.example.traveltaipei.home.model.remote.dto.News
import com.example.traveltaipei.home.model.Attraction
import com.example.traveltaipei.home.viewmodel.HomeViewModel
import com.example.traveltaipei.ui.theme.Pink40
import com.example.traveltaipei.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(),
               onAttractionNavigate: (Int, Parcelable?) -> Unit
               , onNewsNavigate: (String) -> Unit) {

    val newsList by homeViewModel.news
    val attractions by homeViewModel.attractions
    val context = LocalContext.current
    val openLanguageDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Pink40,
                    titleContentColor = Pink40,
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.home_title),
                        style = TextStyle(color = Color.White, fontSize = 28.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                },
                actions = {
                    IconButton(onClick = { openLanguageDialog.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        if (openLanguageDialog.value) {
            LanguageDialog(
                onDismissRequest = { openLanguageDialog.value = false },
                langs = homeViewModel.langs, currentLanguage = homeViewModel.currentLanguage,
                onItemClick = {lang ->
                    homeViewModel.changeCurrentLanguage(lang)
                    openLanguageDialog.value = false
                    //Toast.makeText(context, lang, Toast.LENGTH_SHORT).show()
                }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item { Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))}

            item {
                ListTitle(stringResource(R.string.home_news_title))
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            items(newsList) { news ->
                NewsItem(
                    news = news,
                    onItemClick = {
                        onNewsNavigate(news.url)
                        //onNavigate(R.id.action_home_fragment_to_attraction_fragment, news)
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                ListTitle(stringResource(R.string.home_attraction_title))
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            items(attractions) { attraction ->
                AttractionItem(
                    attraction = attraction,
                    onItemClick = {
                        onAttractionNavigate(R.id.action_home_fragment_to_attraction_fragment, attraction)
                    }
                )
            }
        }
    }


}
@Composable
fun ListTitle(title: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .width(120.dp)
                .background(
                    Pink40,
                    // rounded corner to match with the OutlinedTextField
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center,

            ) {
            Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp))
        }
    }

}


@Composable
fun NewsItem(news: News, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable {
                onItemClick()
            }
            .padding(horizontal = 8.dp, vertical = 8.dp),
        border = BorderStroke(3.dp, Pink40),
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = news.title,
                style = TextStyle(color = Color.Black, fontSize = 22.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = news.description,
                style = TextStyle(color = Color.Black, fontSize = 16.sp),
                textAlign = TextAlign.Start,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Composable
fun AttractionItem(attraction: Attraction, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable {
                onItemClick()
            }
            .padding(horizontal = 8.dp, vertical = 8.dp),
        border = BorderStroke(3.dp, Pink40),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {


            AsyncImage(
                model = if (attraction.images.isEmpty()) null else attraction.images[0].src,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(130.dp, 100.dp),
                error = painterResource(R.drawable.baseline_image_not_supported_24)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(4f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Pink40,
                            // rounded corner to match with the OutlinedTextField
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp),
                    contentAlignment = Alignment.Center,

                    ) {
                    Text(
                        text = attraction.name,
                        style = TextStyle(color = Color.White, fontSize = 16.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = attraction.introduction,
                    style = TextStyle(color = Color.Black, fontSize = 16.sp),
                    textAlign = TextAlign.Start,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp, 30.dp)
                )
            }

        }
    }
}

@Composable
fun LanguageDialog(
    onDismissRequest: () -> Unit,
    langs: List<Pair<String, String>> = emptyList(),
    currentLanguage: String,
    onItemClick: (String) -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp),

        ) {
            LazyColumn(modifier = Modifier.padding(16.dp)) {

                item {
                    Text(
                        text = "選擇語言",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 24.sp
                    )
                }

                items(langs) { (text, value) ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .then(
                                if (currentLanguage == value) Modifier.background(
                                    PurpleGrey40,
                                    shape = RoundedCornerShape(8.dp)
                                ) else Modifier
                            )
                            .clickable { onItemClick(value) }
                            .padding(16.dp),
                    ) {
                        Text(
                            text = text, modifier = Modifier,
                            style = TextStyle(
                                color = Color.Black, fontSize = 16.sp,
                                ), textAlign = TextAlign.Start
                        )
                    }


                }
            }
        }
    }
}


@Preview
@Composable
fun Preview1() {
    val context = LocalContext.current
    NewsItem(news = News(
        title = "北市觀光活動補助4／1起開放受理！ 優先補助地方特色、低碳等議題",
        description = "臺北市觀光活動補助一年共兩期，第2期即將於112年4月1日至4月30日開放申請，對象為112年7月至12月在臺北市舉辦之活動。台北市政府觀光傳播局1日表示，為促進公私協力、共同辦理特色活動吸引旅客到台北，並配合北市永續政策方向，今年將針對結合「循環永續、低碳」概念辦理的觀光活動，或是結合市府跨年、夏日節等指標活動，以及能串聯地方特色的民間活動等優先提供補助，歡迎民間單位共同參與響應。\r\n\r\n▲北市觀光活動補助4/1起開放受理，歡迎具地方特色之民間觀光活動提出申請。(圖片來源：臺北市政府觀光傳播局)\r\n\r\n\r\n觀傳局說明，此次觀光活動補助申請案，如配合以下市府施政重點（詳情請查第2期臺北市觀光活動補助公告）且具「永續辦理」之性質者，將列為優先補助對象：\r\n1.結合本府指標性活動：響應本府大型活動，如：花系列活動、台北夏日系列活動、跨年活動等。\r\n2.串聯觀光產業方案：於臺北市辦理特色活動（如：創意市集、主題活動等）並結合旅遊（宿）業、商圈或臺北市雙層觀光巴士等相關觀光產業。\r\n3.提出含有美食文化、夜間觀光或多日型之主題活動。\r\n4.推廣地方特色場域，如：以北藝中心、北流中心、松菸、貓空、臺北大縱走等主題場域，規劃特色活動或相關體驗，以吸引民眾常態性參與並促進地方觀光人潮及產業活絡。\r\n5.推廣綠色旅遊：響應全球淨零排放目標，結合「循環永續、低碳」概念辦理觀光活動。\r\n\r\n▲今年觀光活動補助將優先補助結合本府施政重點，並能常年辦理的項目。(圖片來源：臺北市政府觀光傳播局)\r\n\r\n觀傳局特別提醒，提案單位須編列自籌款50%以上，且本次審查項目及標準中，「行銷宣傳」將佔比30%，以鼓勵申請單位著重活動行銷宣傳，吸引遊客來臺北參與活動。此外，隨e化消費普及，補助案活動有開放交易者，應導入無現金交易措施，提供民眾多元便利的金流。\r\n\r\n觀傳局說，北市觀光活動補助每年分2期，補助對象為民間單位於臺北市境內辦理的觀光活動，凡在臺北市舉辦，主題以民俗、節慶或其他足以彰顯地方特色，並可促進臺北市觀光發展、增加北市觀光人潮的觀光活動，均可依規定於期限內提出申請，實際補助金額將經審查結果訂定。有意申請的單位，申請資格及方式情詳見「第2期臺北市觀光活動補助公告」，" +
                "並請於112年4月1日至4月30日至「市民服務大平臺」網站，依機關分類至觀光傳播局，即可查詢臺北市觀光活動補助相關內容"
    ), onItemClick = {

    })
//    AttractionItem(
//        attraction = Attraction(
//            name = "古亭河濱公園",
//            address = "100 臺北市中正區永福橋至中正橋間",
//            introduction = "古亭河濱公園位於永福橋至中正橋間，公園面積約27.3公頃，綠地廣闊。單車從思源街沿著自來水博物館方向騎到底就可進入園區內，斜坡道種植灌木排列出「古亭河濱公園」字樣別具巧思。\r\n▲圖片來源：臺北市政府觀光傳播局\r\n\r\n夏日午后，避開烈日，來這裡吹吹風，公園內有網球場、籃球場、溜冰場等一應俱全的運動設施供大家使用。喜愛來此地打球的球友都知道，這裡有兩座網球場比鄰而建，共享綠蔭，運動時不必在烈日下曝曬，最適合用來比賽。\r\n▲圖片來源：臺北市政府觀光傳播局\r\n\r\n永福橋下有攀岩練習場和兒童遊樂設施，滑梯下鋪著厚厚的保護軟墊，可見市府的貼心設計。攀岩壁面高約三米，適合孩子們來此攀爬，訓練體力。夏日帶著孩子到這裡避暑、遊戲是很不錯的選擇。\r\n\r\n公園的自行車道燈光、規劃都相當完善，尤其是夏日夜晚，更是許多人選擇夜騎的好地點。\r\n▲圖片來源：臺北市政府觀光傳播局\r\n\r\n於冬春之際，古亭河濱公園種植著大面積的絕美花海，彷彿像一張夢幻的花毯降臨至公園內，更設置許多以「愛」為主題的裝置藝術供遊客們拍攝美照。\r\n▲圖片來源：臺北市政府觀光傳播局\r\n\r\n111年古亭河濱公園內新增了「古亭狗活動區」，佔地面積703平方公尺，園區設施包含安全圍籬與出入口雙道門加強安全防護，還有多種基本設施，非常適合大家帶著自己的毛小孩一同來放風玩樂。",
//            images = listOf(
//                Image(
//                    src = "https://www.travel.taipei/image/63542",
//                    subject = "",
//                    ext = ".jpg"
//                )
//            )
//        ),
//
//        onItemClick = {
//            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
//        }
//    )
}