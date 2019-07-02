package ch.labrat.anima.features.breed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.labrat.anima.databinding.ListItemBreedBinding

/**
 * Adapter for the [RecyclerView] in [BreedListFragment].
 */
class BreedListAdapter : ListAdapter<Breed, BreedListAdapter.BreedViewHolder>(BreedDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        return BreedViewHolder(ListItemBreedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = getItem(position)
        holder.apply {
            bind(createOnClickListener(breed.id), breed)
            itemView.tag = breed
        }
    }

    private fun createOnClickListener(id: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = BreedListFragmentDirections.actionBreedListFragmentToBreedDetailFragment(id)
            it.findNavController().navigate(direction)
        }
    }

    class BreedViewHolder(
            private val binding: ListItemBreedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Breed) {
            binding.apply {
                clickListener = listener
                breed = item
                executePendingBindings()
            }
        }
    }
}

private class BreedDiffCallBack() : DiffUtil.ItemCallback<Breed>() {

    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem == newItem
    }
}