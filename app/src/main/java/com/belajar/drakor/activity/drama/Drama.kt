package com.belajar.drakor.activity.drama

import android.os.Parcel
import android.os.Parcelable

data class Drama(val title: String, val imageUrl: String?, var isFavorite: Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeByte(if (isFavorite) 1 else 0)
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
