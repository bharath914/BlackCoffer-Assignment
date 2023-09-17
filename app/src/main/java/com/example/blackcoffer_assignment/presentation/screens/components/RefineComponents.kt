package com.example.blackcoffer_assignment.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.blackcoffer_assignment.R
import com.example.blackcoffer_assignment.presentation.viewmodel.ExploreViewModel
import com.example.blackcoffer_assignment.ui.theme.bluegrey
import com.example.blackcoffer_assignment.ui.theme.textClr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefineTopBar(
    navHostController: NavHostController
) {
//    val background = Color(0f,24f,36f)
    SmallTopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                IconButton(onClick = {
                    navHostController.navigateUp()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrrowback),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                Text(text = "Refine", color = Color.White)
            }
        }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = bluegrey,
            navigationIconContentColor = Color.White
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuBox() {
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = "Select Your Availability",
            color = textClr,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(5.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded

            }, modifier = Modifier.fillMaxWidth()
        ) {
            val list = listOf(
                "Available | Hey Let Us Connect",
                "Away | Stay Discrete And Watch",
                "Busy | Do Not Disturb | Will Catch Up Later",
                "SOS | Emergency! Need Assistance! HELP"
            )
            var selectedOptionText by remember {
                mutableStateOf(list[0])
            }
            OutlinedTextField(
                value = selectedOptionText,
                onValueChange = {

                },
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .clickable {
                        expanded != expanded
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = textClr,
                    containerColor = Color.Transparent
                ),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                shape = RoundedCornerShape(15)


            )

            ExposedDropdownMenu(
                expanded = expanded, onDismissRequest = {
                    expanded = !expanded
                }, modifier = Modifier.fillMaxWidth()
            ) {

                list.forEachIndexed { index, s ->

                    DropdownMenuItem(
                        text = {
                            Text(text = s, maxLines = 1)
                        },
                        onClick = {
                            selectedOptionText = s
                            expanded = !expanded
                        },
                        modifier = Modifier.fillMaxWidth()

                    )
                }

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox() {
    var text by remember {
        mutableStateOf("Hi community! I open to new connections \"😊\"")
    }
    var length by remember {
        mutableIntStateOf(47)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Add Your Status", color = textClr, fontWeight = FontWeight.ExtraBold,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = text, onValueChange = {
                if (length <= 250) {
                    text = it
                    length = text.length
                }
            }, colors = TextFieldDefaults.textFieldColors(
                textColor = textClr,
                containerColor = Color.Transparent,


                ),
            textStyle = TextStyle(
                fontSize = 15.sp,
                lineHeight = 18.sp,


                ),
            shape = RoundedCornerShape(15)
        )
        Text(
            text = "$length/250",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = textClr,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 12.sp
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefineSlider() {
    Column(modifier = Modifier.fillMaxWidth()) {
        var sliderValue by remember {
            mutableStateOf(80.0f)
        }

        val thumbOffset = calculateThumbOffset(sliderValue)
        val interaction = MutableInteractionSource()

        Text(
            text = "Select Hyper Local Distance",
            color = textClr,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Slider(
            value = sliderValue,
            onValueChange = {
                sliderValue = it
            },
            thumb = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .offset(y = (-18).dp)
                            .background(bluegrey),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${sliderValue.toInt()}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    SliderDefaults.Thumb(
                        interactionSource = interaction,
                        thumbSize = DpSize(height = 15.dp, width = 15.dp),
                        modifier = Modifier.offset(y = (-12).dp),
                        colors = SliderDefaults.colors(
                            thumbColor = bluegrey,
                            inactiveTickColor = bluegrey
                        )
                    )
                }
            },

            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                thumbColor = bluegrey,
                activeTrackColor = bluegrey,

                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "1km", color = textClr, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text(text = "100km", color = textClr, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}


private fun calculateThumbOffset(value: Float): Dp {

    val valueRange = (0..100)
    val thumbPosition = (value - valueRange.min()) / (valueRange.max() - valueRange.min()) * 200.dp
    return thumbPosition - (15.dp / 2)
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterChips() {

    Column(horizontalAlignment = Alignment.Start) {


        Text(
            text = "Select Purpose",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        FlowRow(maxItemsInEachRow = 4) {


            val listOfHobbies = listOf(
                "Coffee",
                "Business",
                "Hobbies",
                "Friendship",
                "Movies",
                "Dinning",
                "Dating",
                "Matrimony"
            )
            val selectedItems = remember {
                mutableStateListOf(listOfHobbies[0], listOfHobbies[1], listOfHobbies[3])
            }
            listOfHobbies.forEachIndexed { index, s ->

                FilterChip(
                    selected = selectedItems.contains(s),
                    onClick = {
                        if (selectedItems.contains(s)) {
                            selectedItems.remove(s)
                        } else {
                            selectedItems.add(s)
                        }
                    },
                    label = {
                        Text(
                            text = listOfHobbies[index],
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraLight
                        )
                    },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.padding(4.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = bluegrey, selectedLabelColor = Color.White,
                        disabledLabelColor = textClr
                    )
                )
            }
        }

    }
}

@Composable
fun SaveButton() {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {


        OutlinedButton(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = bluegrey
            )
        ) {
            Text(
                text = "Save & Explore",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)

            )

        }
    }
}