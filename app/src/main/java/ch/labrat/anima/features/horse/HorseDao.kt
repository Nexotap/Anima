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

package ch.labrat.anima.features.horse

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the Horse class.
 */
@Dao
interface HorseDao {
    @Query("SELECT * FROM horses ORDER BY name")
    fun getHorses(): LiveData<List<Horse>>

//    @Query("SELECT * FROM horses WHERE growZoneNumber = :growZoneNumber ORDER BY name")
//    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): LiveData<List<Plant>>

    @Query("SELECT * FROM horses WHERE id = :id")
    fun getHorse(id: String): LiveData<Horse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHorses(horses: List<Horse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHorse(horse: Horse)
}