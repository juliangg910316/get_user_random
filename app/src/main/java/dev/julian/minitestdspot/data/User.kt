package dev.julian.minitestdspot.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "user_id") val userId: Int,
    @Embedded val id: Id,
    @Embedded val name : Name,
    val location : String,
    val email : String,
    @Embedded val picture: Picture,
    )

data class Name (
    val title : String,
    val first : String,
    val last  : String
) {
    fun nameComplete() = "$title $first $last"
}

data class Coordinates (
    val latitude  : String? = null,
    val longitude : String? = null
)

data class TimeZone (
    val offset  : String? = null,
    val description : String? = null
)

data class Location (
    val street      : String?,
    val city        : String?,
    val state       : String?,
    val postcode    : Int,
    //@Embedded val coordinates : Coordinates,
    //@Embedded val timezone    : TimeZone
)

data class Id (
    val name  : String? = null,
    val value : String? = null
)

data class Picture (
    val large     : String? = null,
    val medium    : String? = null,
    val thumbnail : String? = null
)

data class InfoService (
    val seed : String,
    val result : Int,
    val page : Int,
        )