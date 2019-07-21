package ch.labrat.anima.utilities

import ch.labrat.anima.R
import ch.labrat.anima.core.App

class Conv {

    fun valueFromPosition(position: Int): String {
        val genders = App.applicationContext().resources.getStringArray(R.array.Genders)
        return genders[position]
    }

}