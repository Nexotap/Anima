package ch.labrat.anima.features.breed

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "breeds")
data class Breed(
        @PrimaryKey var id: String = UUID.randomUUID().toString(),
        var name: String = "",
        var description: String = "",
        var imageUrl: String = "",
        var createdAt: Calendar = Calendar.getInstance(),
        var editedAt: Calendar = Calendar.getInstance()
) {
    override fun toString() = name
}
