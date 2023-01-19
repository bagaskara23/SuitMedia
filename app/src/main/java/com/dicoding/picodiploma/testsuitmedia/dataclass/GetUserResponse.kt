package com.dicoding.picodiploma.testsuitmedia.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class GetUserResponse(
    @PrimaryKey
    @SerializedName("data")
    val data: ArrayList<ListUserItem>
)

data class ListUserItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("avatar")
    val avatar: String,
)

class ConverterList{
    @TypeConverter
    fun fromList(value: List<ListUserItem>) = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<ListUserItem>::class.java).toList()
}