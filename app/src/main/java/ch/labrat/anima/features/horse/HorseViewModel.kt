package ch.labrat.anima.features.horse

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.labrat.anima.utilities.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * The ViewModel used in [HorseAddEditFragment].
 */
class HorseViewModel(
    private var horseRepository: HorseRepository,
    val id: String
) : ViewModel() {

    var horse: LiveData<Horse>

    init {
        if (id == "") {
            horse = MutableLiveData(Horse())
        }
        else {
            horse = horseRepository.getHorse(id)
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
