package com.example.blackcoffer_assignment.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.blackcoffer_assignment.R
import com.example.blackcoffer_assignment.data.entity.MerchantData
import com.example.blackcoffer_assignment.presentation.screens.components.BusinessCard
import com.example.blackcoffer_assignment.presentation.screens.components.ListCard
import com.example.blackcoffer_assignment.presentation.screens.components.MerchantCard
import com.example.blackcoffer_assignment.presentation.screens.components.MyTopBar
import com.example.blackcoffer_assignment.presentation.screens.components.SearchBox
import com.example.blackcoffer_assignment.presentation.viewmodel.ExploreViewModel
import com.example.blackcoffer_assignment.ui.theme.bluegrey
import com.example.blackcoffer_assignment.ui.theme.fabColro
import de.charlex.compose.SpeedDialData
import de.charlex.compose.SpeedDialFloatingActionButton

@Immutable
class ImuutableRandoms {
    private val list: ArrayList<Int> = ArrayList()

    init {
        for (i in 0..50) {
            val random = (0..99).random()
            list.add(random)
        }
    }

    fun getList(): List<Int> {
        return list
    }
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun ExploreScreen(
    navHostController: NavHostController
) {
    Surface {
        Column(verticalArrangement = Arrangement.Center) {

            val exploreViewModel: ExploreViewModel = hiltViewModel()
            var selectedTab by remember {
                mutableIntStateOf(0)
            }


            val personalList = exploreViewModel.filterItems.collectAsState()
            val businessList = exploreViewModel.filterItemsBusiness.collectAsState()
            val merchantList = exploreViewModel.filterItemsMerchant.collectAsState()


            val isLoading = exploreViewModel.isLoading.collectAsState()
            val isLoading2 = exploreViewModel.isLoading2.collectAsState()
            val isLoading3 = exploreViewModel.isLoading3.collectAsState()


            val randoms = ImuutableRandoms()
            val randomList = randoms.getList()


            val lazyListState = rememberLazyListState()





            Scaffold(
                topBar = {
                    MyTopBar(
                        navHostController
                    )
                },
                floatingActionButton = {

                    AnimatedVisibility(visible = true) {


                        SpeedDialFloatingActionButton(
                            modifier = Modifier,
                            fabContentColor = Color.White,
                            speedDialBackgroundColor = fabColro,
                            speedDialContentColor = bluegrey,
                            fabBackgroundColor = bluegrey,
                            speedDialData = listOf(

                                SpeedDialData(
                                    label = "Notes",
                                    painterResource(R.drawable.notes),

                                    ) {

                                },
                                SpeedDialData(
                                    label = "Jobs",
                                    painterResource(R.drawable.jobs)
                                ) {

                                },
                                SpeedDialData(
                                    label = "Netclan Groups",
                                    painterResource(R.drawable.hashtag)
                                ) {

                                },
                                SpeedDialData(
                                    label = "Business Cards",
                                    painterResource(R.drawable.cards)
                                ) {

                                },
                                SpeedDialData(
                                    label = "Buy-Sell-Rent",
                                    painterResource(R.drawable.shopping)
                                ) {

                                },
                                SpeedDialData(
                                    label = "Matrimony",
                                    painterResource(R.drawable.ring)
                                ) {

                                },
                                SpeedDialData(
                                    label = "Dating",
                                    painterResource(R.drawable.dating)
                                ) {

                                }
                            ),
                            animationDuration = 100,
                            initialExpanded = false,
                            showLabels = true,

                            )
                    }

                }
//                floatingActionButtonPosition = FabPosition.End
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {


                    val tabs = listOf(
                        "Personal",
                        "Business",
                        "Merchant"
                    )
                    val bg = Color(0xFD013664)
                    TabRow(
                        selectedTabIndex = selectedTab,
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = bluegrey,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                color = Color.White,
                                height = 3.dp,
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                            )
                        },

                        tabs = {
                            tabs.forEachIndexed { index, tab ->
                                Tab(
                                    selected = index == selectedTab, onClick = {
                                        selectedTab = index
                                        exploreViewModel.updateTabIndex(index)
                                    }, selectedContentColor = Color.White,
                                    unselectedContentColor = Color(0xD2D5D5D5)
                                ) {
                                    Text(
                                        text = tab,
                                        modifier = Modifier.padding(15.dp),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium,

                                        )
                                }
                            }
                        },


                        )

                    SearchBox()


                    val fabVisibility = remember {
                        derivedStateOf {
                            lazyListState.firstVisibleItemIndex < 1
                        }
                    }
                    when (selectedTab) {
                        0 -> {
                            if (isLoading.value) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    LinearProgressIndicator()
                                }
                            }
                        }

                        1 -> {
                            if (isLoading2.value) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    LinearProgressIndicator()
                                }
                            }
                        }

                        2 -> {
                            if (isLoading3.value) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    LinearProgressIndicator()
                                }
                            }
                        }
                    }




                    LazyColumn(
                        state = lazyListState,

                        content = {


                            when (selectedTab) {
                                0 -> {


                                    itemsIndexed(personalList.value) { ind, it ->


                                        ListCard(
                                            it,
                                            distance = randomList[ind],

                                            )
                                        Spacer(modifier = Modifier.height(20.dp))

                                    }

                                }

                                1 -> {

                                    exploreViewModel.getBusinessCollections()
                                    itemsIndexed(businessList.value) { ind, it ->


                                        BusinessCard(businessData = it, distance = randomList[ind])
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }
                                }

                                2 -> {


                                    exploreViewModel.getMerchantCollections()
                                    itemsIndexed(merchantList.value) { ind, it ->


                                        MerchantCard(merchantData = it, distance = randomList[ind])
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }


                                }
                            }


                        },
                        contentPadding = PaddingValues(15.dp),


                        )

//                    }
                }


            }
        }
    }
}


