package com.example.githubexample.data.entities

import android.os.Parcel
import android.os.Parcelable

data class Owner(val login: String, val avatar_url: String) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(avatar_url)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Owner> {
        override fun createFromParcel(parcel: Parcel): Owner {
            return Owner(parcel)
        }

        override fun newArray(size: Int): Array<Owner?> = arrayOfNulls(size)
    }
}