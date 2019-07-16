package ch.labrat.anima.features.settings

import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.room.util.CursorUtil.getColumnIndexOrThrow
import android.content.Context
import android.database.Cursor
import android.provider.CalendarContract
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cursoradapter.widget.CursorAdapter
import ch.labrat.anima.R


class SettingsCalendarCursorAdapter(context: Context, cursor: Cursor) : CursorAdapter(context, cursor, 0) {

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.list_item_calendar, parent, false)
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    override fun bindView(view: View, context: Context, cursor: Cursor) {
        // Find fields to populate in inflated template
        val tvBody = view.findViewById(R.id.tvBody) as TextView
        // Extract properties from cursor
        val body = cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME))
        // Populate fields with extracted properties
        tvBody.setText(body)
    }
}