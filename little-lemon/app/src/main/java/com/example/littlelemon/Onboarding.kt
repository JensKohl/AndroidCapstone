package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Onboarding (navController: NavHostController ) {
    var firstName by remember {mutableStateOf("")}
    var lastName by remember {mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    val context = LocalContext.current

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
                //.padding(5.dp)
                .padding(top=30.dp, bottom = 50.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(1.dp),
                textAlign = TextAlign.Left,
                text = "Personal information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            modifier = Modifier
                .padding(top=10.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = firstName,
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = { firstName = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                label = { Text(text = "First Name") },
                placeholder = { Text(text = "Enter your first name") }
            )
        }
        Row(
            modifier = Modifier
                .padding(top=10.dp, bottom = 10.dp)
                .fillMaxWidth()
        )
        {
            OutlinedTextField(
                value = lastName,
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = { lastName = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                label = { Text(text = "Last Name") },
                placeholder = { Text(text = "Enter your name") }
            )
        }
        Row(
            modifier = Modifier
                .padding(top=10.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = email,
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = { email = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                label = { Text(text = "EMail") },
                placeholder = { Text(text = "Enter your mail") }
            )
        }

        Row(
            modifier = Modifier
                .padding(top=100.dp, bottom = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
        ) {
            Button(
                onClick = {
                    if (firstName == "" || lastName == "" || email == "") {

                        Toast.makeText(
                            context,
                            "Registration unsuccessful. Please enter all data",
                            Toast.LENGTH_LONG)
                            .show()

                    } else {

                        val sharedPreference =  context.getSharedPreferences(
                            "LittleLemon",
                            ComponentActivity.MODE_PRIVATE
                        )
                        var editor = sharedPreference.edit()

                        editor.putString("firstName", firstName)
                        editor.putString("lastName", lastName)
                        editor.putString("email", email)

                        editor.commit()

                        Toast.makeText(
                            context,
                            "Registration successful!",
                            Toast.LENGTH_LONG)
                            .show()
                        navController.navigate(Home.route)
                    }
                },
                colors = buttonColors(Color(0xFFF4CE14)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
            ) {
                Text(
                    text = "Register",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}

