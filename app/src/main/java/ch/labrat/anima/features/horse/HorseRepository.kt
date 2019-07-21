package ch.labrat.anima.features.horse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository module for handling data operations.
 */
class HorseRepository private constructor(private val horseDao: HorseDao) {

    suspend fun insert(horse: Horse) {
        withContext(Dispatchers.IO) {
            horseDao.insert(horse)
        }
    }

    suspend fun update(horse: Horse) {
        withContext(Dispatchers.IO) {
            horseDao.update(horse)
        }
    }

    suspend fun delete(horse: Horse) {
        withContext(Dispatchers.IO) {
            horseDao.delete(horse)
        }
    }

    fun getHorses() = horseDao.getHorses()
    fun getHorseList() = horseDao.getHorseList()

    fun getHorse(id: String) = horseDao.getHorse(id)
    fun getHorseDetail(id: String) = horseDao.getHorseDetail(id)

    fun getHorseById(id: String) = horseDao.getHorseById(id)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: HorseRepository? = null

        fun getInstance(horseDao: HorseDao) =
            instance ?: synchronized(this) {
                instance ?: HorseRepository(horseDao).also { instance = it }
            }
    }
}
