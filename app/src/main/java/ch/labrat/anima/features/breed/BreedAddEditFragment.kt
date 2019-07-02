package ch.labrat.anima.features.breed

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ch.labrat.anima.R
import ch.labrat.anima.databinding.FragmentBreedAddEditBinding
import ch.labrat.anima.utilities.InjectorUtils

/**
 * A fragment representing a single Breed add/edit screen.
 */
class BreedAddEditFragment : Fragment() {
    private val args: BreedAddEditFragmentArgs by navArgs()
    private val breedViewModel: BreedViewModel by viewModels {
        InjectorUtils.provideBreedDetailViewModelFactory(requireActivity(), args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentBreedAddEditBinding>(
            inflater, R.layout.fragment_breed_add_edit, container, false).apply {
            viewModel = breedViewModel
            lifecycleOwner = this@BreedAddEditFragment
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
                    breedViewModel.insert()
                } else {
                    breedViewModel.update()
                }
                findNavController().navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
