package ch.labrat.anima.features.breed

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ch.labrat.anima.R
import ch.labrat.anima.databinding.FragmentBreedDetailBinding
import ch.labrat.anima.utilities.InjectorUtils

/**
 * A fragment representing a single Breed detail screen.
 */
class BreedDetailFragment : Fragment() {
    private val args: BreedDetailFragmentArgs by navArgs()
    private lateinit var shareText: String

    private val breedViewModel: BreedViewModel by viewModels {
        InjectorUtils.provideBreedDetailViewModelFactory(requireActivity(), args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentBreedDetailBinding>(
            inflater, R.layout.fragment_breed_detail, container, false).apply {
            viewModel = breedViewModel
            lifecycleOwner = this@BreedDetailFragment
        }

        breedViewModel.breed.observe(this, Observer { breed ->
            (activity as? AppCompatActivity)?.supportActionBar?.title = breedViewModel.breed.value?.name
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                val direction = BreedDetailFragmentDirections.actionBreedDetailFragmentToBreedEditFragment(args.id)
                findNavController().navigate(direction)
                return true
            }
            R.id.action_delete -> {
                if (args.id != "") {
                    breedViewModel.delete()
                }
                findNavController().navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
