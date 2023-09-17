package com.example.blackcoffer_assignment.presentation.screens.components

import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.blackcoffer_assignment.R
import com.example.blackcoffer_assignment.data.entity.BusinessData
import com.example.blackcoffer_assignment.data.entity.PersonData
import com.example.blackcoffer_assignment.ui.theme.bluegrey
import com.example.blackcoffer_assignment.ui.theme.boxFill
import com.example.blackcoffer_assignment.ui.theme.distanceBox
import com.example.blackcoffer_assignment.ui.theme.imageBackground
import com.example.blackcoffer_assignment.ui.theme.textClr


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BusinessCard(
    businessData: BusinessData,
    distance: Int,

    ) {
    val context = LocalContext.current
    val number = Uri.parse("tel:" + businessData.phoneNumber)
    val phoneIntent = Intent(Intent.ACTION_DIAL, number)


    val contactsIntent = Intent(Intent.ACTION_INSERT)
        .setData(ContactsContract.Contacts.CONTENT_URI)

    contactsIntent.putExtra(ContactsContract.Intents.Insert.NAME, businessData.name)

    contactsIntent.putExtra(ContactsContract.Intents.Insert.PHONE, businessData.phoneNumber)
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
                            text = businessData.name,
                            maxLines = 1,
                            color = textClr,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 15.sp,

                            )
                        val annotatedString = buildAnnotatedString {
                            append(businessData.city)
                            append(" , ")
                            append("within ")
                            append(businessData.distance)
                            append("km")
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



                        Spacer(modifier = Modifier.height(5.dp))

                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(12.dp)
                                .clip(RoundedCornerShape(40))
                                .background(distanceBox),
                        ) {
                            Box(
                                modifier = androidx.compose.ui.Modifier
                                    .width(distance.dp)
                                    .height(12.dp)
                                    .clip(RoundedCornerShape(40))
                                    .background(boxFill)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
//
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(bluegrey), contentAlignment = Alignment.Center
                            ) {
                                IconButton(onClick = {
                                    try {

                                        context.startActivity(phoneIntent)
                                    } catch (e: SecurityException) {
                                        e.printStackTrace()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Phone,
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(25.dp))
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(bluegrey), contentAlignment = Alignment.Center
                            ) {
                                IconButton(onClick = {
                                    try {

                                        context.startActivity(contactsIntent)
                                    } catch (e: SecurityException) {
                                        e.printStackTrace()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.PersonAddAlt1,
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }
                            }

                        }
                    }
                }
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                ) {

                    val str =
                        "${businessData.profession} | ${businessData.experience} years of experience"
                    Text(
                        text = str, maxLines = 1,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        color = textClr
                    )


                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = businessData.about,
                        maxLines = 2,
                        fontWeight = FontWeight.Thin,
                        fontSize = 14.sp,
                        color = textClr
                    )
                    Spacer(modifier = Modifier.height(2.dp))

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
            if (businessData.imageUrl.length > 3) {
                // Glide Image

                GlideImage(
                    model = businessData.imageUrl,
                    contentDescription = "",
                    modifier = Modifier.size(height = 80.dp, width = 70.dp),
                    contentScale = ContentScale.FillBounds
                ) { glide ->
                    glide.load(businessData.imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .centerCrop()
                        .placeholder(R.drawable.loading)
                        .override(200)

                }
            } else {

                Text(
                    text = "${businessData.imageUrl}",
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