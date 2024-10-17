package com.kosign.webill.wehrmanagement.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kosign.webill.wehrmanagement.R
import com.kosign.webill.wehrmanagement.data.model.Login
import com.kosign.webill.wehrmanagement.utils.localization.LocalHelper.updateLocale
import java.util.Locale

@Composable
fun LoginScreen(
    vm: LoginVm = hiltViewModel(),
    modifier: Modifier
){
    val state = vm.state.collectAsState().value
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.data == null) {
            if (state.error == null){
                Text(
                    modifier = Modifier.clickable {
                        vm.login(Login(
                            "sokcheng",
                            "5WlBvc*u5W",
                            "UTLZ_590"
                        ))
                    },
                    text = stringResource(R.string.login)
                )
            }else{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Error: ${state.error.asString(context)}"
                    )
                    Text(
                        modifier = Modifier.clickable {
                            vm.login(Login(
                                "sokcheng",
                                "5WlBvc*u5W",
                                "UTLZ_590"
                            ))
                        }.
                        background(
                            color = Color.Cyan,
                            shape = RoundedCornerShape(5.dp)
                        ),
                        text = stringResource(R.string.try_gain)
                    )
                }

            }

        }else{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = stringResource(R.string.welcome, state.data.fullName),
                )

                Text(
                    modifier = Modifier.clickable {
                        vm.getSummary()
                    }.
                    background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(5.dp)
                    ),
                    text = stringResource(R.string.get_summary)
                )
            }

        }

        if (state.isLoading == true){
            CircularProgressIndicator()
        }
    }
    
}
