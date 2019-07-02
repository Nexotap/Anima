package ch.labrat.anima.features.horse

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import ch.labrat.anima.R
import ch.labrat.anima.databinding.FragmentHorseListBinding
import ch.labrat.anima.utilities.InjectorUtils
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 *
 */
class HorseListFragment : Fragment() {

    private val viewModel: HorseListViewModel by viewModels {
        InjectorUtils.provideHorseListViewModelFactory(requireContext())
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHorseListBinding>(
            inflater, R.layout.fragment_horse_list, container, false).apply {
            lifecycleOwner = this@HorseListFragment

            fab.setOnClickListener {
                val direction = HorseListFragmentDirections.actionHorseListFragmentToHorseDetailFragment("")
                it.findNavController().navigate(direction)
            }
        }
        val adapter = HorseListAdapter()
        binding.horseList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }
/*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_horse_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
*/
    private fun subscribeUi(adapter: HorseListAdapter) {
        viewModel.horses.observe(viewLifecycleOwner, Observer { horses ->
            if (horses != null) adapter.submitList(horses)
        })
    }
/*
    private fun updateData() {
        with(viewModel) {
            if (isFiltered()) {
                clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9)
            }
        }
    }
    */
}
