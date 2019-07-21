package ch.labrat.anima.adapters

import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.databinding.InverseBindingListener
import ch.labrat.anima.features.breed.BreedSpinnerAdapter
import android.widget.AdapterView
import ch.labrat.anima.features.breed.Breed
import kotlinx.coroutines.selects.select


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    } else {
        view.text = ""
    }
}
/*
@BindingAdapter("selectedValue")
fun Spinner.setSelectedValue(selectedValue: String?) {
    if (adapter != null && selectedValue != null ) {
        val position = adapter.get
        setSelection(position, false)
        tag = position
    }
}
*/
/*
@BindingAdapter("selectedValueAttrChanged")
fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            inverseBindingListener?.onChange()
        }
        override fun onNothingSelected(parent: AdapterView<*>) {}
    }
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun Spinner.getSelectedValue(): Any? {
    val breed: Breed = adapter.getItem(selectedItemPosition) as Breed
    return breed.id
}
*/

/*
@BindingAdapter("selectedValue")
fun Spinner.setSelectedValue(selectedValue: String?) {
    if (adapter != null && selectedValue != null ) {
            val position = (adapter as BreedSpinnerAdapter).getPositionById(selectedValue)
            setSelection(position, false)
            tag = position
        }
}

@BindingAdapter("selectedValueAttrChanged")
fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            inverseBindingListener?.onChange()
        }
        override fun onNothingSelected(parent: AdapterView<*>) {}
    }
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun Spinner.getSelectedValue(): Any? {
    val breed: Breed = adapter.getItem(selectedItemPosition) as Breed
    return breed.id
}
*/


/*
@BindingAdapter("wateringText")
fun bindWateringText(textView: TextView, wateringInterval: Int) {
    val resources = textView.context.resources
    val quantityString = resources.getQuantityString(R.plurals.watering_needs_suffix,
        wateringInterval, wateringInterval)

    textView.text = SpannableStringBuilder()
        .bold { append(resources.getString(R.string.watering_needs_prefix)) }
        .append(" ")
        .italic { append(quantityString) }
}
*/
