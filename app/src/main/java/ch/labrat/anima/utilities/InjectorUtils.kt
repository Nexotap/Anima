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

package ch.labrat.anima.utilities

import android.content.Context
import ch.labrat.anima.database.AppDatabase
import ch.labrat.anima.features.breed.BreedViewModelFactory
import ch.labrat.anima.features.breed.BreedListViewModelFactory
import ch.labrat.anima.features.breed.BreedRepository
import ch.labrat.anima.features.horse.HorseListViewModelFactory
import ch.labrat.anima.features.horse.HorseRepository
import ch.labrat.anima.features.horse.HorseViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getHorseRepository(context: Context): HorseRepository {
        return HorseRepository.getInstance(
                AppDatabase.getInstance(context.applicationContext).horseDao())
    }

    private fun getBreedRepository(context: Context): BreedRepository {
        return BreedRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).breedDao())
    }

    fun provideHorseListViewModelFactory(context: Context): HorseListViewModelFactory {
        val repository = getHorseRepository(context)
        return HorseListViewModelFactory(repository)
    }

    fun provideBreedListViewModelFactory(context: Context): BreedListViewModelFactory {
        val repository = getBreedRepository(context)
        return BreedListViewModelFactory(repository)
    }

    fun provideHorseViewModelFactory(
        context: Context,
        id: String
    ): HorseViewModelFactory {
        return HorseViewModelFactory(getHorseRepository(context),id)
    }

    fun provideBreedViewModelFactory(
            context: Context,
            id: String
    ): BreedViewModelFactory {
        return BreedViewModelFactory(getBreedRepository(context),id)
    }



/*
    fun provideGardenHorseingListViewModelFactory(
        context: Context
    ): GardenHorseingListViewModelFactory {
        val repository = getGardenHorseingRepository(context)
        return GardenHorseingListViewModelFactory(repository)
    }
*/
/*
    fun provideHorseDetailViewModelFactory(
        context: Context,
        horseId: String
    ): HorseDetailViewModelFactory {
        return HorseDetailViewModelFactory(getHorseRepository(context),
                getGardenHorseingRepository(context), horseId)
    }
*/
}
