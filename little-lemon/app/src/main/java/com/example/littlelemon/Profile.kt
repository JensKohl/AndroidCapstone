package com.example.littlelemon

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Profile(navController: NavController){

    val context = LocalContext.current

    val sharedPreferences =  context.getSharedPreferences(
        "LittleLemon",
        ComponentActivity.MODE_PRIVATE
    )

    val firstName: String? = sharedPreferences.getString("firstName", "")
    val lastName: String? = sharedPreferences.getString("lastName", "")
    val email: String? = sharedPreferences.getString("email", "")

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "Logo Image",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 30.dp, bottom = 30.dp)
                .fillMaxWidth()
        )
        {
            Text(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp),
                textAlign = TextAlign.Left,
                text = "Personal information:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Row()
        {
            OutlinedTextField(
                value = firstName.toString(),
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = { },
                enabled = false,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                label = { Text(text = "First Name") },
            )
        }
        Row(
            modifier = Modifier
                .padding(top=10.dp, bottom = 10.dp)
                .fillMaxWidth()
        )
        {
            OutlinedTextField(
                value = lastName.toString(),
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = { },
                enabled = false,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                label = { Text(text = "Last Name") },
            )
        }
        Row(
            modifier = Modifier
                .padding(top=10.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = email.toString(),
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = {  },
                enabled = false,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                label = { Text(text = "EMail") },
            )
        }
    }
}