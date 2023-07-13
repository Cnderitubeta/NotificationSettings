package com.essycynthia.notificationsettingsui

import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essycynthia.notificationsettingsui.ui.theme.NotificationSettingsUITheme

object Time {
    const val daily = "Daily"
    const val two_days = "2 days"
    const val five_days = "5 days"
    const val fifteen_days = "15 days"
    const val monthly = "Monthly"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationSettingsUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    NotificationUi()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationUi() {
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_notifications_none_24),
                contentDescription = "bell",
                Modifier.size(50.dp)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Software Update Notification",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Select Frequency",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Light)
                )

            }
            val checkedState = remember { mutableStateOf(false) }
            Switch(
                checked = checkedState.value, onCheckedChange = { checkedState.value = it },
                colors = SwitchDefaults.colors(Color.Green)

            )


        }
        Spacer(modifier = Modifier.height(150.dp))


        val selectedTime = remember {
            mutableStateOf("")
        }
        Column {
            Row {
                RadioButton(
                    selected = selectedTime.value == Time.daily,
                    onClick = { selectedTime.value = Time.daily },
                    colors = RadioButtonDefaults.colors(
                        Color.Green,

                        ),

                )
                Text(
                    text = Time.daily,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(10.dp)
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                RadioButton(
                    selected = selectedTime.value == Time.two_days,
                    onClick = { selectedTime.value = Time.two_days },
                    colors = RadioButtonDefaults.colors(
                        Color.Green
                    )
                )
                Text(
                    text = Time.two_days,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(10.dp)

                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                RadioButton(
                    selected = selectedTime.value == Time.five_days,
                    onClick = { selectedTime.value = Time.five_days },
                    colors = RadioButtonDefaults.colors(
                        Color.Green
                    )
                )
                Text(
                    text = Time.five_days,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 10.dp)

                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                RadioButton(
                    selected = selectedTime.value == Time.fifteen_days,
                    onClick = { selectedTime.value = Time.fifteen_days },
                    colors = RadioButtonDefaults.colors(
                        Color.Green
                    )
                )
                Text(
                    text = Time.fifteen_days,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top =10.dp)

                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                RadioButton(
                    selected = selectedTime.value == Time.monthly,
                    onClick = { selectedTime.value = Time.monthly },
                    colors = RadioButtonDefaults.colors(
                        Color.Green
                    )
                )
                Text(
                    text = Time.monthly,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 10.dp)

                )
            }
        }


    }
}

