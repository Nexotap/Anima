package ch.labrat.anima.features.horse

import androidx.room.*
import ch.labrat.anima.features.breed.Breed
import java.util.*
import java.util.Calendar.DAY_OF_YEAR

@Entity(
    tableName = "horses", foreignKeys = [ForeignKey(
        entity = Breed::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("breed_id")
    )], indices = [Index("id"), Index("breed_id", unique = false)]
)
data class Horse(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var nickname: String = "",
    var trimmingInterval: Int = 49, // how often the horse should be trimmed, in days
    var imageUrl: String = "",
    var dateOfBirth: Calendar? = null,
    var gender: Int = -1,
    @ColumnInfo(name = "breed_id")
    var breed: String? = null,
    var color: String = "",
    var physique: String = "",
    var abnormalities: String = "",
    var keeping: String = "",
    var use: String = "",
    var shoeing: String = "",
    var owner: String = "",
    var lastTrimmingDate: Calendar? = null,
    var createdAt: Calendar = Calendar.getInstance(),
    var editedAt: Calendar = Calendar.getInstance()

) {

    /**
     * Determines if the horse should be trimmed.  Returns true if [since]'s date > date of last
     * trimming + trimming Interval; false otherwise.
     */
    fun shouldBeTrimmed(since: Calendar, lastTrimmingDate: Calendar) =
        since > lastTrimmingDate.apply { add(DAY_OF_YEAR, trimmingInterval) }

    override fun toString() = name
}
