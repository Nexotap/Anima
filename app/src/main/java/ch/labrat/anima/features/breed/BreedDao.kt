package ch.labrat.anima.features.breed

import androidx.lifecycle.LiveData
import androidx.room.*
import ch.labrat.anima.database.BaseDao

/**
 * The Data Access Object for the Breed class.
 */
@Dao
abstract class BreedDao : BaseDao<Breed>() {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertList(breeds: List<Breed>)

    @Query("DELETE FROM breeds")
    abstract fun deleteAll()

    @Query("SELECT * FROM breeds ORDER BY name")
    abstract fun getBreeds(): LiveData<List<Breed>>

    @Query("SELECT * FROM breeds WHERE id = :id")
    abstract fun getBreed(id: String): LiveData<Breed>

    @Query("select * from breeds where id in (:id)")
    abstract fun getBreedById(id: String): Breed

    @Query("SELECT COUNT(id) FROM breeds")
    abstract fun getDataCount(): LiveData<String>

//    @Query("SELECT * FROM pet WHERE ownerId IS :ownerId")
//    List<Pet> getPetsForOwner(String ownerId);
}
