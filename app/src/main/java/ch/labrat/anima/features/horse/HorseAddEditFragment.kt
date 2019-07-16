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

        // Populate Breed Spinner
        breedListViewModel.breeds.observe(this, Observer { spinnerData ->
            val spinnerAdapter = BreedSpinnerAdapter(activity as Context, android.R.layout.simple_spinner_item, spinnerData)
            binding.spBreed.adapter = spinnerAdapter
        })

//        binding.spBreed.post { binding.spBreed.setSelection(6) }

        binding.spBreed.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(
                adapterView: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {
                val breed = binding.spBreed.adapter.getItem(position) as Breed
                // Here you can do the action you want to...
                Toast.makeText(context, "ID: " + breed.id + "\nName: " + breed.name,
                    Toast.LENGTH_SHORT
                ).show()
                horseViewModel.horse.value?.breed = breed.name
            }

            override fun onNothingSelected(adapter: AdapterView<*>) {}
        }

        setHasOptionsMenu(true)

        Log.i(TAG,"HorseAddEditFragment:OnCreateView:" + args.id)


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
                findNavController().navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
