package com.github.ariefannur.capsule.feature_capsule.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ariefannur.capsule.feature_capsule.data.DummyData
import com.github.ariefannur.capsule.feature_capsule.data.StatePreview

const val CORRECT = 1
const val WRONG = 2

@Composable
fun QuestionScreen(data: StatePreview.Quiz) {
    var index by remember {
        mutableIntStateOf(0)
    }
    var indexChoice by remember {
        mutableIntStateOf(-1)
    }

    var stateAnswer by remember {
        mutableIntStateOf(0) // 0 mean didn't answer 1 mean correct 2 mean wrong
    }

    Box (Modifier.fillMaxSize()) {
        Column {
            Row(Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .weight(5f)
                        .height(36.dp)
                        .padding(top = 8.dp, start = 16.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.small
                        ),
                    contentAlignment = Alignment.Center

                ) {
                    Text(text = "Question ${index+1}/10", textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onPrimary)
                }
                Row(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary))
            ) {
                Text(text = data.questions[index].question, modifier = Modifier.padding(8.dp))
            }


            LazyColumn (
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp, end = 8.dp
                    )){
                itemsIndexed(data.questions[index].option) { idx , data ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .background(
                            color = if (stateAnswer == WRONG && idx == indexChoice) {
                                MaterialTheme.colorScheme.errorContainer
                            } else if (indexChoice == idx) {
                                MaterialTheme.colorScheme.tertiaryContainer
                            } else MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(8.dp)
                        .clickable {
                            indexChoice = idx
                            stateAnswer = 0
                        }
                    ) {
                        Text(text = data, textAlign = TextAlign.Center)
                        if (stateAnswer > 0 && idx == indexChoice) {
                            Icon(imageVector = if (stateAnswer == CORRECT) Icons.Filled.Check else Icons.Filled.Close , contentDescription = "", modifier = Modifier.align(Alignment.BottomEnd))
                        }
                    }
                }
            }
        }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
                    .padding(start = 16.dp, end = 16.dp)

            ) {
                Box(modifier = Modifier
                    .weight(3f)
                    .padding(top = 4.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp)
                    .clickable {
                        if (data.questions[index].correct == data.questions[index].option[indexChoice]) {
                            stateAnswer = CORRECT
                        } else {
                            stateAnswer = WRONG
                        }
                    },
                    contentAlignment = Alignment.BottomCenter) {
                    Text(text = "Check Answer", color = MaterialTheme.colorScheme.onPrimary)
                }
                IconButton(onClick = {
                    if (index+1 < data.questions.size ) index ++
                }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")

                }
            }
    }
}

@Preview
@Composable
fun QuestionScreenPreview() {
    QuestionScreen(data = DummyData.dataQuestion)
    
}