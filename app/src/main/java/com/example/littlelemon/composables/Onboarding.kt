package com.example.littlelemon.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.example.littlelemon.R
import com.example.littlelemon.navigation.Home
import com.example.littlelemon.ui.theme.*

@Composable
fun Onboarding(onNavHome: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(Modifier.fillMaxWidth(0.6f)) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon Logo"
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .height(150.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Let's get to know you",
                    style = Typography.h1,
                    color = PrimaryGreen
                )
            }
        }
        item {
            Text(
                text = "Personal Information",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                style = Typography.h3
            )
        }
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedLabelColor = PrimaryGreen,
                        focusedBorderColor = PrimaryGreen
                    ),
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedLabelColor = PrimaryGreen,
                        focusedBorderColor = PrimaryGreen
                    ),
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedLabelColor = PrimaryGreen,
                        focusedBorderColor = PrimaryGreen
                    ),
                )

                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                            showToast(
                                context,
                                "Registration unsuccessful. Please enter all data."
                            )
                        } else {
                            saveUserData(context, firstName, lastName, email)
                            showToast(context, "Registration successful!")

                            // Navigate to Home screen
//                            navController.navigate(Home.route)
                            onNavHome()
                        }
                    },
                    border = BorderStroke(2.dp, PrimaryGreen),
                    modifier = Modifier.fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryYellow,
                        contentColor = PrimaryGreen
                    ),
                ) {
                    Text(text = "Register")
                }
            }
        }

    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private fun saveUserData(context: Context, firstName: String, lastName: String, email: String) {
    val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    sharedPref.edit {
        putString("firstName", firstName)
        putString("lastName", lastName)
        putString("email", email)
        putBoolean("userRegistered", true)
    }
}