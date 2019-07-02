package ch.labrat.anima.features.breed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory for creating a [BreedListViewModel] with a constructor that takes a [BreedRepository].
 */
class BreedListViewModelFactory(
    private val repository: BreedRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = BreedListViewModel(repository) as T
}
