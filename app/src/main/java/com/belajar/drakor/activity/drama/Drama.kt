package com.belajar.drakor.activity.drama

import android.os.Parcel
import android.os.Parcelable

data class Drama(val title: String, val imageUrl: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Drama> {
        override fun createFromParcel(parcel: Parcel): Drama {
            return Drama(parcel)
        }

        override fun newArray(size: Int): Array<Drama?> {
            return arrayOfNulls(size)
        }
    }
}
