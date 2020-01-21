package com.example.githubexample.data.entities

import android.os.Parcel
import android.os.Parcelable

data class Repos(val name: String, val owner: Owner, val stargazers_count: Int, val open_issues_count: Int) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readParcelable(Owner::class.java.classLoader), parcel.readInt(), parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeParcelable(owner, flags)
        parcel.writeInt(stargazers_count)
        parcel.writeInt(open_issues_count)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Repos> {
        override fun createFromParcel(parcel: Parcel): Repos {
            return Repos(parcel)
        }

        override fun newArray(size: Int): Array<Repos?> = arrayOfNulls(size)
    }
}