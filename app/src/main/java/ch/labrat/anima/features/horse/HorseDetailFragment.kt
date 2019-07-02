package ch.labrat.anima.features.horse


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import ch.labrat.anima.R
import ch.labrat.anima.databinding.FragmentHorseDetailBinding
import ch.labrat.anima.databinding.FragmentHorseListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HorseDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHorseDetailBinding>(
            inflater, R.layout.fragment_horse_detail, container, false).apply {
            lifecycleOwner = this@HorseDetailFragment

        }

        setHasOptionsMenu(true)
        return binding.root
    }


}
