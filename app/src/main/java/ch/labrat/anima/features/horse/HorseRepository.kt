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

/**
 * Repository module for handling data operations.
 */
class HorseRepository private constructor(private val horseDao: HorseDao) {

    fun addHorse(horse: Horse) {
        horseDao.addHorse(horse)
    }

    fun getHorses() = horseDao.getHorses()

    fun getHorse(horseId: String) = horseDao.getHorse(horseId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: HorseRepository? = null

        fun getInstance(horseDao: HorseDao) =
                instance ?: synchronized(this) {
                    instance ?: HorseRepository(horseDao).also { instance = it }
                }
    }
}
