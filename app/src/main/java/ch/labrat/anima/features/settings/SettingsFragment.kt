package ch.labrat.anima.features.settings


import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ch.labrat.anima.R
import ch.labrat.anima.features.base.BaseFragment
import ch.labrat.anima.utilities.MY_PERMISSIONS_REQUEST_CALENDAR
import kotlinx.android.synthetic.main.fragment_settings.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsFragment : BaseFragment() {

    private val permissions = arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarItem.setOnClickListener {
            if (arePermissionsEnabled(permissions)) {
                val direction = SettingsFragmentDirections.actionSettingsFragmentToSettingsCalendarFragment()
                findNavController().navigate(direction)
            } else {
                requestMultiplePermissions(permissions, MY_PERMISSIONS_REQUEST_CALENDAR)
            }

        }
    }

}
