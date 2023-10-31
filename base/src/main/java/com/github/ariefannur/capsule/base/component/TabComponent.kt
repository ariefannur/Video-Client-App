package com.github.ariefannur.capsule.base.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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

@Composable
fun TabComponent(
    datas: List<String>,
    onSelectedScreen: (String) -> Unit,
    currentIndex: Int = -1
) {
    var index by remember { mutableIntStateOf(0) }
    LaunchedEffect(currentIndex) {
        index = currentIndex
    }
    LazyRow (Modifier.padding(start = 16.dp, end = 16.dp)){
        itemsIndexed(datas) {idx, data ->
            Column (
                Modifier
                    .clickable {
                        index = idx
                        onSelectedScreen.invoke(data)
                    }
                    .padding(8.dp)) {
                Text(text = data, style = MaterialTheme.typography.bodyMedium)
                Box(modifier = Modifier
                    .width(50.dp)
                    .height(3.dp)
                    .background(if (index == idx) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary))
            }
        }
    }
}