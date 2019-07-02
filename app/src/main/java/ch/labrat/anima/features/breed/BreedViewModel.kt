package ch.labrat.anima.features.breed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * The ViewModel used in [BreedAddEditFragment].
 */
class BreedViewModel(
    private var breedRepository: BreedRepository,
    val id: String
) : ViewModel() {

    var breed: LiveData<Breed>

    init {
        if (id == "") {
            breed = MutableLiveData<Breed>(Breed())
        }
        else {
            breed = breedRepository.getBreed(id)
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
            breedRepository.insert(breed.value as Breed)
        }
    }

    fun update() {
        viewModelScope.launch {
            breedRepository.update(breed.value as Breed)
        }
    }

    fun delete() {
        viewModelScope.launch {
            breedRepository.delete(breed.value as Breed)
        }
    }


}
