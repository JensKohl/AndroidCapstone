package com.example.littlelemon

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavController?){
    val context = LocalContext.current

    val database by lazy {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database")
            .fallbackToDestructiveMigration()
            .build()
    }

    val databaseMenuItems by database
        .menuItemDao()
        .getAll()
        .observeAsState(emptyList())

    var menuItems = databaseMenuItems

    var searchPhrase by remember {
        mutableStateOf("")
    }

    var StarterFilter by remember {
        mutableStateOf(false)
    }

    var MainFilter by remember {
        mutableStateOf(false)
    }

    var DessertFilter by remember {
        mutableStateOf(false)
    }

    var DrinksFilter by remember {
        mutableStateOf(false)
    }


    Column(
    )
    {
        Row()
        {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "Logo Image",
                modifier = Modifier
                    .size(200.dp, 80.dp),
                alignment = Alignment.Center
            )

            Spacer(Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Pic",
                //alignment = Alignment.
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .clickable { navController?.navigate(Profile.route) }
            )
        }

        Column(
            modifier = Modifier
                .background(Color(0xFF495E57))
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
        )
        {
            Column()
            {
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Column {
                        Text(
                            text = "Little Lemon",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF4CE14)
                        )

                        Text(
                            text = "Chicago",
                            fontSize = 24.sp,
                            color = Color(0xFFEDEFEE),
                            modifier = Modifier.fillMaxWidth(0.6f)
                        )

                        Text(
                            text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                            color = Color(0xFFEDEFEE),
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .fillMaxWidth(0.6f)
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                            .clip(RoundedCornerShape(10.dp))
                        )
                }
            }
            Column()
            {
                OutlinedTextField(
                    value = searchPhrase,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onValueChange = { searchPhrase = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    },
                    label = { Text("Enter search phrase") },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
                )
            }
        }

        Column()
        {
            Text(
                text = "ORDER FOR DELIVERY!",
                fontSize = 20.sp,
                color = Color(0xFF333333),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 5.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically)
            {
                OutlinedButton(
                    onClick = {
                        StarterFilter = !StarterFilter
                              },
                    colors = ButtonDefaults.buttonColors(Color(0xFFEDEFEE)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Starters",
                        fontSize = 16.sp,
                        color = Color(0xFF495e57)
                    )
                }
                OutlinedButton(
                    onClick = {MainFilter = !MainFilter},
                    colors = ButtonDefaults.buttonColors(Color(0xFFEDEFEE)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Mains",
                        fontSize = 16.sp,
                        color = Color(0xFF495e57)
                    )
                }
                OutlinedButton(
                    onClick = {DessertFilter = !DessertFilter},
                    colors = ButtonDefaults.buttonColors(Color(0xFFEDEFEE)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Desserts",
                        fontSize = 16.sp,
                        color = Color(0xFF495e57)
                    )
                }
                OutlinedButton(
                    onClick = {DrinksFilter = !DrinksFilter},
                    colors = ButtonDefaults.buttonColors(Color(0xFFEDEFEE)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Drinks",
                        fontSize = 16.sp,
                        color = Color(0xFF495e57)
                    )
                }
            }
            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(
                        top = 5.dp,
                        bottom = 5.dp
                    )
            )
        }

        // TODO: hier die when Abfrage und dann remember Values abfragen??
        when {
            StarterFilter -> {
                menuItems = menuItems.filter {
                        item -> item.category == "starters"
                }
            }
            MainFilter -> {
                menuItems = menuItems.filter {
                        item -> item.category == "mains"
                }
            }
            DessertFilter -> {
                menuItems = menuItems.filter {
                        item -> item.category == "desserts"
                }
            }
            DrinksFilter -> {
                menuItems = menuItems.filter {
                        item -> item.category == "drinks"
                }
            }
        }

        if (searchPhrase != "") {
            menuItems = menuItems.filter {
                    item -> item.title.contains(
                searchPhrase,
                ignoreCase = true
                )
            }
        }
        MenuItemsList(items = menuItems)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent =
            { menuItem ->
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Column{
                        Text(
                            text = menuItem.title,
                            fontSize = 18.sp,
                            color = Color(0xFF333333),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                        )

                        Text(
                            text = menuItem.description,
                            fontSize = 16.sp,
                            color = Color(0xFF495E57),

                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .fillMaxWidth(0.6f)
                        )

                        Text(
                            textAlign = TextAlign.Left,
                            text = "$%.2f".format(menuItem.price),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFF495e57),
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                        )

                    }
                    Spacer(Modifier.weight(1f))

                    GlideImage(
                        model = menuItem.Image,
                        contentDescription = menuItem.title,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    )
                }
                Divider(
                    color = Color(0xFFEDEFEE),
                    thickness = 0.1.dp,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
            }
        )
    }
}