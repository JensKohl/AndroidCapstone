package com.example.littlelemon

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember



@Composable
fun Home(navController: NavController?){
    val context = LocalContext.current

    Column(
        Modifier.verticalScroll(rememberScrollState())
    )
    {
        Row()
        {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "Logo Image",
                modifier = Modifier
                    .size(250.dp, 100.dp),
                alignment = Alignment.Center
            )

            Spacer(Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Pic",
                //alignment = Alignment.
                modifier = Modifier
                    .size(80.dp, 100.dp)
                    .clickable {
                        navController?.navigate(Profile.route)
                    }
            )
        }
        Row(
        )
        {
            Column(
                modifier = Modifier
                    .background(Color(0xFF495E57))
                    .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
            )
            {
                Text(
                    text = "Little Lemon",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF4CE14)
                )
                Text(
                    text = "Chicago",
                    fontSize = 24.sp,
                    color = Color(0xFFEDEFEE)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 18.dp)
                ) {
                    Text(
                        text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                        color = Color(0xFFEDEFEE),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 28.dp)
                            .fillMaxWidth(0.6f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Upper Panel Image",
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))

                    )
                }
                // TODO: search bar
                var searchPhrase by remember {mutableStateOf("")}

                OutlinedTextField(
                    value = searchPhrase,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start=1.dp, end=1.dp),
                    onValueChange = { searchPhrase = it },

                    label = { Text("Search") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White)
                )


            }
        }
    }
}