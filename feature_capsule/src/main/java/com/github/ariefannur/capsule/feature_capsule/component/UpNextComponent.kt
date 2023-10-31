package com.github.ariefannur.capsule.feature_capsule.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UpNextComponent(modifier: Modifier, title: String, desc: String, click: ()-> Unit) {
    Column (
        modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Text(text = "Up Next", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp))
        Card  (modifier = Modifier.clickable {
              click.invoke()
        }, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)) {
            Box (Modifier.fillMaxWidth().padding(8.dp)) {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = desc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Icon(modifier = Modifier.align(Alignment.CenterEnd), imageVector = Icons.Filled.ArrowForward, contentDescription = "")
            }
        }
    }
}