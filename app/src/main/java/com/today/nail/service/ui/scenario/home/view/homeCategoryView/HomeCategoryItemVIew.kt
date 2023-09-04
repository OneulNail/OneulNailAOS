package com.today.nail.service.ui.scenario.home.view.homeCategoryView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.today.nail.service.data.home.ContentItem
import com.today.nail.service.data.home.dto.categoryItem.PostDTO
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.navigationGraph.HomeRoute
import com.today.nail.service.ui.scenario.home.view.homeView.BottomNavigation
import com.today.nail.service.ui.util.ToastHelper
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeCategoryItemView(activityViewModel : TopLevelViewModel,
                         navController: NavController,
                         viewModel: HomeCategoryItemVIewModel = hiltViewModel(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        it.calculateBottomPadding()
        CategoryItemScreen(
            onClickBackButton = {navController.popBackStack()},
            onClickItem = {
                navController.navigate(HomeRoute.ItemDetail.routes)
                activityViewModel.updateSelectedPostId(it) },
            onClickCommingSoon = {
                ToastHelper.showToast("준비 중인 기능입니다.")
            },
            getPostList = viewModel.postList
        )
    }
}
@Composable
fun CategoryItemScreen(
    onClickBackButton :()-> Unit,
    onClickItem : (Long) -> Unit,
    onClickCommingSoon : () -> Unit,
    getPostList: StateFlow<List<ContentItem>>,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(1f)) {
            Row(modifier= Modifier
                .align(alignment = Alignment.CenterStart)
                .offset(x = 5.dp)
                .fillMaxWidth(0.3f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {onClickBackButton()},
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 15.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIos,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
                //상단 텍스트
                Text(
                    text = "네일",
                    color = Color(0xFF7A00C5),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.offset(x = (-15).dp)
                )
            }
            Row(modifier= Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 15.dp)
            ){
                //상단 우측 버튼1
                IconButton(
                    onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
                //상단 우측 버튼2
                IconButton(
                    onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(5.dp)

                ) {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
                //상단 우측 버튼3
                IconButton(
                    onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingBag,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(1.dp),
            color = Color.LightGray,
        )

        //스타일 카테고리
        Box(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 11.dp)
            .height(40.dp)){
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xFFA4A4A4))
                        .padding(1.dp)
                        .width(24.dp)
                        .height(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Map,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xFFA4A4A4))
                        .padding(1.dp)
                        .width(24.dp)
                        .height(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Tune,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5)
                    )
                }
                Spacer(modifier = Modifier.width(13.dp))
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxHeight()  //fill the max height
                        .width(1.dp)
                        .padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Box(modifier = Modifier.clickable { onClickCommingSoon() }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SwapVert,
                            contentDescription = null,
                            tint = Color.LightGray,
                        )
                        Text(
                            text = "인기순",
                            color = Color.LightGray,
                        )
                    }
                }
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) {
                    item {
                        Box(modifier = Modifier
                            .clickable { onClickCommingSoon() }
                            .border(
                                0.1.dp,
                                color = Color.LightGray,
                                RoundedCornerShape(size = 15.dp)
                            )
                        ) {
                            Text(text = "    내 주변    ")
                        }
                    }
                    item {
                        Box(modifier = Modifier
                            .clickable { onClickCommingSoon() }
                            .border(
                                0.1.dp,
                                color = Color.LightGray,
                                RoundedCornerShape(size = 15.dp)
                            )
                        ) {
                            Text(text = "    지역    ")
                        }
                    }
                    item {
                        Box(modifier = Modifier
                            .clickable { onClickCommingSoon() }
                            .border(
                                0.1.dp,
                                color = Color.LightGray,
                                RoundedCornerShape(size = 15.dp)
                            )
                        ) {
                            Text(text = "    종류    ")
                        }
                    }
                    item {
                        Box(modifier = Modifier
                            .clickable { onClickCommingSoon() }
                            .border(
                                0.1.dp,
                                color = Color.LightGray,
                                RoundedCornerShape(size = 15.dp)
                            )
                        ) {
                            Text(text = "    가격    ")
                        }
                    }
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 16.dp),
            color = Color.LightGray,
        )

        //게시물 전체 조회
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp)
        ) {
            val postList = getPostList.value

            items(postList) { postList ->
                Column() {
                    Box(
                        modifier = Modifier
                            .clickable {
                                //게시물 id 전달
                                onClickItem(postList.postId)
                            }
                            .size(180.dp)
                            .background(Color.LightGray, RoundedCornerShape(size = 15.dp))) {
//                        Image(imageVector = post.imageUrl, contentDescription = null)
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(10.dp)
                                .clickable { onClickCommingSoon() }
                        )
                    }
                    Box {

                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClickItem(postList.postId)
                        }
                    ) {
                        Column() {
                            Row(){
                                Text(
                                    text = "01",
                                    style = TextStyle(
                                        fontSize = 15.sp,
//                                        fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFFA4A4A4),
                                    )
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = postList.name,
                                    style = TextStyle(
                                        fontSize = 13.sp,
//                                        fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )
                            }
                            Text(
                                modifier = Modifier.fillMaxWidth(),
//                                text = postList.content,
                                text = "임시",
                                style = TextStyle(
                                    fontSize = 13.sp,
//                                    fontFamily = FontFamily(Font(R.font.roboto)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFA4A4A4),
                                )
                            )
                            Box(modifier = Modifier.align(Alignment.End)) {
                                val price = postList.price
                                Text(
                                    text = "$price 원",
                                    style = TextStyle(
                                        fontSize = 13.sp,
//                                    fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )
                            }
                        }
                    }
                }
            }
//            items(count = 16) { item ->
//                if (item % 4 < 2) {
//                    Box(
//                        modifier = Modifier
//                            .clickable {
//                                onClickItem(item.toLong())
//                            }
//                            .size(150.dp)
//                            .background(Color.LightGray, RoundedCornerShape(size = 15.dp)))
//                    Box {
//                        Icon(
//                            imageVector = Icons.Default.FavoriteBorder,
//                            contentDescription = null,
//                            modifier = Modifier
//                                .align(Alignment.TopEnd)
//                                .padding(10.dp)
//                                .clickable { onClickCommingSoon() }
//                        )
//                    }
//                }
//                else{
//                    Box(modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
//                            onClickItem(item.toLong())
//                        }
//                    ) {
//                        Column() {
//                            Row(){
//                                Text(
//                                    text = "01",
//                                    style = TextStyle(
//                                        fontSize = 15.sp,
////                                        fontFamily = FontFamily(Font(R.font.roboto)),
//                                        fontWeight = FontWeight(700),
//                                        color = Color(0xFFA4A4A4),
//                                    )
//                                )
//                                Spacer(modifier = Modifier.width(4.dp))
//                                Text(
//                                    text = "네일 샵 이름",
//                                    style = TextStyle(
//                                        fontSize = 13.sp,
////                                        fontFamily = FontFamily(Font(R.font.roboto)),
//                                        fontWeight = FontWeight(700),
//                                        color = Color(0xFF000000),
//                                    )
//                                )
//                            }
//                            Text(
//                                modifier = Modifier.fillMaxWidth(),
//                                text = "설명",
//                                style = TextStyle(
//                                    fontSize = 13.sp,
////                                    fontFamily = FontFamily(Font(R.font.roboto)),
//                                    fontWeight = FontWeight(500),
//                                    color = Color(0xFFA4A4A4),
//                                )
//                            )
//                            Box(modifier = Modifier.align(Alignment.End)) {
//                                Text(
//                                    text = "69,000원",
//                                    style = TextStyle(
//                                        fontSize = 13.sp,
////                                    fontFamily = FontFamily(Font(R.font.roboto)),
//                                        fontWeight = FontWeight(700),
//                                        color = Color(0xFF000000),
//                                    )
//                                )
//                            }
//                        }
//                    }
//                }
//
//            }
        }
    }
}

@Composable
fun PostScreen() {

}

@Preview
@Composable
fun Preview(viewModel: HomeCategoryItemVIewModel = hiltViewModel()) {
    val postList = viewModel.postList
    CategoryItemScreen(
        onClickBackButton = { /*TODO*/ },
        onClickCommingSoon = {},
        onClickItem = {},
        getPostList = postList,
    )
}