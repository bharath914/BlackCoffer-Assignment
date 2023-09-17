package com.example.blackcoffer_assignment.presentation.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.blackcoffer_assignment.R
import com.example.blackcoffer_assignment.presentation.navigation.NavConst
import com.example.blackcoffer_assignment.presentation.viewmodel.ExploreViewModel
import com.example.blackcoffer_assignment.ui.theme.statusBarClr
import com.example.blackcoffer_assignment.ui.theme.textClr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    navHostController: NavHostController
) {


    SmallTopAppBar(
        title = {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(contentAlignment = TopStart) {

                    Icon(
                        painterResource(id = R.drawable.sortpx),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier

                            .size(36.dp)

                    )
                }


                //

                Box(
                    modifier = Modifier
                        .weight(4f)
                        .padding(start = 20.dp),
                    contentAlignment = TopStart
                ) {
                    Column {


                        Text(
                            text = "Howdy ,Bharath Prakash Ayinala!!",
                            maxLines = 1,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.LocationOn,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "Andhra Pradesh",
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        }
                    }

                }
                //Refine BUtton
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 5.dp),
                    contentAlignment = BottomEnd
                ) {
                    IconButton(
                        onClick = {
                            navHostController.navigate(NavConst.refine)
                        }, modifier = Modifier

                            .size(48.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {


                            Icon(
                                painterResource(id = R.drawable.event_listpx),
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = "Refine",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = statusBarClr
        )

    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SearchBox() {
    var text = remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val exploreViewModel: ExploreViewModel = hiltViewModel()


        val tabitem = exploreViewModel.currentTab.collectAsState()



        Box(
            modifier = Modifier
                .weight(7f)
                .padding(top = 10.dp, end = 10.dp)

        ) {
            TextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                    when (tabitem.value) {
                        0 -> {
                            exploreViewModel.setSearchTextPersonal(it)
                        }

                        1 -> {
                            exploreViewModel.setSearchTextBusiness(it)
                        }

                        2 -> {
                            exploreViewModel.setSearchTextMerchant(it)
                        }
                    }


                },
                modifier = Modifier


                    .height(48.dp)
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp,
                        shape = RoundedCornerShape(50),
                        color = Color.Black
                    ),

                maxLines = 1,


                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    containerColor = Color.White,

                    ),
                shape = RoundedCornerShape(50),
                placeholder = {
                    Text(
                        text = "Search",
                        modifier = Modifier.padding(start = 20.dp),
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search, contentDescription = "",
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .offset(y = 2.dp)
                            .size(20.dp)
                    )
                },
                textStyle = TextStyle(
                    fontSize = 16.sp
                )
            )
        }

        Box(
            modifier = Modifier
                .weight(1.5f)
                .padding(top = 10.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.tunepx),
                    contentDescription = "",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
