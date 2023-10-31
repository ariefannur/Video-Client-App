package com.github.ariefannur.capsule.feature_capsule.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.github.ariefannur.capsule.feature_capsule.component.UpNextComponent
import com.github.ariefannur.capsule.feature_capsule.data.StatePreview
import com.github.ariefannur.capsule.feature_capsule.player.VideoPlayer

@Composable
fun NoteScreen(data: StatePreview.Notes, toNext: ()-> Unit) {
    // lifecycle Aware
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val latestLifecycleEvent = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            latestLifecycleEvent.value = event
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
    val isIdle by remember {
        derivedStateOf {
            latestLifecycleEvent.value == Lifecycle.Event.ON_PAUSE
        }
    }
    Box (modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 32.dp)) {
            Text(text = data.title+":", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp))
            VideoPlayer(uri = data.uri, idle = isIdle)
        }
        data.upNext?.let {
            UpNextComponent(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp),
                title = it.title, desc = it.description,
                click = { toNext.invoke() }
            )
        }
    }
}