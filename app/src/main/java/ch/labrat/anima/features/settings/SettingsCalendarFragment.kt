package ch.labrat.anima.features.settings


import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.labrat.anima.R
import ch.labrat.anima.utilities.TAG
import kotlinx.android.synthetic.main.fragment_settings_calendar.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsCalendarFragment : Fragment() {

    var curSelection: Int = -1

// Projection array. Creating indices for this array instead of doing
    // dynamic lookups improves performance.
    val EVENT_PROJECTION = arrayOf<String>(
        CalendarContract.Calendars._ID, // 0
        CalendarContract.Calendars.ACCOUNT_NAME, // 1
        CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, // 2
        CalendarContract.Calendars.OWNER_ACCOUNT // 3
    )
    /*
    // The indices for the projection array above.
    private val PROJECTION_ID_INDEX = 0
    private val PROJECTION_ACCOUNT_NAME_INDEX = 1
    private val PROJECTION_DISPLAY_NAME_INDEX = 2
    private val PROJECTION_OWNER_ACCOUNT_INDEX = 3
*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        showCalendars()

//        var colorArrays = resources.getStringArray(R.array.Colors)
//        var arrayAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, colorArrays)

        val uri: Uri = CalendarContract.Calendars.CONTENT_URI
//        val selection: String = "((${CalendarContract.Calendars.ACCOUNT_NAME} = ?) AND (" +
//                "${CalendarContract.Calendars.ACCOUNT_TYPE} = ?) AND (" +
//                "${CalendarContract.Calendars.OWNER_ACCOUNT} = ?))"
//        val selectionArgs: Array<String> = arrayOf("hera@example.com", "com.example", "hera@example.com")
//        val cur: Cursor = activity!!.contentResolver.query(uri, EVENT_PROJECTION, selection, selectionArgs, null)
        val cur: Cursor = activity!!.contentResolver.query(uri, EVENT_PROJECTION, null, null, null)

        val cursorAdapter =  SettingsCalendarCursorAdapter(context!!, cur)

        listView.adapter = cursorAdapter

        listView.setOnItemClickListener { adapterView, view, position: Int, id: Long ->

            Log.i(TAG, "Item: " +  position)
        }

    }
/*
    private fun showCalendars() {
        Log.i(TAG, "showCalendars()")
        val uri: Uri = CalendarContract.Calendars.CONTENT_URI
//        val selection: String = "((${CalendarContract.Calendars.ACCOUNT_NAME} = ?) AND (" +
//                "${CalendarContract.Calendars.ACCOUNT_TYPE} = ?) AND (" +
//                "${CalendarContract.Calendars.OWNER_ACCOUNT} = ?))"
//        val selectionArgs: Array<String> = arrayOf("hera@example.com", "com.example", "hera@example.com")
//        val cur: Cursor = activity!!.contentResolver.query(uri, EVENT_PROJECTION, selection, selectionArgs, null)
        val cur: Cursor = activity!!.contentResolver.query(uri, EVENT_PROJECTION, null, null, null)


        // Use the cursor to step through the returned records
        while (cur.moveToNext()) {
            // Get the field values
            val calID: Long = cur.getLong(PROJECTION_ID_INDEX)
            val displayName: String = cur.getString(PROJECTION_DISPLAY_NAME_INDEX)
            val accountName: String = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX)
            val ownerName: String = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX)

            val fullText = calID.toString() + " " + displayName + " " + accountName + " " + ownerName
            Log.i(TAG, fullText)
        }

        if (cur.count < 1)
            Log.e(TAG,"No calendar found in the device")

        cur.close()

    }
    */
}
