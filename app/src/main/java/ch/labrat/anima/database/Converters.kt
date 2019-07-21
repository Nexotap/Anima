package ch.labrat.anima.database

import androidx.room.TypeConverter
import java.util.Calendar

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {
    @TypeConverter fun calendarToDatestamp(calendar: Calendar?): Long? {
        return  if (calendar == null) null else calendar.timeInMillis
    }

    @TypeConverter fun datestampToCalendar(value: Long?): Calendar? {
        return if(value == null) null else Calendar.getInstance().apply { timeInMillis = value }
    }
}