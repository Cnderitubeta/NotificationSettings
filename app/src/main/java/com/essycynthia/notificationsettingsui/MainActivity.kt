package com.essycynthia.notificationsettingsui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.unit.sp
import com.essycynthia.notificationsettingsui.ui.theme.NotificationSettingsUITheme
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

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
                    Navigation()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppMainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Welcome to Software Update Notifications")
        }

    }

}

@Composable
fun NotificationScreen() {
    var developerUpdatedOn by remember { mutableStateOf("") }
    var userUpdatedOn by remember { mutableStateOf("") }


    Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = developerUpdatedOn,
            label = { Text(text = "Enter developer last update date") },
            placeholder = { Text(text = "YYYY-MM-DD") },
            onValueChange = {
                developerUpdatedOn = it
            }
        )
        OutlinedTextField(
            value = userUpdatedOn,
            label = { Text(text = "Enter playstore last update date") },
            placeholder = { Text(text = "YYYY-MM-DD") },
            onValueChange = {
                userUpdatedOn = it
            }
        )
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val currentDate = Date()
        var developerUpdateDate: Date? = null
        var userUpdateDate: Date? = null

        try {
            if (developerUpdatedOn.isNotEmpty()) {
                developerUpdateDate = dateFormat.parse(developerUpdatedOn)
            }

            if (userUpdatedOn.isNotEmpty()) {
                userUpdateDate = dateFormat.parse(userUpdatedOn)
            }
        } catch (e: ParseException) {
            // Handle the case when the date format is incorrect
            // Show an error message or perform any necessary action
            return
        }
        val isOutdated = userUpdateDate?.before(developerUpdateDate) ?: false
        val daysOutOfDate = if (isOutdated && userUpdateDate != null) {
            val currentDateWithoutTime = LocalDate.now()
            val userUpdateDateWithoutTime = userUpdateDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            ChronoUnit.DAYS.between(userUpdateDateWithoutTime, currentDateWithoutTime)
        } else {
            0 // Default value if conditions are not met
        }
        val notice = MyNotification(
            LocalContext.current,
            "Software Update Notification",
            if (isOutdated) "The app is out of date for $daysOutOfDate days" else "The app is up to date"
        )
        notice.firNotification()

        Text(text = "Is App Out of Date: ${if (isOutdated) "Yes" else "No"}")
        Text(text = "Days Out of Date: $daysOutOfDate")

    }
}

@Composable
fun NotificationUi() {
    Column(
        Modifier.fillMaxSize()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_notifications_none_24),
                contentDescription = "bell",
                Modifier
                    .size(40.dp)
                    .padding(top = 10.dp)
            )
            Column() {
                Text(
                    text = "Software Update Notification",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Select Frequency",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Light),
                )

            }
            Spacer(modifier = Modifier.height(10.dp))

            val checkedState = remember { mutableStateOf(false) }
            Switch(
                checked = checkedState.value, onCheckedChange = { checkedState.value = it },
                colors = SwitchDefaults.colors(Color(0xFF2AAA8A))

            )


        }


        val selectedTime = remember {
            mutableStateOf("")
        }
        Column {
            Row {
                RadioButton(
                    selected = selectedTime.value == Time.daily,
                    onClick = { selectedTime.value = Time.daily },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                        unselectedColor = Color(0xFF2AAA8A)

                    ),
                )
                Text(
                    text = Time.daily,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier.padding(top = 10.dp, start = 0.5.dp)
                )

            }
            Spacer(modifier = Modifier.height(3.dp))
            Row {
                RadioButton(
                    selected = selectedTime.value == Time.two_days,
                    onClick = { selectedTime.value = Time.two_days },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                        unselectedColor = Color(0xFF2AAA8A)

                    ),
                )
                Spacer(modifier = Modifier.size(1.dp))
                Text(
                    Time.two_days,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 10.dp)

                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            Row {
                RadioButton(
                    selected = selectedTime.value == Time.five_days,
                    onClick = { selectedTime.value = Time.five_days },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                        unselectedColor = Color(0xFF2AAA8A)

                    ),
                )
                Text(
                    text = Time.five_days,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier.padding(top = 10.dp, start = 0.5.dp)

                )
            }
            Spacer(modifier = Modifier.height(3.dp))

            Row {
                RadioButton(
                    selected = selectedTime.value == Time.fifteen_days,
                    onClick = { selectedTime.value = Time.fifteen_days },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                        unselectedColor = Color(0xFF2AAA8A)

                    )
                )
                Text(
                    text = Time.fifteen_days,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier.padding(top = 10.dp, start = 0.5.dp)

                )
            }
            Spacer(modifier = Modifier.height(3.dp))

            Row {
                RadioButton(
                    selected = selectedTime.value == Time.monthly,
                    onClick = { selectedTime.value = Time.monthly },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                        unselectedColor = Color(0xFF2AAA8A)

                    ),
                )
                Text(
                    text = Time.monthly,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier.padding(top = 10.dp, start = 0.5.dp)

                )
            }
        }


    }
}

