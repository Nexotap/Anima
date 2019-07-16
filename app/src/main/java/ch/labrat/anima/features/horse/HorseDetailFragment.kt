package ch.labrat.anima.features.horse

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
import ch.labrat.anima.databinding.FragmentHorseDetailBinding
import ch.labrat.anima.utilities.InjectorUtils

/**
 * A fragment representing a single Horse detail screen.
 */
class HorseDetailFragment : Fragment() {
    private val args: HorseDetailFragmentArgs by navArgs()
    private lateinit var shareText: String

    private val horseViewModel: HorseViewModel by viewModels {
        InjectorUtils.provideHorseViewModelFactory(requireActivity(), args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHorseDetailBinding>(
            inflater, R.layout.fragment_horse_detail, container, false).apply {
            viewModel = horseViewModel
            lifecycleOwner = this@HorseDetailFragment
        }

        horseViewModel.horse.observe(this, Observer { horse ->
            (activity as? AppCompatActivity)?.supportActionBar?.title = horseViewModel.horse.value?.name
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
                val direction = HorseDetailFragmentDirections.actionHorseDetailFragmentToHorseAddEditFragment(args.id)
                findNavController().navigate(direction)
                return true
            }
            R.id.action_delete -> {
                if (args.id != "") {
                    horseViewModel.delete()
                }
                findNavController().navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
