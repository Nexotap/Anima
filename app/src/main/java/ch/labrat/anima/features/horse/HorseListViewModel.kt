package ch.labrat.anima.features.horse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

/**
 * The ViewModel for [HorseListFragment].
 */
class HorseListViewModel internal constructor(horseRepository: HorseRepository) : ViewModel() {

    val horses: LiveData<List<HorseListItem>> = horseRepository.getHorseList()

}
