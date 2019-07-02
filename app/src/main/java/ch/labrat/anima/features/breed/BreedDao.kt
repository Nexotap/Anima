/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
