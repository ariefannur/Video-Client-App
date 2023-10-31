package com.github.ariefannur.capsule.feature_capsule.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ariefannur.capsule.feature_capsule.component.UpNextComponent
import com.github.ariefannur.capsule.feature_capsule.data.StatePreview

@Composable
fun LineScreen(data: StatePreview.Lines, toNext: ()-> Unit) {
    Box (modifier = Modifier
        .fillMaxSize()) {
        Column (Modifier.padding(16.dp)){
            Text(text = data.headLine, style = MaterialTheme.typography.titleMedium)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = data.text,
                style = MaterialTheme.typography.bodySmall
            )
        }
        data.upNext?.let {
            UpNextComponent(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp),
                title = it.title, desc = it.description, click = { toNext.invoke() }
            )
        }
    }
}