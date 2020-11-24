package com.vershininivan.weather.items

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class FavoriteListItem(
    val city: String?,
    val city_ascii: String?,
    val country: String?,
    val lat: Double,
    val lon: Double
): Parcelable {

  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readDouble(),
      parcel.readDouble()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(city)
    parcel.writeString(city_ascii)
    parcel.writeString(country)
    parcel.writeDouble(lat)
    parcel.writeDouble(lon)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<FavoriteListItem> {
    override fun createFromParcel(parcel: Parcel): FavoriteListItem {
      return FavoriteListItem(parcel)
    }

    override fun newArray(size: Int): Array<FavoriteListItem?> {
      return arrayOfNulls(size)
    }
  }
}