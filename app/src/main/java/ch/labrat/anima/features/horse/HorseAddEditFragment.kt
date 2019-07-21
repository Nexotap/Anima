package ch.labrat.anima.features.horse

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ch.labrat.anima.R
import ch.labrat.anima.databinding.FragmentHorseAddEditBinding
import ch.labrat.anima.features.breed.BreedListViewModel
import ch.labrat.anima.features.breed.BreedSpinnerAdapter
import ch.labrat.anima.utilities.InjectorUtils
import android.widget.AdapterView
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener
import ch.labrat.anima.features.breed.Breed
import ch.labrat.anima.utilities.TAG


/**
 * A fragment representing a single Horse add/edit screen.
 */
class HorseAddEditFragment : Fragment() {
    private val args: HorseAddEditFragmentArgs by navArgs()
    private val horseViewModel: HorseViewModel by viewModels {
        InjectorUtils.provideHorseViewModelFactory(requireActivity(), args.id)
    }
    private val breedListViewModel: BreedListViewModel by viewModels {
        InjectorUtils.provideBreedListViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHorseAddEditBinding>(
            inflater, R.layout.fragment_horse_add_edit, container, false).apply {
            viewModel = horseViewModel
            lifecycleOwner = this@HorseAddEditFragment
        }

        // Populate Breed Spinner and set selected value
        breedListViewModel.breeds.observe(this, Observer { spinnerData ->
            val spinnerAdapter = BreedSpinnerAdapter(activity as Context, android.R.layout.simple_spinner_item, spinnerData)
            binding.spBreed.adapter = spinnerAdapter

            // Get selected value from viewmodel and set selected value of breed spinner
            horseViewModel.horse.value?.breed.let {
                if ( it != null) {
                    val position = (binding.spBreed.adapter as BreedSpinnerAdapter).getPositionById(it)
                    binding.spBreed.setSelection(position)
                    binding.spBreed.tag = position
                }
            }

        })

        binding.spBreed.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {
                // Set selected value in the viewmodel
                val breed = binding.spBreed.adapter.getItem(position) as Breed
                horseViewModel.horse.value?.breed = breed.id
            }

            override fun onNothingSelected(adapter: AdapterView<*>) {}
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_edit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                if (args.id == "") {
                    horseViewModel.insert()
                } else {
                    horseViewModel.update()
                }
                Log.i(TAG,"onOptionsItemSelected() gender: " + horseViewModel.horse.value?.gender)
                findNavController().navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
