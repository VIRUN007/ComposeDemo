package com.kosign.webill.wehrmanagement.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.kosign.webill.wehrmanagement.R
import com.kosign.webill.wehrmanagement.utils.localization.LocalHelper
import com.kosign.webill.wehrmanagement.utils.localization.LocalHelper.updateLocale
import java.util.Locale

@Composable
fun LocalizationExample(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val context = LocalContext.current
    var greeting by remember { mutableStateOf(context.getString(R.string.greeting)) }

    Box(modifier = modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = greeting)

            Button(onClick = {
                val local = Locale("km")
                context.updateLocale(local)
                LocalHelper.saveLocale(context, locale = local)
                greeting = context.getString(R.string.greeting)
            }) {
                Text(text = "Switch to Khmer")
            }

            Button(onClick = {
                val local = Locale("en")
                context.updateLocale(local)
                LocalHelper.saveLocale(context, locale = local)
                greeting = context.getString(R.string.greeting)
            }) {
                Text(text = "Switch to English")
            }

            Button(onClick = {
                navController.navigate("Login"){
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            }) {
                Text(text = "Login")
            }
        }
    }

}
