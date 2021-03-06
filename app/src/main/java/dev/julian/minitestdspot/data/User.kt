package dev.julian.minitestdspot.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "user_id") val userId: Int,
    @Embedded val id: Id,
    @Embedded val name : Name,
    @Embedded val location : Location,
    val email : String,
    @Embedded val picture: Picture,
    ) : Serializable

data class Name (
    val title : String,
    val first : String,
    val last  : String
) {
    fun nameComplete() = "$title $first $last"
}

data class Coordinates (
    val latitude  : String,
    val longitude : String
)

data class TimeZone (
    val offset  : String,
    val description : String
)

data class Street (
    val number  : Int,
    val name : String
)

data class Location (
    @Embedded(
        prefix = "street_"
    ) val street      : Street,
    val city        : String,
    val state       : String,
    val country       : String,
    val postcode    : String,
    @Embedded val coordinates : Coordinates,
    @Embedded val timezone    : TimeZone
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