package ch.labrat.anima.features.horse

import androidx.room.ColumnInfo

data class HorseListItem(
    @ColumnInfo(name ="id")
    var id: String ="",

    @ColumnInfo(name ="name")
    var name: String? ="",

    @ColumnInfo(name ="breedname")
    var breedName: String? ="",

    @ColumnInfo(name ="gender")
    var gender: Int? = -1

)
