package ch.labrat.anima.features.breed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

/**
 * The ViewModel for [BreedListFragment].
 */
class BreedListViewModel internal constructor(breedRepository: BreedRepository) : ViewModel() {

    val breeds: LiveData<List<Breed>> = breedRepository.getBreeds()

}
