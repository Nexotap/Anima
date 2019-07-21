package ch.labrat.anima.utilities

import android.util.Log
import ch.labrat.anima.R
import ch.labrat.anima.core.App

object Conv {

    fun valueFromPosition(position: Int): String {
        val genders = App.applicationContext().resources.getStringArray(R.array.Genders)
        Log.i(TAG, "valueFromPosition:" + position)
        return genders[1]
    }

}