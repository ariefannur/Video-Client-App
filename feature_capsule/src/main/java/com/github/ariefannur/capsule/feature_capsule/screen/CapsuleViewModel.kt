package com.github.ariefannur.capsule.feature_capsule.screen

import androidx.lifecycle.ViewModel
import com.github.ariefannur.capsule.feature_capsule.data.DummyData
import com.github.ariefannur.capsule.feature_capsule.data.StatePreview

class CapsuleViewModel: ViewModel() {

    fun getTitles() = DummyData.getTitles()

    fun getStatePreview(title: String): StatePreview  {
        return DummyData.getStatePreview(title)
    }

    fun toNextPage(title: String) = DummyData.getStatePreview(title).upNext?.next

}