package com.example.blackcoffer_assignment.presentation.screens.components

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.blackcoffer_assignment.R
import com.example.blackcoffer_assignment.data.entity.PersonData
import com.example.blackcoffer_assignment.presentation.viewmodel.ExploreViewModel
import com.example.blackcoffer_assignment.ui.theme.bluegrey
import com.example.blackcoffer_assignment.ui.theme.boxFill
import com.example.blackcoffer_assignment.ui.theme.distanceBox
import com.example.blackcoffer_assignment.ui.theme.imageBackground
import com.example.blackcoffer_assignment.ui.theme.textClr
import kotlin.math.log


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListCard(
    personData: PersonData,
    distance: Int,

    ) {


    Box(modifier = Modifier.fillMaxWidth()) {

        Card(
            modifier = Modifier
                .padding(start = 25.dp, end = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,

                ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(10)

        ) {


            Column(modifier = Modifier.fillMaxSize()) {
                Row {
                    Box(
                        modifier = Modifier
                            .size(height = 110.dp, width = 80.dp)

                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 15.dp, top = 15.dp),

                        ) {
                        Text(
                            text = "+ INVITE",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp,
                            color = textClr
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = personData.name,
                            maxLines = 1,
                            color = textClr,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 15.sp,

                            )
                        val annotatedString = buildAnnotatedString {
                            append(personData.city)
                            append(" | ")
                            append(personData.profession)
                        }
                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = annotatedString,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = textClr,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Within ${personData.distance}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = textClr
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(15.dp)
                                .clip(RoundedCornerShape(40))
                                .background(distanceBox),
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(distance.dp)
                                    .height(15.dp)
                                    .clip(RoundedCornerShape(40))
                                    .background(boxFill)
                            )
                        }
//                        Slider(value = , onValueChange = )
                    }
                }
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    val str = personData.hobbies.replace(',', '|')
                    Text(
                        text = str, maxLines = 1,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        color = textClr
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = personData.about,
                        maxLines = 2,
                        fontWeight = FontWeight.Thin,
                        fontSize = 14.sp,
                        color = textClr
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = personData.description,
                        maxLines = 4,
                        fontWeight = FontWeight.Thin,
                        fontSize = 14.sp,
                        color = textClr,
                        style = TextStyle(lineHeight = 18.sp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 22.dp)
                .size(height = 80.dp, width = 70.dp)
                .clip(RoundedCornerShape(20))

                .background(imageBackground),
            contentAlignment = Alignment.Center

        ) {
            if (personData.imageUrl.length > 3) {
                // Glide Image

                GlideImage(
                    model = personData.imageUrl,
                    contentDescription = "",
                    modifier = Modifier.size(height = 80.dp, width = 70.dp),
                    contentScale = ContentScale.FillBounds
                ) { glide ->
                    glide.load(personData.imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .centerCrop()
                        .placeholder(R.drawable.loading)
                        .override(200)

                }
            } else {

                Text(
                    text = "${personData.imageUrl}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = textClr
                )
            }
        }


    }
}



