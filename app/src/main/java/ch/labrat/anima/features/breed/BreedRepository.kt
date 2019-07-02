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

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository module for handling data operations.
 */
class BreedRepository private constructor(private val breedDao: BreedDao) {

    suspend fun insert(breed: Breed) {
        withContext(Dispatchers.IO) {
            breedDao.insert(breed)
        }
    }

    suspend fun update(breed: Breed) {
        withContext(Dispatchers.IO) {
            breedDao.update(breed)
        }
    }

    suspend fun delete(breed: Breed) {
        withContext(Dispatchers.IO) {
            breedDao.delete(breed)
        }
    }

    fun getBreeds() = breedDao.getBreeds()

    fun getBreed(id: String) = breedDao.getBreed(id)

    fun getBreedById(id: String) = breedDao.getBreedById(id)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: BreedRepository? = null

        fun getInstance(breedDao: BreedDao) =
                instance ?: synchronized(this) {
                    instance ?: BreedRepository(breedDao).also { instance = it }
                }
    }
}
