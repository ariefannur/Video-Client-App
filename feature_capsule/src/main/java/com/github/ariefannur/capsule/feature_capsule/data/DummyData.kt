package com.github.ariefannur.capsule.feature_capsule.data

import android.net.Uri

object DummyData {

    val dataQuestion = StatePreview.Quiz(
        title = "Quiz",
        questions = listOf(
            Question("2 + 3 + 4 = ?", listOf(
                "2", "3", "4", "5"
            )),
            Question("What is black", listOf(
                "Color", "Think", "Word", "Animal"
            )),
            Question("What is black", listOf(
                "Color", "Think", "Word", "Animal"
            )),
            Question("What is black", listOf(
                "Color", "Think", "Word", "Animal"
            ))
        ),
        upNext = null
    )

    val dataNotes = StatePreview.Notes(
        uri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
        title = "Notes",
        upNext = UpNext(dataQuestion, "Next Screen", "This is description of next")
    )

    val dataLine = StatePreview.Lines(
        title = "Lines",
        headLine = "Lorem Ipsum",
        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
        upNext = UpNext(dataNotes, "Next Screen", "This is description of next")
    )

    val dataVideo = StatePreview.Video(
        uri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
        title = "Video",
        upNext = UpNext(dataLine, "Next Screen", "This is description of next")
    )


    fun getTitles(): List<String> = listOf(
        "Video", "Lines", "Notes", "Quiz"
    )

    fun getStatePreview(title: String): StatePreview {
        return when (title) {
            "Video" -> dataVideo
            "Lines" -> dataLine
            "Notes" -> dataNotes
            else -> dataQuestion
        }
    }

}