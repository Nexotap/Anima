package ch.labrat.anima.features.horse

import androidx.lifecycle.LiveData
import androidx.room.*
import ch.labrat.anima.database.BaseDao

/**
 * The Data Access Object for the Horse class.
 */
@Dao
abstract class HorseDao : BaseDao<Horse>() {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertList(horses: List<Horse>)

    @Query("DELETE FROM horses")
    abstract fun deleteAll()

    @Query("SELECT * FROM horses ORDER BY name")
    abstract fun getHorses(): LiveData<List<Horse>>

    @Query("SELECT * FROM horses WHERE id = :id")
    abstract fun getHorse(id: String): LiveData<Horse>

    @Query("select * from horses where id in (:id)")
    abstract fun getHorseById(id: String): Horse

    @Query("SELECT COUNT(id) FROM horses")
    abstract fun getDataCount(): LiveData<String>

}
