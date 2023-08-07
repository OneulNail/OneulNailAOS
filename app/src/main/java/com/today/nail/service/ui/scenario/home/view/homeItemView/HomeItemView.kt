package com.today.nail.service.ui.scenario.home.view.homeItemView

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.today.nail.service.ui.scenario.home.navigationGraph.HomeRoute
import com.today.nail.service.ui.scenario.home.view.homeView.BottomNavigation

@Composable
fun HomeItemView(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        ItemScreen(
            onClickBackButton = {navController.popBackStack()},
            onClickItem = {navController.navigate(HomeRoute.ItemDetail.routes)},
        )
    }

}
@Composable
fun ItemScreen(
    onClickBackButton :()-> Unit,
    onClickItem : () -> Unit,
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
                    )
                }
                //상단 텍스트
                Text(
                    text = "아이템",
                    color = Color(0xFF7A00C5),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                )
            }
            Row(modifier= Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 15.dp)

            ){
                //상단 우측 버튼1
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                    )
                }
                //상단 우측 버튼2
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(5.dp)

                ) {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = null,
                    )
                }
                //상단 우측 버튼3
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingBag,
                        contentDescription = null,
                    )
                }
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.LightGray,
        )

        //스타일 카테고리
        Box(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 11.dp)
            .height(40.dp)){
            Row {
                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                ) {
                }
                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                ) {

                }
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxHeight()  //fill the max height
                        .width(1.dp)
                        .padding(vertical = 8.dp)
                )
                Button(onClick = { /*TODO*/ },
                ) {
                    Text(text = "인기순")
                }

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp)
                ) {
                    items(4) { item ->
                        Text(text = item.toString(),
                            modifier = Modifier
                                .clickable {}
                                .size(50.dp)
                                .background(Color.LightGray))
                    }
                }

            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.LightGray,
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp)
        ) {
            items(count = 16) { item ->
                if (item % 4 < 2) {
                    Text(text = "Image",
                        modifier = Modifier
                            .clickable {
                                onClickItem()
                            }
                            .size(150.dp)
                            .background(Color.LightGray, RoundedCornerShape(size = 15.dp)))
                    Box {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.TopEnd).padding(5.dp).clickable {  }
                        )
                    }
                }
                else{
                    Text(text = "Text",
                        modifier= Modifier
                            .clickable {
                                onClickItem()
                            }
                            .size(100.dp)
                            .background(Color.LightGray, RoundedCornerShape(size = 15.dp)))
                }

            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ItemScreen(onClickBackButton = { /*TODO*/ }) {

    }
}