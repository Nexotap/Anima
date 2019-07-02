package ch.labrat.anima.features.breed

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import ch.labrat.anima.R
import ch.labrat.anima.databinding.FragmentBreedListBinding
import ch.labrat.anima.utilities.InjectorUtils
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 *
 */
class BreedListFragment : Fragment() {

    private val viewModel: BreedListViewModel by viewModels {
        InjectorUtils.provideBreedListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentBreedListBinding>(
            inflater, R.layout.fragment_breed_list, container, false).apply {
            lifecycleOwner = this@BreedListFragment

            fab.setOnClickListener {
                val direction = BreedListFragmentDirections.actionBreedListFragmentToBreedAddEditFragment("")
                it.findNavController().navigate(direction)
            }
        }

        val adapter = BreedListAdapter()
        binding.breedList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    /*
        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.menu_breed_list, menu)
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
    private fun subscribeUi(adapter: BreedListAdapter) {
        viewModel.breeds.observe(viewLifecycleOwner, Observer { breeds ->
            if (breeds != null) adapter.submitList(breeds)
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
