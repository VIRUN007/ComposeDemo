package com.kosign.webill.wehrmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.kosign.webill.wehrmanagement.navigation.Navigation
import com.kosign.webill.wehrmanagement.ui.theme.WeHRManagementTheme
import com.kosign.webill.wehrmanagement.utils.localization.LocalHelper.loadLocale
import com.kosign.webill.wehrmanagement.utils.localization.LocalHelper.updateLocale
import com.kosign.webill.wehrmanagement.utils.networkconnection.ConnectionState
import com.kosign.webill.wehrmanagement.utils.networkconnection.connectivityState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Load and apply the saved locale
        // This will set your current Local to what you have saved from your Persistent store
        // it work because there only on activity.
        val locale = loadLocale(this)
        updateLocale(locale)
        setContent {
            WeHRManagementTheme {
                // internet connection
                val connection by connectivityState()
                val isConnected = connection === ConnectionState.Available
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        if (isConnected.not()) {
                            Snackbar(
                                action = {}, modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "Please check your connection !")
                            }
                        }
                    }
                ) { innerPadding ->
                    Navigation(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeHRManagementTheme {
        Greeting("Android")
    }
}