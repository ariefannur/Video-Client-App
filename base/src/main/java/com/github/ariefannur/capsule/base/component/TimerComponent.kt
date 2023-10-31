package com.github.ariefannur.capsule.base.component


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun TimerComponent() {
    var time by remember { mutableIntStateOf(3 * 60) }
    Row (Modifier.border(1.dp, MaterialTheme.colorScheme.tertiary, MaterialTheme.shapes.medium).padding(8.dp).padding(end = 8.dp)) {
        Icon(imageVector = Icons.Default.Info, contentDescription = "")
        Text(modifier = Modifier.padding(start = 8.dp), text = time.formatMinutes())

        LaunchedEffect(Unit) {
            while (time > 0) {
                delay(1.seconds)
                time--
            }
        }
    }


}

fun Int.formatMinutes(): String {
    return "${(this / 60).toString().padStart(2, '0')}:${
        (this % 60).toString().padStart(2, '0')
    } min"
}

