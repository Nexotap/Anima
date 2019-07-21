package ch.labrat.anima.features.breed

import android.content.Context
import android.widget.ArrayAdapter

class BreedSpinnerAdapter(context: Context, textViewResourceId: Int, private val values: List<Breed>) :
    ArrayAdapter<Breed>(context, textViewResourceId, values) {

    override fun getItem(position: Int): Breed {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getPositionById(id: String): Int {
        for (item: Breed in values) {
            if (item.id == id)
                return values.indexOf(item)
        }
        return -1
    }
/*
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView

        label.setTextColor(Color.RED)
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.text = (values[position].name)
        // And finally return your dynamic (or custom) view for each spinner item
        return label
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = (values[position].name)

        return label
    }
*/
}
