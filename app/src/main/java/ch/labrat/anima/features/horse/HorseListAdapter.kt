package ch.labrat.anima.features.horse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.labrat.anima.databinding.ListItemHorseBinding

/**
 * Adapter for the [RecyclerView] in [HorseListFragment].
 */
class HorseListAdapter : ListAdapter<Horse, HorseListAdapter.ViewHolder>(HorseDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val horse = getItem(position)
        holder.apply {
            bind(createOnClickListener(horse.id), horse)
            itemView.tag = horse
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemHorseBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(id: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = HorseListFragmentDirections.actionHorseListFragmentToHorseDetailFragment(id)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ListItemHorseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Horse) {
            binding.apply {
                clickListener = listener
                horse = item
                executePendingBindings()
            }
        }
    }
}

private class HorseDiffCallback : DiffUtil.ItemCallback<Horse>() {

    override fun areItemsTheSame(oldItem: Horse, newItem: Horse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Horse, newItem: Horse): Boolean {
        return oldItem == newItem
    }
}