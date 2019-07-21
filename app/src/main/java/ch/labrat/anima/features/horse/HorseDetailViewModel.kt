package ch.labrat.anima.features.horse

import android.util.Log
import androidx.lifecycle.*
import ch.labrat.anima.utilities.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * The ViewModel used in [HorseAddEditFragment].
 */
class HorseDetailViewModel(
    private var horseRepository: HorseRepository,
    val id: String
) : ViewModel() {

    var horse: LiveData<HorseDetail>

    init {
        if (id == "") {
            horse = MutableLiveData(HorseDetail())
        }
        else {
            horse = horseRepository.getHorseDetail(id)
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    fun insert() {

        viewModelScope.launch {
            horseRepository.insert(horse.value as Horse)
        }
    }

    fun update() {
        viewModelScope.launch {
            horseRepository.update(horse.value as Horse)
        }
    }

    fun delete() {
        viewModelScope.launch {
            horseRepository.delete(horse.value as Horse)
        }
    }
}

/**
 * Factory for creating a [HorseDetailViewModel] with a constructor that takes a [HorseRepository]
 * and an ID for the current [Horse].
 */
class HorseDetailViewModelFactory(
    private val horseRepository: HorseRepository,
    private val id: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HorseDetailViewModel(horseRepository, id) as T
    }
}
