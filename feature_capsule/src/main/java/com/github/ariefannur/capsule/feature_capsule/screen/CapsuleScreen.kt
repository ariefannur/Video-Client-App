package com.github.ariefannur.capsule.feature_capsule.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.ariefannur.capsule.base.component.TabComponent
import com.github.ariefannur.capsule.base.component.TimerComponent
import com.github.ariefannur.capsule.feature_capsule.R
import com.github.ariefannur.capsule.feature_capsule.data.DummyData
import com.github.ariefannur.capsule.feature_capsule.data.StatePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapsuleScreen(isDarkMode: Boolean = false, onChangeMode: (Boolean) -> Unit = {}) {
    val viewModel: CapsuleViewModel = CapsuleViewModel()
    var page by remember {
        mutableStateOf("Video")
    }
    var statePreview by remember {
        mutableStateOf<StatePreview>(DummyData.dataVideo)
    }

    var menuExpanded by rememberSaveable { mutableStateOf(false) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Blood") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    if (page == "Quiz")
                        TimerComponent()
                    else {
                        IconButton(onClick = { menuExpanded = !menuExpanded }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Localized description"
                            )
                        }
                        DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = !menuExpanded }) {
                            DropdownMenuItem(text = {
                                Text(text = stringResource(if (isDarkMode) R.string.light_mode else R.string.dark_mode))
                            }, onClick = {
                                menuExpanded = !menuExpanded
                                onChangeMode.invoke(!isDarkMode)
                            })
                        }
                    }
                }
            )
        }
    ) {

        Column (Modifier.padding(it)) {
            TabComponent(datas = viewModel.getTitles(), onSelectedScreen = { title ->
                page = title
                statePreview = viewModel.getStatePreview(page)
            }, currentIndex = viewModel.getTitles().indexOf(page))

            when(page) {
                "Video" -> VideoScreen(statePreview as StatePreview.Video, toNext = {
                    statePreview.upNext?.let { next ->
                        page = next.next.title
                        statePreview = next.next
                    }
                })
                "Lines" -> LineScreen(statePreview as StatePreview.Lines, toNext = {
                    statePreview.upNext?.let { next ->
                        page = next.next.title
                        statePreview = next.next
                    }
                })
                "Notes" -> NoteScreen(statePreview as StatePreview.Notes, toNext = {
                    statePreview.upNext?.let { next ->
                        page = next.next.title
                        statePreview = next.next
                    }
                })
                "Quiz" -> QuestionScreen(statePreview as StatePreview.Quiz)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CapsulePreview() {
    CapsuleScreen(true)
}
