package com.mtjin.bungsegwon.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Menu(
    val id: Long,
    val name: String,
    val priority: Int
): Parcelable
