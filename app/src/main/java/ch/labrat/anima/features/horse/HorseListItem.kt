package ch.labrat.anima.features.horse

import androidx.room.ColumnInfo

data class HorseListItem(
    @ColumnInfo(name ="horsename")
    var horseName: String? ="",

    @ColumnInfo(name ="breedname")
    var breedName: String? =""
)
