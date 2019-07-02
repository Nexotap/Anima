package ch.labrat.anima.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ch.labrat.anima.database.AppDatabase
import ch.labrat.anima.features.breed.Breed
import ch.labrat.anima.utilities.BREED_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import java.util.*

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override suspend fun doWork(): Result = coroutineScope {

        try {
            applicationContext.assets.open(BREED_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Breed>>() {}.type
                    val plantList: List<Breed> = Gson().fromJson(jsonReader, plantType)

                    plantList.forEach{
                        it.id = UUID.randomUUID().toString()
                        it.createdAt = Calendar.getInstance()
                        it.editedAt = Calendar.getInstance()
                    }

                    val database = AppDatabase.getInstance(applicationContext)
                    database.breedDao().insertList(plantList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }
}