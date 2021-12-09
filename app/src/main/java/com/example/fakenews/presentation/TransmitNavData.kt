package com.example.fakenews.presentation

import android.os.Parcelable
import com.example.fakenews.presentation.enums.TopicEnum
import com.example.fakenews.presentation.recycler.News
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransmitNavData(
    val filter:  String,
    val groupInfo: String
):Parcelable
