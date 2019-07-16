package ch.labrat.anima.features.breed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

/**
 * The ViewModel for [BreedListFragment].
 */
class BreedListViewModel internal constructor(breedRepository: BreedRepository) : ViewModel() {
/*
    val categoryIdItemPosition = MutableLiveData<Int>()
    var categoryIdValue
        get() =
            categoryIdItemPosition.value?.let {
                breeds?.get(it)?.id
            }
        set(value) {
            val position = breeds?.indexOfFirst {
                it.id == value
            } ?: -1
            if (position != -1) {
                categoryIdItemPosition.value = position
            }
        }
*/
    val breeds: LiveData<List<Breed>> = breedRepository.getBreeds()

}
