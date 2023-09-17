package com.example.blackcoffer_assignment.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.blackcoffer_assignment.presentation.screens.components.DropDownMenuBox
import com.example.blackcoffer_assignment.presentation.screens.components.FilterChips
import com.example.blackcoffer_assignment.presentation.screens.components.RefineSlider
import com.example.blackcoffer_assignment.presentation.screens.components.RefineTopBar
import com.example.blackcoffer_assignment.presentation.screens.components.SaveButton
import com.example.blackcoffer_assignment.presentation.screens.components.TextBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefineScreen(
    navHostController: NavHostController
) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {


        Scaffold(
            topBar = {
                RefineTopBar(
                    navHostController
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                        .verticalScroll(scrollState),
//                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    DropDownMenuBox()
                    Spacer(modifier = Modifier.height(15.dp))
                    TextBox()
                    Spacer(modifier = Modifier.height(15.dp))
                    RefineSlider()
                    Spacer(modifier = Modifier.height(15.dp))
                    FilterChips()
                    Spacer(modifier = Modifier.height(15.dp))
                    SaveButton()


                }

            }
        }
    }
}