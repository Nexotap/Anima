package ch.labrat.anima.features.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ch.labrat.anima.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

//    private val viewModel: GardenPlantingListViewModel by viewModels {
//        InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
//    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
//        val adapter = GardenPlantingAdapter()
//        binding.gardenList.adapter = adapter
//        subscribeUi(adapter, binding)
        return binding.root
    }

/*
    private fun subscribeUi(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {
        viewModel.gardenPlantings.observe(viewLifecycleOwner, Observer { plantings ->
            binding.hasPlantings = !plantings.isNullOrEmpty()
        })

        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner, Observer { result ->
            if (!result.isNullOrEmpty())
                adapter.submitList(result)
        })
    }
    */
}
