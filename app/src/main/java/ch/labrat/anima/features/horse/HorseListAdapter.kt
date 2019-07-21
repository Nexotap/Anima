package ch.labrat.anima.features.horse

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.labrat.anima.databinding.ListItemHorseBinding
import ch.labrat.anima.utilities.TAG

/**
 * Adapter for the [RecyclerView] in [HorseListFragment].
 */
class HorseListAdapter : ListAdapter<HorseListItem, HorseListAdapter.ViewHolder>(ITEM_COMPARATOR) {

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

        fun bind(listener: View.OnClickListener, item: HorseListItem) {
            binding.apply {
                clickListener = listener
                this.item = item
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int {
        val count = super.getItemCount()
        return count
    }

    companion object {
        // A DiffUtil.ItemCallback for calculating the diff between two non-null items in a list.
        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<HorseListItem>() {
            override fun areContentsTheSame(
                oldItem: HorseListItem,
                newItem: HorseListItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: HorseListItem,
                newItem: HorseListItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}
