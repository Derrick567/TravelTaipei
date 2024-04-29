package com.example.traveltaipei.home.model.remote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class News(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val files: @RawValue List<Any> = emptyList(),
    val begin: @RawValue Any = "",
    val end: @RawValue Any= "",
    val links: @RawValue List<Link> = emptyList(),
    val modified: String = "",
    val posted: String = "",
    val url: String = ""
): Parcelable